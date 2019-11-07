from rest_framework.serializers import ModelSerializer

from equipment.models import CryptoCurrencies


class CryptoCurrencySerializer(ModelSerializer):
    class Meta:
        model = CryptoCurrencies
        fields = [
            "BTC",
            "ETH",
            "LTC"
        ]
