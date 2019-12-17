from django.core.validators import MinValueValidator, MaxValueValidator
from django.db import models
from django.contrib.auth.models import User

# Create your models here.

from myuser.models import TemplateUser


class Follow(models.Model):
    follower = models.ForeignKey(TemplateUser, on_delete=models.CASCADE, default='',related_name='follower')
    following = models.ForeignKey(TemplateUser, on_delete=models.CASCADE, default='',related_name='following')
    is_active = models.BooleanField(default=False)
