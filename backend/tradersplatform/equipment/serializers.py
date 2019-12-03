from rest_framework.serializers import ModelSerializer

from equipment.models import CryptoCurrencies, Metals, Stocks, Currencies, ETFs, ETFDetail, TraceIndices, ETFPrice, \
    ETFInformation


class CryptoCurrencySerializer(ModelSerializer):
    class Meta:
        model = CryptoCurrencies
        fields = '__all__'


class MetalsSerializer(ModelSerializer):
    class Meta:
        model = Metals
        fields = '__all__'


class StockSerializer(ModelSerializer):
    class Meta:
        model = Stocks
        fields = '__all__'


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
        model = ETFPrice
        fields = '__all__'


class TradeIndicesSerializer(ModelSerializer):
    class Meta:
        model = TraceIndices
        fields = '__all__'


class ETFMultSerializer(ModelSerializer):
    SPY = ETFDetailSerializer()
    IVV = ETFDetailSerializer()
    VTI = ETFDetailSerializer()

    class Meta:
        model = ETFs
        fields = '__all__'


class ETFInfoSerializer(ModelSerializer):

    class Meta:
        model = ETFInformation
        fields = '__all__'
