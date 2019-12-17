from rest_framework.serializers import ModelSerializer

from article.models import Article
from myuser.serializers import TempUserCreateSerializer


class ArticleCreateSerializer(ModelSerializer):
    class Meta:
        model = Article
        fields = [
            'id',
            'title',
            'content',
            'is_public',
            'author',
            'created_date',
            'image',
        ]


class ArticleListSerializer(ModelSerializer):
    class Meta:
        model = Article
        fields = [
            'id',
            'title',
            'content',
            'is_public',
            'created_date',
            'image',
        ]


class PublicArticleListSerializer(ModelSerializer):
    author = TempUserCreateSerializer()

    class Meta:
        model = Article
        fields = [
            'id',
            'title',
            'content',
            'author',
            'is_public',
            'created_date',
            'image',
        ]


class ArticleUpdateSerializer(ModelSerializer):
    class Meta:
        model = Article
        fields = [
            'id',
            'title',
            'content',
            'is_public',
            'author',
            'created_date',
            'image',
        ]

        extra_kwargs = {"title":
                            {"required": False},
                        "content":
                            {"required": False},
                        "is_public":
                            {"required": False},
                        "author":
                            {"required": False},
                        "created_date":
                            {"required": False},
                        }


class ArticleGetSerializer(ModelSerializer):
    author = TempUserCreateSerializer()

    class Meta:
        model = Article
        fields = [
            'id',
            'title',
            'content',
            'is_public',
            'author',
            'created_date',
            'image',
        ]
