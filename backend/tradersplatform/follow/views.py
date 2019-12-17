from django.http import HttpResponse
from django.shortcuts import render

# Create your views here.
from rest_framework.exceptions import ValidationError
from rest_framework.generics import CreateAPIView, ListAPIView, DestroyAPIView, UpdateAPIView
from rest_framework.response import Response
from rest_framework.views import APIView
import json

from follow.models import Follow
from follow.serializers import FollowCreateSerializer, FollowerListSerializer, FollowingListSerializer
from myuser.models import TemplateUser
from notification.models import Notification
from datetime import datetime


class CreateFollowAPIView(CreateAPIView):

    def post(self, request, *args, **kwargs):
        check_if_user(request)
        id=request.user.id
        user=TemplateUser.objects.get(id=id)
        following = request.data['following']
        query = Follow.objects.filter(following=following, follower=user)
        if query:
            raise ValidationError({"detail": 'You have already follow this person'})
        user_following=TemplateUser.objects.filter(id=following).first()
        if not user_following:
            raise ValidationError({"detail": 'User does not exist'})
        if user_following.is_public:
            data= {"follower": user, "following": following,"is_active" : True}
        else:
            data = {"follower": user, "following": following,"is_active" : False}
        serializer=FollowCreateSerializer(data=data)
        serializer.is_valid(raise_exception=True)
        serializer.save()
        if not user_following.is_public:
            new_notif=Notification(
                owner=user_following,
                text=request.user.username+" wants to follow you",
                date=datetime.now()
            )
            new_notif.save()
        return Response(serializer.data, status=200)


class ApproveFollowAPIView(UpdateAPIView):

    def put(self, request, *args, **kwargs):
        check_if_user(request)
        id = kwargs.get("pk")
        user_id = request.user.id
        query = Follow.objects.filter(id=id,following=user_id).first()
        if not query:
            raise ValidationError({"detail": 'This Follow does not exist or you are not follower of this request'})
        query.is_active=True
        query.save()
        serializer=FollowerListSerializer(query)
        return Response(serializer.data,status=200)


class ListFollowAPIView(ListAPIView):
    serializer_class = FollowCreateSerializer
    queryset = Follow.objects.all()


class DeleteFollowAPIView(DestroyAPIView):

    def delete(self, request, *args, **kwargs):
        check_if_user(request)
        id = request.user.id
        user = TemplateUser.objects.get(id=id)
        following=request.data['following']
        query = Follow.objects.filter(following=following,follower=user)
        if not query:
            raise ValidationError({"detail": 'You do not follow this person'})
        follow=query.first()
        follow.delete()
        return Response({},status=200)


class ListFollowerAPIView(ListAPIView):

    def get(self, request, *args, **kwargs):
        check_if_user(request)
        id=request.user.id
        user=TemplateUser.objects.get(id=id)
        query=Follow.objects.filter(following=user)
        serializer = FollowerListSerializer(query, many=True)
        return Response({"list":serializer.data}, status=200)


class ListFollowerWithIdAPIView(ListAPIView):

    def get(self, request, *args, **kwargs):
        check_if_user(request)
        id = request.data.get('id', None)
        if id  is None:
            raise ValidationError({"detail": "give id"})
        user=TemplateUser.objects.get(id=id)
        query=Follow.objects.filter(following=user)
        serializer = FollowerListSerializer(query, many=True)
        return Response({"list":serializer.data}, status=200)


class ListFollowerWithIdFrontAPIView(ListAPIView):

    def get(self, request, *args, **kwargs):
        check_if_user(request)
        id = kwargs.get("pk")
        user = TemplateUser.objects.filter(id=id).first()
        if not user:
            raise ValidationError({"detail": "user does not exist"})
        query=Follow.objects.filter(following=user)
        serializer = FollowerListSerializer(query, many=True)
        return Response({"list":serializer.data}, status=200)


class ListFollowingAPIView(ListAPIView):

    def get(self, request, *args, **kwargs):
        check_if_user(request)
        id=request.user.id
        user=TemplateUser.objects.get(id=id)
        query=Follow.objects.filter(follower=user)
        serializer = FollowingListSerializer(query, many=True)
        return Response({"list":serializer.data}, status=200)


class IsFollowingAPIView(APIView):

    def get(self, request, *args, **kwargs):
        check_if_user(request)
        id=request.user.id
        following_id=request.data.get('following',None)
        if following_id is None:
            raise ValidationError({"detail": "give following id"})
        user=TemplateUser.objects.get(id=id)
        following=TemplateUser.objects.filter(id=following_id).first()
        if not following:
            raise ValidationError({"detail": "User with this following id does not exist"})
        query=Follow.objects.filter(follower=user,following=following).first()
        if query:
            return HttpResponse(json.dumps({'result': 'Found'}),
                       content_type="application/json")
        else:
            return HttpResponse(json.dumps({'result': 'Not Found'}),
                       content_type="application/json")


class IsFollowingFrontAPIView(APIView):

    def get(self, request, *args, **kwargs):
        check_if_user(request)
        id=request.user.id
        following_id=kwargs.get("pk")
        if following_id is None:
            raise ValidationError({"detail": "give following id"})
        user=TemplateUser.objects.get(id=id)
        following=TemplateUser.objects.filter(id=following_id).first()
        if not following:
            raise ValidationError({"detail": "User with this following id does not exist"})
        query=Follow.objects.filter(follower=user,following=following).first()
        if query:
            return HttpResponse(json.dumps({'result': 'Found'}),
                       content_type="application/json")
        else:
            return HttpResponse(json.dumps({'result': 'Not Found'}),
                       content_type="application/json")


class IsFollowerFrontAPIView(APIView):

    def get(self, request, *args, **kwargs):
        check_if_user(request)
        following_id=request.user.id
        id=kwargs.get("pk")
        if following_id is None:
            raise ValidationError({"detail": "give following id"})
        user=TemplateUser.objects.get(id=id)
        following=TemplateUser.objects.filter(id=following_id).first()
        if not following:
            raise ValidationError({"detail": "User with this following id does not exist"})
        query=Follow.objects.filter(follower=user,following=following).first()
        if query:
            return HttpResponse(json.dumps({'result': 'Found'}),
                       content_type="application/json")
        else:
            return HttpResponse(json.dumps({'result': 'Not Found'}),
                       content_type="application/json")



class ListFollowingWithIdAPIView(ListAPIView):

    def get(self, request, *args, **kwargs):
        check_if_user(request)
        id = request.data.get('id', None)
        if id  is None:
            raise ValidationError({"detail": "give id"})
        user=TemplateUser.objects.get(id=id)
        query=Follow.objects.filter(follower=user)
        serializer = FollowingListSerializer(query, many=True)
        return Response({"list":serializer.data}, status=200)


class ListFollowingFrontWithIdAPIView(ListAPIView):

    def get(self, request, *args, **kwargs):
        check_if_user(request)
        id = kwargs.get("pk")
        user=TemplateUser.objects.filter(id=id).first()
        if not user:
            raise ValidationError({"detail": "user does not exist"})
        query=Follow.objects.filter(follower=user)
        serializer = FollowingListSerializer(query, many=True)
        return Response({"list":serializer.data}, status=200)


def check_if_user(request):
    if request.user.is_anonymous:
        raise ValidationError({"detail": "User is not authorized"})


def check_if_basic(request):
    if request.user.is_anonymous:
        raise ValidationError({"detail":"User is not authorized"})
    user = request.user
    u = TemplateUser.objects.get(username=user.username)
    query_groups = u.groups.all()
    groups_names = []
    for groups in query_groups:
        groups_names.append(groups.name)

    if 'trader' in groups_names:
        raise ValidationError({"detail":"User is already a trader"})
    if 'basic' not in groups_names:
        raise ValidationError({"detail":"User is not authorized for this activity"})


def check_if_trader(request):
    if request.user.is_anonymous:
        raise ValidationError({"detail":"User is not authorized"})
    user = request.user
    u = TemplateUser.objects.get(username=user.username)
    query_groups = u.groups.all()
    groups_names = []
    for groups in query_groups:
        groups_names.append(groups.name)

    if 'basic' in groups_names:
        raise ValidationError({"detail":"User is already a basic"})
    if 'trader' not in groups_names:
        raise ValidationError({"detail":"User is not authorized for this activity"})