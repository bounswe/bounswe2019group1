import django.utils.timezone
from django.db import models

from myuser.models import TemplateUser
from article.models import Article


class ArticleLike(models.Model):
    article = models.ForeignKey(Article, on_delete=models.CASCADE, blank=False, related_name="liked_article")
    user = models.ForeignKey(TemplateUser, on_delete=models.CASCADE, blank=False, related_name="user_like")
    liked_date = models.DateTimeField(default=django.utils.timezone.now)


class ArticleDislike(models.Model):
    article = models.ForeignKey(Article, on_delete=models.CASCADE, blank=False, related_name="disliked_article")
    user = models.ForeignKey(TemplateUser, on_delete=models.CASCADE, blank=False, related_name="user_dislike")
    disliked_date = models.DateTimeField(default=django.utils.timezone.now)
