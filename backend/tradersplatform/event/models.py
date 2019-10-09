from django.db import models

# Create your models here.
from myuser.models import TemplateUser


class Event(models.Model):
    description = models.CharField(max_length=300, blank=True,null=True, unique=False)
    title = models.CharField(max_length=300, blank=False, null=False, unique=False)
    enabled = models.BooleanField(null=True)
    followers = models.ManyToManyField(TemplateUser, blank=True)
    signifance_level= models.IntegerField(blank=True,null=True,unique=False,default=0)