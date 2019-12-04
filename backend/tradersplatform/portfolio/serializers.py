from rest_framework.serializers import ModelSerializer

from portfolio.models import Portfolio


class PortfolioSerializer(ModelSerializer):

    class Meta:
        model = Portfolio
        fields = '__all__'