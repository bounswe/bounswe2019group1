from django.shortcuts import render

# Create your views here.
from rest_framework.generics import RetrieveAPIView, ListAPIView, CreateAPIView, UpdateAPIView, DestroyAPIView
from rest_framework_jwt.settings import api_settings
from myuser.models import TemplateUser
from myuser.serializers import TempUserCreateSerializer
from django.contrib.auth.models import Group
from rest_framework.exceptions import ValidationError
from django.contrib.auth.models import User
from rest_framework.response import Response


class TempUserCreateAPIView(CreateAPIView):
    serializer_class = TempUserCreateSerializer
    queryset = TemplateUser.objects.filter()

    def post(self, request, *args, **kwargs):
        #make password with set password
        data=request.data
        serializer=TempUserCreateSerializer(data=data)
        serializer.is_valid(raise_exception=True)
        serializer.save()
        id=serializer.data["id"]
        user=User.objects.filter(id=id)
        if not user:
            raise ValidationError("user not exist")
        user=user.first()
        current_url = request.path_info
        group_name = "guest"
        if "trader" in current_url:
            group_name = "trader"
        if "basic" in current_url:
            group_name = "basic"
        my_group, created = Group.objects.get_or_create(name=group_name)
        my_group.user_set.add(user)
        user_temp=TemplateUser.objects.get(id=user.id)
        serializer=TempUserCreateSerializer(user_temp)
        return Response(serializer.data, status=200)