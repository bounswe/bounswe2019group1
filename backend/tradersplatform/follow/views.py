from django.shortcuts import render

# Create your views here.
from rest_framework.exceptions import ValidationError
from rest_framework.generics import CreateAPIView, ListAPIView, DestroyAPIView
from rest_framework.response import Response

from follow.models import Follow
from follow.serializers import FollowCreateSerializer, FollowerListSerializer, FollowingListSerializer
from myuser.models import TemplateUser


class CreateFollowAPIView(CreateAPIView):

    def post(self, request, *args, **kwargs):
        check_if_user(request)
        id=request.user.id
        user=TemplateUser.objects.get(id=id)
        following = request.data['following']
        data = {"follower": user, "following": following}
        serializer=FollowCreateSerializer(data=data)
        serializer.is_valid(raise_exception=True)
        serializer.save()
        return Response(serializer.data, status=200)


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
        return Response(status=200)


class ListFollowerAPIView(ListAPIView):

    def get(self, request, *args, **kwargs):
        check_if_user(request)
        id=request.user.id
        user=TemplateUser.objects.get(id=id)
        query=Follow.objects.filter(following=user)
        serializer = FollowerListSerializer(query, many=True)
        return Response(serializer.data, status=200)


class ListFollowerWithIdAPIView(ListAPIView):

    def get(self, request, *args, **kwargs):
        check_if_user(request)
        id = request.data.get('id', None)
        if id  is None:
            raise ValidationError({"detail": "give id"})
        user=TemplateUser.objects.get(id=id)
        query=Follow.objects.filter(following=user)
        serializer = FollowerListSerializer(query, many=True)
        return Response(serializer.data, status=200)


class ListFollowingAPIView(ListAPIView):

    def get(self, request, *args, **kwargs):
        check_if_user(request)
        id=request.user.id
        user=TemplateUser.objects.get(id=id)
        query=Follow.objects.filter(follower=user)
        serializer = FollowingListSerializer(query, many=True)
        return Response(serializer.data, status=200)


class ListFollowingWithIdAPIView(ListAPIView):

    def get(self, request, *args, **kwargs):
        check_if_user(request)
        id = request.data.get('id', None)
        if id  is None:
            raise ValidationError({"detail": "give id"})
        user=TemplateUser.objects.get(id=id)
        query=Follow.objects.filter(follower=user)
        serializer = FollowingListSerializer(query, many=True)
        return Response(serializer.data, status=200)


def check_if_user(request):
    if request.user.is_anonymous:
        raise ValidationError({"detail": "User is not authorized"})