from django.contrib.auth.models import User
from django.core.serializers.json import DjangoJSONEncoder
from django.forms import model_to_dict
from django.shortcuts import render, redirect

# Create your views here.
from rest_framework.generics import RetrieveAPIView, ListAPIView, CreateAPIView, UpdateAPIView, DestroyAPIView
from rest_framework.response import Response
from rest_framework.status import HTTP_200_OK, HTTP_400_BAD_REQUEST
from rest_framework.utils import json
from rest_framework.views import APIView
from rest_framework_jwt.settings import api_settings
from auth.serializers import *


jwt_payload_handler = api_settings.JWT_PAYLOAD_HANDLER
jwt_encode_handler = api_settings.JWT_ENCODE_HANDLER


class UserCreateAPIView(CreateAPIView):
    serializer_class = UserCreateSerializer
    queryset = User.objects.filter()


class UserListAPIView(ListAPIView):
    serializer_class = UserCreateSerializer
    queryset = User.objects.filter()