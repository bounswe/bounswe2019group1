from rest_framework.serializers import ModelSerializer

from equipment.models import CryptoCurrencies, Metals, Stocks


class CryptoCurrencySerializer(ModelSerializer):
    class Meta:
        model = CryptoCurrencies
        fields = [
            "BTC",
            "ETH",
            "LTC"
        ]


class MetalsSerializer(ModelSerializer):
    class Meta:
        model = Metals
        fields = [
            "XAG",
            "XAU",
            "XRH"
        ]


class StockSerializer(ModelSerializer):
    class Meta:
        model = Stocks
        fields = [
            "GOOGL",
            "AAPL",
            "GM"
        ]
