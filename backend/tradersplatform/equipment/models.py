from django.db import models


# Create your models here


class CryptoCurrencies(models.Model):
    BTC = models.DecimalField(max_digits=20,decimal_places=10, blank=True,null=True, unique=False)
    ETH = models.DecimalField(max_digits=20,decimal_places=10, blank=True,null=True, unique=False)
    LTC = models.DecimalField(max_digits=20,decimal_places=10, blank=True,null=True, unique=False)


class Metals(models.Model):
    XAG = models.DecimalField(max_digits=30,decimal_places=20, blank=True,null=True, unique=False)
    XAU = models.DecimalField(max_digits=30,decimal_places=20, blank=True,null=True, unique=False)


class Stocks(models.Model):
    GOOGL = models.DecimalField(max_digits=20,decimal_places=10, blank=True,null=True, unique=False)
    AAPL = models.DecimalField(max_digits=20,decimal_places=10, blank=True,null=True,  unique=False)
    GM = models.DecimalField(max_digits=20,decimal_places=10, blank=True,null=True, unique=False)


class Currencies(models.Model):
    EUR = models.DecimalField(max_digits=20,decimal_places=10, blank=True,null=True, unique=False)
    GBP = models.DecimalField(max_digits=20,decimal_places=10, blank=True,null=True, unique=False)
    TRY = models.DecimalField(max_digits=20,decimal_places=10, blank=True,null=True, unique=False)


class ETFDetail(models.Model):
    mobile_title = models.CharField(max_length=800, blank=True, null=True, unique=False)
    #price = models.CharField(max_length=800, blank=True, null=True, unique=False)
    assets = models.CharField(max_length=800, blank=True, null=True, unique=False)
    average_volume = models.CharField(max_length=800, blank=True, null=True, unique=False)
    ytd = models.CharField(max_length=800, blank=True, null=True, unique=False)


class ETFPrice(models.Model):
    price = models.CharField(max_length=800, blank=True, null=True, unique=False)
    assets = models.CharField(max_length=800, blank=True, null=True, unique=False)

class ETFs(models.Model):
    SPY = models.ForeignKey(ETFPrice, on_delete=models.CASCADE, default='',related_name='SPY')
    IVV = models.ForeignKey(ETFPrice, on_delete=models.CASCADE, default='',related_name='IVV')
    VTI = models.ForeignKey(ETFPrice, on_delete=models.CASCADE, default='',related_name='VTI')


class TraceIndices(models.Model):
    DJI = models.DecimalField(max_digits=20,decimal_places=10, blank=True,null=True, unique=False)
    IXIC = models.DecimalField(max_digits=20,decimal_places=10, blank=True,null=True, unique=False)
    INX = models.DecimalField(max_digits=20,decimal_places=10, blank=True,null=True, unique=False)

