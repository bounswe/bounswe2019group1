from django.shortcuts import render

# Create your views here.
from rest_framework.generics import CreateAPIView, ListAPIView
from rest_framework.views import APIView

from follow.views import check_if_user
from myuser.models import TemplateUser
from rest_framework.response import Response
from rest_framework.exceptions import ValidationError
from django.apps import apps

from notification.models import Notification
from notification.serializers import NotificationSerializer
from datetime import datetime,timedelta


class CreateNotificationAPIView(CreateAPIView):

    def post(self, request, *args, **kwargs):
        request_data = request.data
        request_data['date']=datetime.now()
        serializer=NotificationSerializer(data=request_data)
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
