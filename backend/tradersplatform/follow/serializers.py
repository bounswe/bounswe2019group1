from rest_framework.serializers import ModelSerializer

from follow.models import Follow
from myuser.serializers import TempUserCreateSerializer


class FollowCreateSerializer(ModelSerializer):
    class Meta:
        model = Follow
        fields = [
            "id",
            "follower",
            "following",
            "is_active"
        ]


class FollowerListSerializer(ModelSerializer):

    follower =TempUserCreateSerializer()

    class Meta:
        model = Follow
        fields = [
            "id",
            "follower",
            "following",
            "is_active"
        ]


class FollowingListSerializer(ModelSerializer):

    following =TempUserCreateSerializer()

    class Meta:
        model = Follow
        fields = [
            "id",
            "follower",
            "following",
            "is_active"
        ]
