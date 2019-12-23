from rest_framework.serializers import ModelSerializer

from portfolio_follow.models import PortfolioFollow
from myuser.serializers import TempUserCreateSerializer
from portfolio.serializers import PortfolioListSerializer


class PortfolioFollowCreateSerializer(ModelSerializer):
    class Meta:
        model = PortfolioFollow
        fields = [
            "id",
            "follower",
            "portfolio"
        ]


class PortfolioFollowerListSerializer(ModelSerializer):

    follower = TempUserCreateSerializer()

    class Meta:
        model = PortfolioFollow
        fields = [
            "id",
            "follower",
            "portfolio"
        ]


class FollowingPortfolioListSerializer(ModelSerializer):

    portfolio = PortfolioListSerializer()

    class Meta:
        model = PortfolioFollow
        fields = [
            "id",
            "follower",
            "portfolio"
        ]


class FollowPortfolioListSerializer(ModelSerializer):

    follower = TempUserCreateSerializer()
    portfolio = PortfolioListSerializer()

    class Meta:
        model = PortfolioFollow
        fields = [
            "id",
            "follower",
            "portfolio"
        ]