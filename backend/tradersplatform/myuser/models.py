from django.db import models
from django.contrib.auth.models import User
from django.core.validators import MinValueValidator, MaxValueValidator

# Create your models here.


class TemplateUser(User):
    location = models.CharField(max_length=300, blank=True, null=True, unique=False,default='a')
    phone_number = models.BigIntegerField(blank=True,unique=True, null=True, validators=[MinValueValidator(1000000),
                                                                                  MaxValueValidator(10000000000 - 1)],default='1000001')
    iban_number = models.BigIntegerField(blank=True,unique=True, null=True, validators=[MinValueValidator(1000000000000000),
                                                                                  MaxValueValidator(10000000000000000 - 1)],default='1000001')
    citizenship_number = models.CharField(max_length=300, blank=True, null=True, unique=False,default='a')