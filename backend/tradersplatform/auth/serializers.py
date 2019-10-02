from rest_framework.fields import SerializerMethodField
from rest_framework.serializers import ModelSerializer
from django.contrib.auth.models import User


class UserCreateSerializer(ModelSerializer):

    class Meta:
        model = User
        fields = [
            'id',
            'username',
            'email',
            'first_name',
            'last_name',
            'password'
        ]


class UserListSerializer(ModelSerializer):
    groups = SerializerMethodField()

    class Meta:
        model = User
        fields = [
            'id',
            'username',
            'email',
            'first_name',
            'last_name',
            'groups'
        ]

    def get_groups(self, obj):
        groups = obj.groups.all()
        g = [group.name for group in groups]
        return g