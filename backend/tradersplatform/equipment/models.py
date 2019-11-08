from django.db import models


# Create your models here


class CryptoCurrencies(models.Model):
    BTC = models.CharField(max_length=300, blank=True,null=True, unique=False)
    ETH = models.CharField(max_length=300, blank=False, null=False, unique=False)
    LTC = models.CharField(max_length=300, blank=False, null=False, unique=False)


class Metals(models.Model):
    XAG = models.CharField(max_length=300, blank=True,null=True, unique=False)
    XAU = models.CharField(max_length=300, blank=False, null=False, unique=False)
    XRH = models.CharField(max_length=300, blank=False, null=False, unique=False)