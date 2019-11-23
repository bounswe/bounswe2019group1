import decimal

from django.shortcuts import render

# Create your views here.
from rest_framework.generics import CreateAPIView, ListAPIView, UpdateAPIView

from follow.views import check_if_user
from wallet.models import Wallet
from rest_framework.exceptions import ValidationError
from rest_framework.response import Response
from wallet.serializers import WalletCreateSerializer, WalletListSerializer


class WalletCreateAPIView(CreateAPIView):

    def post(self, request, *args, **kwargs):
        prev=Wallet.objects.filter(owner=request.user.id)
        if prev:
            raise ValidationError({"detail": "You have wallet already"})
        dict={"owner":request.user.id}
        serializer=WalletCreateSerializer(data=dict)
        serializer.is_valid(raise_exception=True)
        serializer.save()
        return Response(serializer.data, status=200)


class WalletListAPIView(ListAPIView):
    serializer_class = WalletListSerializer
    queryset = Wallet.objects.all()


class SendUSDAPIView(UpdateAPIView):

    def put(self, request, *args, **kwargs):
        check_if_user(request)
        curr_wallet = Wallet.objects.filter(owner=request.user.id).first()
        if not curr_wallet:
            raise ValidationError({"detail": "You don't have wallet"})
        dollar=request.data.get("USD",None)
        if dollar is None:
            raise ValidationError({"detail": "Give the amount of USD"})
        is_float=isinstance(dollar, float) or isinstance(dollar, int)
        if not is_float:
            raise ValidationError({"detail": "Amount of USD must be in float or int form"})
        curr_wallet.USD=curr_wallet.USD+decimal.Decimal(dollar)
        curr_wallet.Sent_USD=curr_wallet.Sent_USD+decimal.Decimal(dollar)
        curr_wallet.save()
        serializer=WalletListSerializer(curr_wallet)
        return Response(serializer.data, status=200)


class PurchaseEquipmentAPIView(UpdateAPIView):

    def put(self, request, *args, **kwargs):
        check_if_user(request)
        curr_wallet = Wallet.objects.filter(owner=request.user.id).first()
        if not curr_wallet:
            raise ValidationError({"detail": "You don't have wallet"})





