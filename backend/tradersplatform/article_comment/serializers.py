from rest_framework.serializers import ModelSerializer

from article_comment.models import ArticleComment
from myuser.serializers import TempUserListSerializer


class ArticleCommentCreateSerializer(ModelSerializer):
    class Meta:
        model = ArticleComment
        fields = [
            'id',
            'text',
            'user',
            'article',
            'created_date',
        ]


class ArticleCommentUpdateSerializer(ModelSerializer):
    class Meta:
        model = ArticleComment
        fields = [
            'id',
            'text',
            'user',
            'article',
            'created_date',
        ]

        extra_kwargs = {"text":
                            {"required": False},
                        "user":
                            {"required": False},
                        "article":
                            {"required": False},
                        "created_date":
                            {"required": False},
                        }


class ArticleCommentListSerializer(ModelSerializer):
    user = TempUserListSerializer()

    class Meta:
        model = ArticleComment
        fields = [
            'id',
            'text',
            'user',
            'article',
            'created_date',
        ]
