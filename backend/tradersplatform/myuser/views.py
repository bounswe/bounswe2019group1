from django.core.handlers.base import logger
from django.shortcuts import render

# Create your views here.
from rest_framework.generics import RetrieveAPIView, ListAPIView, CreateAPIView, UpdateAPIView, DestroyAPIView
from rest_framework.views import APIView
from rest_framework_jwt.settings import api_settings

from myuser.functions import send_email_cv
from myuser.models import TemplateUser
from myuser.serializers import TempUserCreateSerializer, TempUserLoginSerializer
from django.contrib.auth.models import Group
from rest_framework.exceptions import ValidationError
from django.contrib.auth.models import User
from rest_framework.response import Response
from rest_framework.status import HTTP_200_OK, HTTP_400_BAD_REQUEST
from datetime import datetime
import logging


class TempUserCreateAPIView(CreateAPIView):
    serializer_class = TempUserCreateSerializer
    queryset = TemplateUser.objects.filter()

    def post(self, request, *args, **kwargs):
        #make password with set password
        data=request.data
        password = request.data.get('password', None)
        if password is None:
            raise ValidationError("You need to give a password")
        elif len(password) < 8:
            raise ValidationError("Password size should be greater than 8")
        username = request.data.get('username', None)
        email = request.data.get('email', None)
        length = len(username)
        for i in range(length-3):
            print(i)
            if username[i:(i+4)] in password:
                raise ValidationError("Password can not contain public pieces of information")
        length = len(email)
        for i in range(length-3):
            if email[i:(i + 4)] in password:
                raise ValidationError("Password can not contain public pieces of information")
        del data['password']
        register_time=datetime.now()
        time=register_time.strftime("%m/%d/%Y, %H:%M:%S")
        data['last_changed_password_date']=register_time
        serializer=TempUserCreateSerializer(data=data)
        serializer.is_valid(raise_exception=True)
        serializer.save()
        #try:
            #send_email_cv(serializer.data)
        #except:
            #serializer=serializer.data
            #id=serializer['id']
            #temp=TemplateUser.objects.get(id=id)
            #temp.delete()
            #raise ValidationError("email is not valid")
        id=serializer.data["id"]
        user=User.objects.filter(id=id)
        if not user:
            raise ValidationError("user not exist")
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
        #logger = logging.getLogger('APPNAME')
        #logger.critical('rising in a stunishing level')
        #logger.info('%s logged in successfully', user_temp.username)
        new_data={}
        serializer=TempUserCreateSerializer(user_temp)
        new_data["user"]=serializer.data
        temp_data={"username":request.data["username"],"password":password}
        serializer = TempUserLoginSerializer(data=temp_data)
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


class UserAutoLogin(APIView):

    def get(self, request, *args, **kwargs):
        if request.user.is_anonymous:
            raise ValidationError("User is not authorized")
        id=request.user.id
        general_user=TemplateUser.objects.filter(id=id)
        if not general_user:
            raise ValidationError("User does not exist")
        general_user=general_user.first()
        serializer=TempUserCreateSerializer(general_user)
        return Response(serializer.data, status=200)


class NoParsingFilter(logging.Filter):
    def filter(self, record):
        return not record.getMessage().startswith('aa')

#logger.addFilter(NoParsingFilter())
