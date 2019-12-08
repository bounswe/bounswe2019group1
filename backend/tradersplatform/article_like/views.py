from django.shortcuts import render
import django.utils.timezone
# Create your views here.
from rest_framework.exceptions import ValidationError
from rest_framework.generics import CreateAPIView, ListAPIView, DestroyAPIView, UpdateAPIView
from rest_framework.response import Response

from article.models import Article
from article_like.models import ArticleLike, ArticleDislike
from article_like.serializers import ArticleLikeSerializer, ArticleDislikeSerializer, LikedArticleListSerializer, \
    DislikedArticleListSerializer, ArticleLikeListSerializer, ArticleDislikeListSerializer
from myuser.models import TemplateUser


class LikeArticleAPIView(CreateAPIView):

    def post(self, request, *args, **kwargs):
        check_if_user(request)
        id = request.user.id
        user = TemplateUser.objects.get(id=id)
        article_id = request.data['article_id']
        article = Article.objects.filter(id=article_id).first()
        if not article or not article.is_public:
            return Response({"detail": "article not exist"}, status=400)
        like_query = ArticleLike.objects.filter(article=article, user=user)
        if like_query:
            return Response({"You have already liked this article"}, status=400)
        query = ArticleDislike.objects.filter(article=article, user=user)
        if query:
            dislike = query.first()
            dislike.delete()
        data = {"article": article_id, "user": user, "liked_date": django.utils.timezone.now()}
        serializer = ArticleLikeSerializer(data=data)
        serializer.is_valid(raise_exception=True)
        serializer.save()
        return Response(serializer.data, status=200)


class DislikeArticleAPIView(CreateAPIView):

    def post(self, request, *args, **kwargs):
        check_if_user(request)
        id = request.user.id
        user = TemplateUser.objects.get(id=id)
        article_id = request.data['article_id']
        article = Article.objects.filter(id=article_id).first()
        if not article or not article.is_public:
            return Response({"detail": "article not exist"}, status=400)
        dislike_query = ArticleDislike.objects.filter(article=article, user=user)
        if dislike_query:
            return Response({"You have already disliked this article"}, status=400)
        query = ArticleLike.objects.filter(article=article, user=user)
        if query:
            like = query.first()
            like.delete()
        data = {"article": article_id, "user": user, "disliked_date": django.utils.timezone.now()}
        serializer = ArticleDislikeSerializer(data=data)
        serializer.is_valid(raise_exception=True)
        serializer.save()
        return Response(serializer.data, status=200)


class ListLikedArticlesAPIView(ListAPIView):

    def get(self, request, *args, **kwargs):
        check_if_user(request)
        user_id = kwargs.get("pk")
        user = TemplateUser.objects.get(id=user_id)
        query = ArticleLike.objects.filter(user=user)
        serializer = LikedArticleListSerializer(query, many=True)
        return Response(serializer.data, status=200)


class ListDislikedArticlesAPIView(ListAPIView):

    def get(self, request, *args, **kwargs):
        check_if_user(request)
        user_id = kwargs.get("pk")
        user = TemplateUser.objects.get(id=user_id)
        query = ArticleDislike.objects.filter(user=user)
        serializer = DislikedArticleListSerializer(query, many=True)
        return Response(serializer.data, status=200)


class ArticleLikesAPIView(ListAPIView):

    def get(self, request, *args, **kwargs):
        check_if_user(request)
        article_id = kwargs.get("pk")
        article = Article.objects.get(id=article_id)
        query = ArticleLike.objects.filter(article=article)
        serializer = ArticleLikeListSerializer(query, many=True)
        return Response(serializer.data, status=200)


class ArticleDislikesAPIView(ListAPIView):

    def get(self, request, *args, **kwargs):
        check_if_user(request)
        article_id = kwargs.get("pk")
        article = Article.objects.get(id=article_id)
        query = ArticleDislike.objects.filter(article=article)
        serializer = ArticleDislikeListSerializer(query, many=True)
        return Response(serializer.data, status=200)


class CountArticleLikeAPIView(DestroyAPIView):

    def get(self, request, *args, **kwargs):
        check_if_user(request)
        article_id = kwargs.get("pk")
        article = Article.objects.get(id=article_id)
        count = ArticleLike.objects.filter(article=article).count()
        return Response(data=count, status=200)


class CountArticleDislikeAPIView(UpdateAPIView):

    def get(self, request, *args, **kwargs):
        check_if_user(request)
        article_id = kwargs.get("pk")
        article = Article.objects.get(id=article_id)
        count = ArticleDislike.objects.filter(article=article).count()
        return Response(data=count, status=200)


def check_if_user(request):
    if request.user.is_anonymous:
        raise ValidationError({"detail": "User is not authorized"})
