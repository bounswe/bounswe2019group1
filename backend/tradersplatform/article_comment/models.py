import django.utils.timezone
from django.db import models

from myuser.models import TemplateUser
from article.models import Article


class ArticleComment(models.Model):
    text = models.CharField(max_length=200, unique=True, blank=False)
    user = models.ForeignKey(TemplateUser, on_delete=models.CASCADE, blank=False, related_name="user")
    article = models.ForeignKey(Article, on_delete=models.CASCADE, blank=False, related_name="article")
    created_date = models.DateTimeField(default=django.utils.timezone.now)
