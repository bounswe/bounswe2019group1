from rest_framework.serializers import ModelSerializer

from myuser.serializers import TempUserListSerializer
from portfolio.models import Portfolio


class PortfolioSerializer(ModelSerializer):

    class Meta:
        model = Portfolio
        fields = '__all__'


class PortfolioListSerializer(ModelSerializer):
    owner=TempUserListSerializer()

    class Meta:
        model = Portfolio
        fields = [
            'id',
            'owner',
            'name',

        ]