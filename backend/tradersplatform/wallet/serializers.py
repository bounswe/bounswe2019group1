from rest_framework.serializers import ModelSerializer

from wallet.models import Wallet


class WalletCreateSerializer(ModelSerializer):
    class Meta:
        model = Wallet
        fields = [
            "id",
            "owner"
        ]


class WalletListSerializer(ModelSerializer):
    class Meta:
        model = Wallet
        fields = '__all__'