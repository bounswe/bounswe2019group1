from django.shortcuts import render

import json
import urllib
import django.utils.timezone
# Create your views here.
from rest_framework.exceptions import ValidationError
from rest_framework.generics import CreateAPIView, ListAPIView, DestroyAPIView, UpdateAPIView, RetrieveAPIView
from rest_framework.response import Response

from article.models import Article
from article.serializers import ArticleCreateSerializer, ArticleListSerializer, PublicArticleListSerializer, \
    ArticleUpdateSerializer, ArticleGetSerializer
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
        query = Article.objects.filter(author=user).order_by('-created_date')
        serializer = ArticleListSerializer(query, many=True)
        return Response(serializer.data, status=200)


class ListPublicArticleAPIView(ListAPIView):
    serializer_class = PublicArticleListSerializer
    queryset = Article.objects.filter(is_public=True).order_by('-created_date')

class SearchArticle(ListAPIView):

    def get(self, request, *args, **kwargs):
        queryset = Article.objects.filter(is_public=True).order_by('-created_date')

        search_item = kwargs.get("pk")
        url = "https://api.datamuse.com/words?ml=" + str(search_item) + "&max=100"
        response = urllib.request.urlopen(url)
        semantics = json.loads(response.read())

        semantics.insert(0, {"word": str(search_item), "score": 1000000, "tags": []})
        search_results = {"results": []}
        for word in semantics:
            for article in queryset.all():
                if word['word'] in article.title.lower() or word['word'] in article.content.lower():
                    # deserialized_article = json.loads(PublicArticleListSerializer(data=article))

                    search_results['results'].append({"id": article.id,
                                                      "title": article.title,
                                                      "content": article.content,
                                                      "author": "",
                                                      "is_public": True,
                                                      "created_date": article.created_date,
                                                      "image": None})

        return Response(search_results, 200)


class ListArticleWithUserIdAPIView(ListAPIView):

    def get(self, request, *args, **kwargs):
        id = kwargs.get("pk")
        if id is None:
            raise ValidationError({"detail": "give id"})
        user = TemplateUser.objects.get(id=id)
        query = Article.objects.filter(author=user).order_by('-created_date')
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
        return Response({},status=200)


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


class GetArticleAPIView(RetrieveAPIView):

    def get(self, request, *args, **kwargs):
        article_id = kwargs.get("pk")
        query = Article.objects.filter(id=article_id)
        if not query:
            raise ValidationError({"detail": 'article does not exist'})
        article = query.first()
        serializer = ArticleGetSerializer(article)
        return Response(serializer.data, status=200)


def check_if_user(request):
    if request.user.is_anonymous:
        raise ValidationError({"detail": "User is not authorized"})
