from django.db import models

# Create your models here.
from myuser.models import TemplateUser


class Prediction(models.Model):
    tradingEquipment = models.CharField(max_length=10, unique=True, blank=False)
    user = models.ForeignKey(TemplateUser, on_delete=models.CASCADE, blank=False, related_name="user_predict")
    is_Rising = models.BooleanField(default=True)
    is_valid = models.BooleanField(default=False)
    has_validate = models.BooleanField(default=False)