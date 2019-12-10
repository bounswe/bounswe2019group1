import django.utils.timezone
from rest_framework.serializers import ModelSerializer

from prediction.models import Prediction


class PredictionSerializer(ModelSerializer):

    class Meta:
        model = Prediction
        fields = [
            'tradingEquipment',
            'user',
            'is_Rising'
        ]