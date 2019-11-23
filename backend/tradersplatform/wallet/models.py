from django.db import models

# Create your models here.
from myuser.models import TemplateUser


class Wallet(models.Model):
    owner = models.ForeignKey(TemplateUser, on_delete=models.CASCADE, default='',related_name='owner')
    USD = models.DecimalField(max_digits=20, decimal_places=10, blank=True, null=True, unique=False, default=0)
    Sent_USD = models.DecimalField(max_digits=20, decimal_places=10, blank=True, null=True, unique=False, default=0)
    Wealth_USD = models.DecimalField(max_digits=20, decimal_places=10, blank=True, null=True, unique=False, default=0)
    BTC = models.DecimalField(max_digits=20, decimal_places=10, blank=True, null=True, unique=False, default=0)
    ETH = models.DecimalField(max_digits=20, decimal_places=10, blank=True, null=True, unique=False, default=0)
    LTC = models.DecimalField(max_digits=20, decimal_places=10, blank=True, null=True, unique=False, default=0)
    XAG = models.DecimalField(max_digits=20, decimal_places=10, blank=True, null=True, unique=False, default=0)
    XAU = models.DecimalField(max_digits=20, decimal_places=10, blank=True, null=True, unique=False, default=0)
    GOOGL = models.DecimalField(max_digits=20, decimal_places=10, blank=True, null=True, unique=False, default=0)
    AAPL = models.DecimalField(max_digits=20, decimal_places=10, blank=True, null=True, unique=False, default=0)
    GM = models.DecimalField(max_digits=20, decimal_places=10, blank=True, null=True, unique=False, default=0)
    EUR = models.DecimalField(max_digits=20, decimal_places=10, blank=True, null=True, unique=False, default=0)
    GBP = models.DecimalField(max_digits=20, decimal_places=10, blank=True, null=True, unique=False, default=0)
    TRY = models.DecimalField(max_digits=20, decimal_places=10, blank=True, null=True, unique=False, default=0)
    SPY = models.DecimalField(max_digits=20, decimal_places=10, blank=True, null=True, unique=False, default=0)
    IVV = models.DecimalField(max_digits=20, decimal_places=10, blank=True, null=True, unique=False, default=0)
    VTI = models.DecimalField(max_digits=20, decimal_places=10, blank=True, null=True, unique=False, default=0)

