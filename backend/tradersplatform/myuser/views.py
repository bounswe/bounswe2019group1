from django.core.handlers.base import logger
from django.shortcuts import render

# Create your views here.
from rest_framework.generics import RetrieveAPIView, ListAPIView, CreateAPIView, UpdateAPIView, DestroyAPIView
from rest_framework.views import APIView
from rest_framework_jwt.settings import api_settings
import requests

from follow.views import check_if_user
from myuser.functions import send_email
from myuser.models import TemplateUser
from myuser.serializers import TempUserCreateSerializer, TempUserLoginSerializer, UserUpdateSerializer
from django.contrib.auth.models import Group
from rest_framework.exceptions import ValidationError
from django.contrib.auth.models import User
from rest_framework.response import Response
from rest_framework.status import HTTP_200_OK, HTTP_400_BAD_REQUEST
from datetime import datetime
import json
import logging


class TempUserCreateAPIView(CreateAPIView):
    serializer_class = TempUserCreateSerializer
    queryset = TemplateUser.objects.filter()

    def post(self, request, *args, **kwargs):
        # make password with set password
        data = request.data
        password = request.data.get('password', None)
        if password is None:
            raise ValidationError({"detail": "You need to give a password"})
        elif len(password) < 8:
            raise ValidationError({"detail": "Password size should be greater than 8"})
        username = request.data.get('username', None)
        email = request.data.get('email', None)
        length = len(username)
        for i in range(length - 3):
            if username[i:(i + 4)] in password:
                raise ValidationError({"detail": "Password can not contain public pieces of information"})
        length = len(email)
        for i in range(length - 3):
            if email[i:(i + 4)] in password:
                raise ValidationError({"detail": "Password can not contain public pieces of information"})
        del data['password']
        register_time = datetime.now()
        time = register_time.strftime("%m/%d/%Y, %H:%M:%S")
        data['last_changed_password_date'] = register_time
        serializer = TempUserCreateSerializer(data=data)
        serializer.is_valid(raise_exception=True)
        serializer.save()
        #try:
            #send_email(serializer.data)
        #except:
            #serializer=serializer.data
            #id=serializer['id']
            #temp=TemplateUser.objects.get(id=id)
            #temp.delete()
            #raise ValidationError("email is not valid")
        id=serializer.data["id"]
        user=User.objects.filter(id=id)
        if not user:
            raise ValidationError({"detail": "user not exist"})
        user=user.first()
        user.set_password(password)
        user.save()
        current_url = request.path_info
        group_name = "guest"
        if "trader" in current_url:
            group_name = "trader"
        if "basic" in current_url:
            group_name = "basic"
        my_group, created = Group.objects.get_or_create(name=group_name)
        my_group.user_set.add(user)
        user_temp=TemplateUser.objects.get(id=user.id)
        new_data={}
        serializer=TempUserCreateSerializer(user_temp)
        new_data["user"]=serializer.data
        temp_data={"username":request.data["username"],"password":password}
        serializer = TempUserLoginSerializer(data=temp_data)
        #logger = logging.getLogger('APPNAME')  # or __name__ for current module
        #logger.critical('rising in a stunishing level')
        #logger.info('%s logged in successfully', user_temp.username)
        #logger.error("now error")
        #logger2 = logging.getLogger('APPNAME')
        if serializer.is_valid(raise_exception=True):
            new_data["token"] = serializer.data["token"]
            return Response(new_data, status=HTTP_200_OK)

        return Response(serializer.errors, status=HTTP_400_BAD_REQUEST)


class UserLoginAPIView(APIView):
    serializer_class = TempUserLoginSerializer

    def post(self, request, *args, **kwargs):
        data = request.data
        serializer = TempUserLoginSerializer(data=data)
        if serializer.is_valid(raise_exception=True):
            new_data = serializer.data
            return Response(new_data, status=HTTP_200_OK)
        return Response(serializer.errors, status=HTTP_400_BAD_REQUEST)


class ArrangeLog(APIView):

    def post(self, request, *args, **kwargs):
        logger = logging.getLogger('APPNAME')
        logger.setLevel(logging.ERROR)
        return Response("log arranged", status=HTTP_200_OK)


class UserAutoLogin(APIView):

    def get(self, request, *args, **kwargs):
        if request.user.is_anonymous:

            raise ValidationError({"detail": "User is not authorized"})
        id=request.user.id
        general_user=TemplateUser.objects.filter(id=id)
        if not general_user:
            raise ValidationError({"detail": "User does not exist"})
        general_user=general_user.first()
        serializer=TempUserCreateSerializer(general_user)
        return Response(serializer.data, status=200)


class NoParsingFilter(logging.Filter):
    def filter(self, record):
        return not record.getMessage().startswith('aa')

#logger.addFilter(NoParsingFilter())


class UserRetrieveAPI(RetrieveAPIView):
    serializer_class = TempUserCreateSerializer
    queryset = TemplateUser.objects.filter()


class UserRetrieveMobileAPI(RetrieveAPIView):

    def get(self, request, *args, **kwargs):
        id = request.data.get('id', None)
        if id is not None:
            query=TemplateUser.objects.filter(id=id)
            if not query:
                raise ValidationError({"detail": " User does not exist "})
            query=query.first()
            serializer = TempUserCreateSerializer(query)
            return Response(serializer.data)
        else:
            raise ValidationError({"detail": "Give user id"})


class SearchUserListAPIView(ListAPIView):

    def post(self, request, *args, **kwargs):
        check_if_user(request)
        service_query_general=TemplateUser.objects.all()
        username = request.data.get('username', None)
        name=username.split()
        if username is not None:
                service_query_general = service_query_general.filter(first_name__contains=name[0])
                service_query_general = service_query_general.filter(last_name__contains=name[1])
        else:
            raise ValidationError({"detail": "give username"})

        page = self.paginate_queryset(service_query_general)
        if page is not None:
            serializer = TempUserCreateSerializer(page, many=True)
            return self.get_paginated_response(serializer.data)

        serializer = TempUserCreateSerializer(service_query_general, many=True)
        return Response(serializer.data)


class UserUpdateAPIView (UpdateAPIView):

    def put(self, request, *args, **kwargs):
        id = request.user.id
        user = TemplateUser.objects.filter(id=id).first()
        if not user:
            return Response({"detail": "user not exist"}, status=400)
        serializer = UserUpdateSerializer(user, data=request.data)
        serializer.is_valid(raise_exception=True)
        serializer.save()
        return Response(serializer.data, status=200)


class PasswordUpdateAPIView(UpdateAPIView):

    def put(self, request, *args, **kwargs):
        id = request.user.id
        user = TemplateUser.objects.filter(id=id).first()
        if not user:
            return Response({"detail": "user not exist"}, status=400)
        old_password=request.data.get('old_password', None)
        if old_password is None:
            raise ValidationError({"detail": 'Give us old password'})
        if not user.check_password(old_password):
            raise ValidationError({"detail": "Incorrect credential"})
        new_password = request.data.get('new_password', None)
        if new_password is None:
            raise ValidationError({"detail": 'Give us new password'})
        user.set_password(new_password)
        user.save()
        id=user.id
        new_user = TemplateUser.objects.filter(id=id).first()
        serializer=TempUserCreateSerializer(new_user)
        return Response(serializer.data, status=200)


class PasswordUpdateWithoutLoginAPIView(UpdateAPIView):

    def put(self, request, *args, **kwargs):
        id = kwargs.get("pk")
        user = TemplateUser.objects.filter(id=id).first()
        if not user:
            return Response({"detail": "user not exist"}, status=400)
        new_password = request.data.get('new_password', None)
        if new_password is None:
            raise ValidationError({"detail": 'Give us new password'})
        user.set_password(new_password)
        user.save()
        id=user.id
        new_user = TemplateUser.objects.filter(id=id).first()
        serializer=TempUserCreateSerializer(new_user)
        return Response(serializer.data, status=200)


class ForgotPassword(ListAPIView):

    def post(self, request, *args, **kwargs):
        email=request.data.get('email', None)
        if email is None:
            raise ValidationError({"detail": 'Give the email'})
        try:
            send_email(email)
        except:
            raise ValidationError({"detail":"email is not valid"})


class CurrencyAPI(ListAPIView):

    def get(self, request, *args, **kwargs):
        '''
        find this request in
        https://fixer.io sign up and read the documentation
        :param request:
        :param args:
        :param kwargs:
        :return:
        '''
        url = 'http://data.fixer.io/api/latest?access_key=1c569a006dc128d15b612d8d1ac04f96'
        headers = {}
        response = requests.request('GET', url, headers=headers, allow_redirects=False)
        ret=json.loads(response.text)
        return Response(ret, 200)


class CryptoCurrencyAPI(ListAPIView):

    def get(self, request, *args, **kwargs):
        '''
        find this request in
        https://coinlayer.com/ sign up and read the documentation
        :param request:
        :param args:
        :param kwargs:
        :return:
        '''
        url = 'http://api.coinlayer.com/api/live?access_key=9fdc61fa75c3cfce8e1e5fd50f362113'
        headers = {}
        response = requests.request('GET', url, headers=headers, allow_redirects=False)
        ret=json.loads(response.text)
        return Response(ret, 200)