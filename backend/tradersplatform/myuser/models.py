from django.db import models
from django.contrib.auth.models import User
from django.core.validators import MinValueValidator, MaxValueValidator
from datetime import datetime


# Create your models here.

class TemplateUser(User):
    location = models.CharField(max_length=300, blank=True, null=True, unique=False, default='')
    phone_number = models.CharField(max_length=300, blank=True, null=True, unique=False, default='')
    iban_number = models.CharField(max_length=300, blank=True, null=True, unique=False, default='')
    citizenship_number = models.CharField(max_length=300, blank=True, null=True, unique=False, default='')
    title = models.CharField(max_length=300, blank=True, null=True, unique=False, default='')
    biography = models.CharField(max_length=300, blank=True, null=True, unique=False, default='')
    last_changed_password_date = models.DateTimeField( blank=True, null=True, unique=False, default=None)
    photo = models.FileField(upload_to="profile", blank=True, null=True, unique=False)
    is_public = models.BooleanField(default=False)
    correct_prediction_counter = models.IntegerField(blank=True,null=True,unique=False,default=0)
    prediction_counter = models.IntegerField(blank=True,null=True,unique=False,default=0)
    #yeni = models.CharField(max_length=300, blank=True, null=True, unique=False, default='')
