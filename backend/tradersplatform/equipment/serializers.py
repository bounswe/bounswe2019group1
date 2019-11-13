from rest_framework.serializers import ModelSerializer

from equipment.models import CryptoCurrencies, Metals, Stocks, Currencies, ETFs, ETFDetail


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


class CurrencySerializer(ModelSerializer):
    class Meta:
        model = Currencies
        fields = '__all__'


class ETFSerializer(ModelSerializer):
    class Meta:
        model = ETFs
        fields = '__all__'


class ETFDetailSerializer(ModelSerializer):
    class Meta:
        model = ETFDetail
        fields = '__all__'


class ETFMultSerializer(ModelSerializer):
    SPY = ETFDetailSerializer()
    IVV = ETFDetailSerializer()
    VTI = ETFDetailSerializer()

    class Meta:
        model = ETFs
        fields = '__all__'