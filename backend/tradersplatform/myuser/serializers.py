from rest_framework.serializers import ModelSerializer
from .models import TemplateUser
from rest_framework.exceptions import ValidationError
from django.contrib.auth.models import User
from django.contrib.auth.models import Group


class TempUserCreateSerializer(ModelSerializer):

    class Meta:
        model = TemplateUser
        fields = [
            'id',
            'username',
            'email',
            'first_name',
            'last_name',
            'password',
            'location',
            'phone_number',
            'iban_number',
            'citizenship_number',
        ]