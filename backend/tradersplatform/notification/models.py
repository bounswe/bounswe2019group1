
from django.db import models

# Create your models here.

from myuser.models import TemplateUser


class Notification(models.Model):
    owner = models.ForeignKey(TemplateUser, on_delete=models.CASCADE, default='',related_name='notifier')
    text = models.CharField(max_length=300, blank=True, null=True, unique=False, default='')
    date = models.DateTimeField( blank=True, null=True, unique=False, default=None)
    is_pending = models.BooleanField(default=False)
    is_active = models.BooleanField(default=False)


class SetNotification(models.Model):
    owner = models.ForeignKey(TemplateUser, on_delete=models.CASCADE, default='',related_name='owner_notif')
    currency = models.CharField(max_length=300, blank=True, null=True, unique=False, default='')
    amount = models.DecimalField(max_digits=20, decimal_places=10, blank=True, null=True, unique=False, default=0)
    is_bigger= models.BooleanField(default=True)