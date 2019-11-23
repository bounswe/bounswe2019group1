from django.db import models

# Create your models here.
from myuser.models import TemplateUser


class Wallet(models.Model):
    owner = models.ForeignKey(TemplateUser, on_delete=models.CASCADE, default='',related_name='owner')
    USD = models.DecimalField(max_digits=20,decimal_places=10, blank=True,null=True, unique=False)
    Sent_USD = models.DecimalField(max_digits=20,decimal_places=10, blank=True,null=True, unique=False)
    Wealth_USD = models.DecimalField(max_digits=20,decimal_places=10, blank=True,null=True, unique=False)
    BTC = models.DecimalField(max_digits=20,decimal_places=10, blank=True,null=True, unique=False)
    ETH = models.DecimalField(max_digits=20,decimal_places=10, blank=True,null=True, unique=False)
    LTC = models.DecimalField(max_digits=20,decimal_places=10, blank=True,null=True, unique=False)
    XAG = models.DecimalField(max_digits=20,decimal_places=10, blank=True,null=True, unique=False)
    XAU = models.DecimalField(max_digits=20,decimal_places=10, blank=True,null=True, unique=False)
    XRH = models.DecimalField(max_digits=20,decimal_places=10, blank=True,null=True, unique=False)
    GOOGL = models.DecimalField(max_digits=20,decimal_places=10, blank=True,null=True, unique=False)
    AAPL = models.DecimalField(max_digits=20,decimal_places=10, blank=True,null=True, unique=False)
    GM = models.DecimalField(max_digits=20,decimal_places=10, blank=True,null=True, unique=False)
    EUR = models.DecimalField(max_digits=20,decimal_places=10, blank=True,null=True, unique=False)
    GBP = models.DecimalField(max_digits=20,decimal_places=10, blank=True,null=True, unique=False)
    TRY = models.DecimalField(max_digits=20,decimal_places=10, blank=True,null=True, unique=False)
    SPY = models.DecimalField(max_digits=20,decimal_places=10, blank=True,null=True, unique=False)
    IVV = models.DecimalField(max_digits=20,decimal_places=10, blank=True,null=True, unique=False)
    VTI = models.DecimalField(max_digits=20,decimal_places=10, blank=True,null=True, unique=False)

