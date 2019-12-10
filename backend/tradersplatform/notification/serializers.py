import django.utils.timezone
from rest_framework.serializers import ModelSerializer

from notification.models import Notification, SetNotification


class NotificationSerializer(ModelSerializer):

    class Meta:
        model = Notification
        fields = '__all__'


class SetNotificationSerializer(ModelSerializer):

    class Meta:
        model = SetNotification
        fields = '__all__'