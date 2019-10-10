from django.shortcuts import render

# Create your views here.
from rest_framework.generics import RetrieveAPIView, ListAPIView, CreateAPIView, UpdateAPIView, DestroyAPIView
from rest_framework.views import APIView
from rest_framework_jwt.settings import api_settings
from myuser.models import TemplateUser
from myuser.serializers import TempUserCreateSerializer, TempUserLoginSerializer
from django.contrib.auth.models import Group
from rest_framework.exceptions import ValidationError
from django.contrib.auth.models import User
from rest_framework.response import Response
from rest_framework.status import HTTP_200_OK, HTTP_400_BAD_REQUEST
import requests


def get_location(location_request):
    response = requests.get("https://maps.googleapis.com/maps/api/geocode/json",
                            params=[('latlng', location_request), ('key', 'API_KEY')])
    if response.status_code == 200:
        address_result = response.json()
        if address_result.size() > 0:
            addresses = address_result["results"]
            return addresses[0]["formatted_address"]
        else:
            return ValidationError("Address not found")


class TempUserCreateAPIView(CreateAPIView):
    serializer_class = TempUserCreateSerializer
    queryset = TemplateUser.objects.filter()

    def post(self, request, *args, **kwargs):
        # make password with set password
        data = request.data
        password = request.data.get('password', None)
        if password is None:
            raise ValidationError("you need to give a password")
        del data['password']
        serializer = TempUserCreateSerializer(data=data)
        serializer.is_valid(raise_exception=True)
        serializer.save()
        id = serializer.data["id"]
        user = User.objects.filter(id=id)
        if not user:
            raise ValidationError("user not exist")
        user = user.first()
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
        user_temp = TemplateUser.objects.get(id=user.id)
        location_request = data['location']
        if get_location(location_request):
            user_temp['last_changed_password_date'] = get_location(location_request)
        new_data = {}
        serializer = TempUserCreateSerializer(user_temp)
        new_data["user"] = serializer.data
        temp_data = {"username": request.data["username"], "password": password}
        serializer = TempUserLoginSerializer(data=temp_data)
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
        id = request.user.id
        general_user = TemplateUser.objects.filter(id=id)
        if not general_user:
            raise ValidationError("User does not exist")
        general_user = general_user.first()
        serializer = TempUserCreateSerializer(general_user)
        return Response(serializer.data, status=200)

