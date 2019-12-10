import django.utils.timezone
from rest_framework.serializers import ModelSerializer

from notification.models import Notification, SetNotification, BuyOrder, SellOrder


class NotificationSerializer(ModelSerializer):

    class Meta:
        model = Notification
        fields = '__all__'


class SetNotificationSerializer(ModelSerializer):

    class Meta:
        model = SetNotification
        fields = '__all__'


class BuyOrderSerializer(ModelSerializer):

    class Meta:
        model = BuyOrder
        fields = '__all__'


class SellOrderSerializer(ModelSerializer):

    class Meta:
        model = SellOrder
        fields = '__all__'