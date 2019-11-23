import django.utils.timezone
from django.db import models

from myuser.models import TemplateUser


class Article(models.Model):
    title = models.CharField(max_length=300, unique=True, blank=False)
    content = models.CharField(max_length=5000, unique=False, blank=False)
    is_public = models.BooleanField(default=True)
    author = models.ForeignKey(TemplateUser, on_delete=models.CASCADE, blank=False, related_name="article")
    created_date = models.DateTimeField(default=django.utils.timezone.now)
