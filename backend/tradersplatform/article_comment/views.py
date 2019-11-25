import django.utils.timezone

# Create your views here.

from rest_framework.exceptions import ValidationError
from rest_framework.generics import CreateAPIView, ListAPIView, DestroyAPIView, UpdateAPIView
from rest_framework.response import Response

from article.models import Article
from article_comment.models import ArticleComment
from article_comment.serializers import ArticleCommentCreateSerializer, ArticleCommentUpdateSerializer, ArticleCommentListSerializer
from myuser.models import TemplateUser


class CreateArticleCommentAPIView(CreateAPIView):

    def post(self, request, *args, **kwargs):
        check_if_user(request)
        id = request.user.id
        user = TemplateUser.objects.get(id=id)
        text = request.data['text']
        article_id = request.data['article_id']
        article = Article.objects.get(id=article_id)
        if article is None or not article.is_public:
            raise ValidationError("Article does not exist")
        data = {"text": text, "user": user, "article": article.id,
                "created_date": django.utils.timezone.now()}
        serializer = ArticleCommentCreateSerializer(data=data)
        serializer.is_valid(raise_exception=True)
        serializer.save()
        return Response(serializer.data, status=200)


class DeleteArticleCommentAPIView(DestroyAPIView):

    def delete(self, request, *args, **kwargs):
        check_if_user(request)
        id = request.user.id
        user = TemplateUser.objects.get(id=id)
        comment_id = request.data['id']
        query = ArticleComment.objects.filter(id=comment_id, user=user)
        if not query:
            raise ValidationError({"detail": 'You do not have a comment with this id'})
        article_comment = query.first()
        article_comment.delete()
        return Response(status=200)


class UpdateArticleCommentAPIView(UpdateAPIView):

    def put(self, request, *args, **kwargs):
        check_if_user(request)
        id = request.user.id
        user = TemplateUser.objects.get(id=id)
        comment_id = request.data['id']
        comment = ArticleComment.objects.get(id=comment_id)
        if comment is None:
            raise ValidationError("Comment does not exist")
        query = ArticleComment.objects.filter(id=comment_id, user=user)
        if not query:
            raise ValidationError({"detail": 'You do not have a comment with this id'})
        serializer = ArticleCommentUpdateSerializer(comment, data=request.data)
        serializer.is_valid(raise_exception=True)
        serializer.save()
        return Response(serializer.data, status=200)


class ListArticleCommentAPIView(DestroyAPIView):
    def get(self, request, *args, **kwargs):
        article_id = kwargs.get("pk")
        query = ArticleComment.objects.filter(article_id=article_id)
        serializer = ArticleCommentListSerializer(query, many=True)
        return Response(serializer.data, status=200)


def check_if_user(request):
    if request.user.is_anonymous:
        raise ValidationError({"detail": "User is not authorized"})
