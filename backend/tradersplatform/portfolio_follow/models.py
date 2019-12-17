from django.core.validators import MinValueValidator, MaxValueValidator
from django.db import models
from django.contrib.auth.models import User

# Create your models here.

from myuser.models import TemplateUser
from portfolio.models import Portfolio


class PortfolioFollow(models.Model):
    follower = models.ForeignKey(TemplateUser, on_delete=models.CASCADE, default='', related_name='portfolio_follower')
    portfolio = models.ForeignKey(Portfolio, on_delete=models.CASCADE, default='', related_name='following_portfolio')
