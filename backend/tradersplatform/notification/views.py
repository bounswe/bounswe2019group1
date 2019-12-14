import os

from django.shortcuts import render

# Create your views here.
from rest_framework.generics import CreateAPIView, ListAPIView
from rest_framework.views import APIView

from follow.views import check_if_user
from myuser.models import TemplateUser
from rest_framework.response import Response
from rest_framework.exceptions import ValidationError
from django.apps import apps

from notification.functions import set_notification, buy_order, sell_order, predict
from notification.models import Notification, SetNotification
from notification.serializers import NotificationSerializer, SetNotificationSerializer, BuyOrderSerializer, \
    SellOrderSerializer
from datetime import datetime,timedelta


class CreateNotificationAPIView(CreateAPIView):

    def post(self, request, *args, **kwargs):
        request_data = request.data
        request_data['date']=datetime.now()
        serializer=NotificationSerializer(data=request_data)
        serializer.is_valid(raise_exception=True)
        serializer.save()
        return Response(serializer.data, status=200)


class SetNotificationAPIView(CreateAPIView):

    def post(self, request, *args, **kwargs):
        check_if_user(request)
        request_data = request.data
        request_data['owner']=request.user.id
        serializer=SetNotificationSerializer(data=request_data)
        serializer.is_valid(raise_exception=True)
        serializer.save()
        return Response(serializer.data, status=200)


class BuyOrderAPIView(CreateAPIView):

    def post(self, request, *args, **kwargs):
        check_if_user(request)
        request_data = request.data
        request_data['owner']=request.user.id
        serializer=BuyOrderSerializer(data=request_data)
        serializer.is_valid(raise_exception=True)
        serializer.save()
        return Response(serializer.data, status=200)


class SellOrderAPIView(CreateAPIView):

    def post(self, request, *args, **kwargs):
        check_if_user(request)
        request_data = request.data
        request_data['owner']=request.user.id
        serializer=SellOrderSerializer(data=request_data)
        serializer.is_valid(raise_exception=True)
        serializer.save()
        return Response(serializer.data, status=200)



class ListNotificationAPIView(ListAPIView):
    serializer_class = NotificationSerializer
    queryset = Notification.objects.filter()

    def get_queryset(self, *args, **kwargs):
        check_if_user(self.request)
        user_id=self.request.user.id
        a=Notification.objects.filter(owner__id=user_id)
        return Notification.objects.filter(owner__id=user_id).order_by("-date")


class ListSetNotificationAPIView(ListAPIView):
    serializer_class = SetNotificationSerializer
    queryset = SetNotification.objects.filter()

    def get_queryset(self, *args, **kwargs):
        buy_order()
        check_if_user(self.request)
        user_id=self.request.user.id
        a=Notification.objects.filter(owner__id=user_id)
        return SetNotification.objects.filter(owner__id=user_id)
