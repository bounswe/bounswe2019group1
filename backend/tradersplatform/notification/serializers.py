import django.utils.timezone
from rest_framework.serializers import ModelSerializer

from notification.models import Notification


class NotificationSerializer(ModelSerializer):

    class Meta:
        model = Notification
        fields = '__all__'
