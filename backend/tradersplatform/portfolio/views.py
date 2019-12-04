from django.shortcuts import render

# Create your views here.
from rest_framework.generics import CreateAPIView, ListAPIView
from rest_framework.views import APIView

from follow.views import check_if_user
from myuser.models import TemplateUser
from portfolio.models import Portfolio
from portfolio.serializers import PortfolioSerializer, PortfolioListSerializer
from rest_framework.response import Response
from rest_framework.exceptions import ValidationError
from django.apps import apps


class CreatePortfolioAPIView(CreateAPIView):

    def post(self, request, *args, **kwargs):
        check_if_user(request)
        id=request.user.id
        user=TemplateUser.objects.get(id=id)
        request_data = request.data
        request_data['owner']=user
        serializer=PortfolioSerializer(data=request_data)
        serializer.is_valid(raise_exception=True)
        serializer.save()
        return Response(serializer.data, status=200)


class RetrievePortfolioAPIView(APIView):

    def get(self, request, *args, **kwargs):
        id = kwargs.get("pk")
        #user_id = request.user.id
        portfolio=Portfolio.objects.filter(id=id).first()
        if not portfolio:
            raise ValidationError({"detail": "This portfolio does not exist"})
        list_currencies = ['BTC', 'LTC', 'ETH', 'XAG', 'XAU', 'GOOGL', 'AAPL', 'GM', 'EUR', 'GBP', 'TRY', 'DJI', 'IXIC',
                           'INX', 'SPY', 'IVV', 'VTI']
        dict={}
        for i in range(0, len(list_currencies)):
            if getattr(portfolio, list_currencies[i]):
                if i < 3:
                    model = 'CryptoCurrencies'
                elif i < 5:
                    model = 'Metals'
                elif i < 8:
                    model = 'Stocks'
                elif i < 11:
                    model = 'Currencies'
                elif i < 14:
                    model = 'TraceIndices'
                elif i < 17:
                    model = 'ETFInformation'
                equipment = apps.get_model("equipment", model)
                last = equipment.objects.all().last()
                dict[list_currencies[i]]=getattr(last, list_currencies[i])
        return Response(dict,status=200)


class ListPortfolioAPIView(ListAPIView):
    serializer_class = PortfolioListSerializer

    def get_queryset(self):
        return Portfolio.objects.filter(owner__id=self.kwargs.get("pk"))