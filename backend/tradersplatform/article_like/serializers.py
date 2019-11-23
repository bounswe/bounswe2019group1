from rest_framework.serializers import ModelSerializer

from article_like.models import ArticleLike, ArticleDislike
from myuser.serializers import TempUserCreateSerializer
from article.serializers import ArticleCreateSerializer


class ArticleLikeSerializer(ModelSerializer):
    class Meta:
        model = ArticleLike
        fields = [
            'id',
            'article',
            'user',
            'liked_date',
        ]


class ArticleDislikeSerializer(ModelSerializer):
    class Meta:
        model = ArticleDislike
        fields = [
            'id',
            'article',
            'user',
            'disliked_date',
        ]


class LikedArticleListSerializer(ModelSerializer):
    article = ArticleCreateSerializer()

    class Meta:
        model = ArticleLike
        fields = [
            'id',
            'article',
            'liked_date',
        ]


class DislikedArticleListSerializer(ModelSerializer):
    article = ArticleCreateSerializer()

    class Meta:
        model = ArticleDislike
        fields = [
            'id',
            'article',
            'disliked_date',
        ]


class ArticleLikeListSerializer(ModelSerializer):
    user = TempUserCreateSerializer()

    class Meta:
        model = ArticleLike
        fields = [
            'id',
            'user',
            'liked_date',
        ]


class ArticleDislikeListSerializer(ModelSerializer):
    user = TempUserCreateSerializer()

    class Meta:
        model = ArticleLike
        fields = [
            'id',
            'user',
            'disliked_date',
        ]
