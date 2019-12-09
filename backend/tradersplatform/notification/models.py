
from django.db import models

# Create your models here.

from myuser.models import TemplateUser


class Notification(models.Model):
    owner = models.ForeignKey(TemplateUser, on_delete=models.CASCADE, default='',related_name='notifier')
    text = models.CharField(max_length=300, blank=True, null=True, unique=False, default='')
    date = models.DateTimeField( blank=True, null=True, unique=False, default=None)
