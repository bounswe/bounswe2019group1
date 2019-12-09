from django.db import models

# Create your models here.
from myuser.models import TemplateUser


class Portfolio(models.Model):
    owner = models.ForeignKey(TemplateUser, on_delete=models.CASCADE, default='')
    name = models.CharField(max_length=300, blank=True, null=True, unique=False, default='default')
    BTC = models.BooleanField(default=False)
    ETH = models.BooleanField(default=False)
    LTC = models.BooleanField(default=False)
    XAG = models.BooleanField(default=False)
    XAU = models.BooleanField(default=False)
    GOOGL = models.BooleanField(default=False)
    AAPL = models.BooleanField(default=False)
    GM = models.BooleanField(default=False)
    EUR = models.BooleanField(default=False)
    GBP = models.BooleanField(default=False)
    TRY = models.BooleanField(default=False)
    SPY = models.BooleanField(default=False)
    IVV = models.BooleanField(default=False)
    VTI = models.BooleanField(default=False)
    DJI = models.BooleanField(default=False)
    IXIC = models.BooleanField(default=False)
    INX = models.BooleanField(default=False)
