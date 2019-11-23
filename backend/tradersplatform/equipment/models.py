from django.db import models


# Create your models here


class CryptoCurrencies(models.Model):
    BTC = models.DecimalField(max_digits=20,decimal_places=10, blank=True,null=True, unique=False)
    ETH = models.DecimalField(max_digits=20,decimal_places=10, blank=True,null=True, unique=False)
    LTC = models.DecimalField(max_digits=20,decimal_places=10, blank=True,null=True, unique=False)


class Metals(models.Model):
    XAG = models.CharField(max_length=300, blank=True,null=True, unique=False)
    XAU = models.CharField(max_length=300, blank=False, null=False, unique=False)
    XRH = models.CharField(max_length=300, blank=False, null=False, unique=False)


class Stocks(models.Model):
    GOOGL = models.DecimalField(max_digits=20,decimal_places=10, blank=True,null=True, unique=False)
    AAPL = models.DecimalField(max_digits=20,decimal_places=10, blank=True,null=True,  unique=False)
    GM = models.DecimalField(max_digits=20,decimal_places=10, blank=True,null=True, unique=False)


class Currencies(models.Model):
    EUR = models.CharField(max_length=300, blank=True,null=True, unique=False)
    GBP = models.CharField(max_length=300, blank=False, null=False, unique=False)
    TRY = models.CharField(max_length=300, blank=False, null=False, unique=False)


class ETFDetail(models.Model):
    mobile_title = models.CharField(max_length=800, blank=True, null=True, unique=False)
    price = models.CharField(max_length=800, blank=True, null=True, unique=False)
    assets = models.CharField(max_length=800, blank=True, null=True, unique=False)
    average_volume = models.CharField(max_length=800, blank=True, null=True, unique=False)
    ytd = models.CharField(max_length=800, blank=True, null=True, unique=False)


class ETFs(models.Model):
    SPY = models.ForeignKey(ETFDetail, on_delete=models.CASCADE, default='',related_name='SPY')
    IVV = models.ForeignKey(ETFDetail, on_delete=models.CASCADE, default='',related_name='IVV')
    VTI = models.ForeignKey(ETFDetail, on_delete=models.CASCADE, default='',related_name='VTI')
