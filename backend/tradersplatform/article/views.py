from django.shortcuts import render
import django.utils.timezone
# Create your views here.
from rest_framework.exceptions import ValidationError
from rest_framework.generics import CreateAPIView, ListAPIView, DestroyAPIView, UpdateAPIView
from rest_framework.response import Response

from article.models import Article
from article.serializers import ArticleCreateSerializer, ArticleListSerializer, PublicArticleListSerializer, \
    ArticleUpdateSerializer
from myuser.models import TemplateUser


class CreateArticleAPIView(CreateAPIView):

    def post(self, request, *args, **kwargs):
        check_if_user(request)
        id = request.user.id
        user = TemplateUser.objects.get(id=id)
        title = request.data['title']
        content = request.data['content']
        is_public = request.data['is_public']
        data = {"title": title, "content": content, "is_public": is_public, "author": user,
                "created_date": django.utils.timezone.now()}
        serializer = ArticleCreateSerializer(data=data)
        serializer.is_valid(raise_exception=True)
        serializer.save()
        return Response(serializer.data, status=200)


class ListArticleAPIView(ListAPIView):

    def get(self, request, *args, **kwargs):
        check_if_user(request)
        id = request.user.id
        user = TemplateUser.objects.get(id=id)
        query = Article.objects.filter(author=user)
        serializer = ArticleListSerializer(query, many=True)
        return Response(serializer.data, status=200)


class ListPublicArticleAPIView(ListAPIView):
    serializer_class = PublicArticleListSerializer
    queryset = Article.objects.filter(is_public=True)


class ListArticleWithUserIdAPIView(ListAPIView):

    def get(self, request, *args, **kwargs):
        id = request.data.get('id', None)
        if id is None:
            raise ValidationError({"detail": "give id"})
        user = TemplateUser.objects.get(id=id)
        query = Article.objects.filter(author=user)
        serializer = PublicArticleListSerializer(query, many=True)
        return Response(serializer.data, status=200)


class DeleteArticleAPIView(DestroyAPIView):

    def delete(self, request, *args, **kwargs):
        check_if_user(request)
        id = request.user.id
        user = TemplateUser.objects.get(id=id)
        article_id = request.data['id']
        query = Article.objects.filter(id=article_id, author=user)
        if not query:
            raise ValidationError({"detail": 'You do not have an article with this id'})
        article = query.first()
        article.delete()
        return Response(status=200)


class UpdateArticleAPIView(UpdateAPIView):

    def put(self, request, *args, **kwargs):
        check_if_user(request)
        id = request.user.id
        user = TemplateUser.objects.get(id=id)
        article_id = request.data['id']
        article = Article.objects.filter(id=article_id).first()
        if not article:
            return Response({"detail": "article not exist"}, status=400)
        query = Article.objects.filter(id=article_id, author=user)
        if not query:
            raise ValidationError({"detail": 'You do not have an article with this id'})
        serializer = ArticleUpdateSerializer(article, data=request.data)
        serializer.is_valid(raise_exception=True)
        serializer.save()
        return Response(serializer.data, status=200)


def check_if_user(request):
    if request.user.is_anonymous:
        raise ValidationError({"detail": "User is not authorized"})
