from __future__ import absolute_import, unicode_literals
import requests
from django.shortcuts import render
import json
from rest_framework.response import Response
from datetime import datetime, timedelta
from collections import OrderedDict


from rest_framework.exceptions import ValidationError
# Create your views here.
from rest_framework.generics import ListAPIView, RetrieveAPIView
from datetime import datetime

from rest_framework.views import APIView

from equipment.calculate import calculate_etf, calculate_cryptocurrency, calculate_metal_currency, \
    calculate_stock_currency, calculate_trace_indices
from equipment.models import ETFDetail, ETFs, ETFPrice, Currencies, CryptoCurrencies, Metals, Stocks, TraceIndices, \
    ETFInformation
from equipment.serializers import CryptoCurrencySerializer, MetalsSerializer, StockSerializer, CurrencySerializer, \
    ETFDetailSerializer, ETFMultSerializer, TradeIndicesSerializer, ETFInfoSerializer

from celery.schedules import crontab
from celery.task import periodic_task
from celery import shared_task

from myuser.models import TemplateUser


'''from django_cron import CronJobBase, Schedule


from celery import task
'''
'''@task()
def task_number_one():
    user = TemplateUser.objects.get(id=1)
    user.username = "sonuncu deneme"
    user.save()
    print("donee")

class MyCronJob(CronJobBase):
    RUN_EVERY_MINS = 120 # every 2 hours

    schedule = Schedule(run_every_mins=RUN_EVERY_MINS)
    code = 'my_app.my_cron_job'    # a unique code

    def do(self):
        pass    # do your thing here

@shared_task(run_every=timedelta(seconds=30))
def every_30_seconds():
    print("Running periodic task!")


@shared_task(run_every=timedelta(seconds=1))
def every_monday_morning():
    user = TemplateUser.objects.get(id=1)
    user.username = "user.username + '2'"
    user.save()
    print("donee")'''





def my_scheduled_job():
    user=TemplateUser.objects.get(id=2)
    user.username=user.username+"1"
    user.save()
    print("donee")


class CurrencyAPI(APIView):
    serializer_class = CurrencySerializer

    def get(self, request, *args, **kwargs):
        serializer=CurrencySerializer(Currencies.objects.last())
        return Response(serializer.data, 200)

'''    def get(self, request, *args, **kwargs):
        
        find this request in
        https://www.exchangerate-api.com sign up and read the documentation
        :param request:
        :param args:
        :param kwargs:
        :return:
        
        url = 'https://api.exchangerate-api.com/v4/latest/USD'
        headers = {}
        response = requests.request('GET', url, headers=headers, allow_redirects=False)
        ret=json.loads(response.text)
        my_scheduled_job()
        ser=ret['rates']
        serializer=CurrencySerializer(data=ser)
        serializer.is_valid(raise_exception=True)
        serializer.save()
        return Response(serializer.data, 200)'''


class CurrencyAPILastMonth(ListAPIView):

    def get(self, request, *args, **kwargs):
        '''
        find this request in
        https://www.exchangerate-api.com sign up and read the documentation
        :param request:
        :param args:
        :param kwargs:
        :return:
        '''
        start_date = datetime.now() - timedelta(days=30)
        print(datetime.now())
        one_month=start_date.strftime('%Y-%m-%d')
        today=datetime.today().strftime('%Y-%m-%d')
        url = 'https://api.exchangeratesapi.io/history?start_at='+one_month+'&end_at='+today+'&symbols=EUR,GBP,TRY&base=USD'
        headers = {}
        response = requests.request('GET', url, headers=headers, allow_redirects=False)
        ret=json.loads(response.text)
        ser = ret['rates']
        ser=OrderedDict(sorted(ser.items(), key=lambda t: t[0]))
        return Response(ser, 200)


class CurrencyConverterAPI(ListAPIView):

    def get(self, request, *args, **kwargs):
        '''
        find this request in
        https://ratesapi.io/documentation/ read the documentation
        :param request:
        :param args:
        :param kwargs:
        :return:
        '''
        curr_from = request.data.get('from', None)
        curr_to = request.data.get('to', None)
        if curr_from is None or curr_to is None:
            raise ValidationError({"detail": "You have to give currency symbol"})
        url = 'https://api.ratesapi.io/api/latest?base='+curr_from+'&symbols='+curr_to
        headers = {}
        response = requests.request('GET', url, headers=headers, allow_redirects=False)
        ret=json.loads(response.text)
        return Response(ret, 200)


class CryptoCurrencyAPI(APIView):

    def get(self, request, *args, **kwargs):
        serializer=CryptoCurrencySerializer(CryptoCurrencies.objects.last())
        return Response(serializer.data, 200)


class CryptoCurrencyHistoricalAPI(ListAPIView):

    def get(self, request, *args, **kwargs):
        '''
        find this request in
        https://coinlayer.com/ sign up and read the documentation
        :param request:
        :param args:
        :param kwargs:
        :return:
        '''
        url = 'https://min-api.cryptocompare.com/data/v2/histoday?fsym=ETH&tsym=USD&limit=30'
        headers = {}
        response = requests.request('GET', url, headers=headers, allow_redirects=False)
        ret = json.loads(response.text)
        data=ret['Data']['Data']
        ret_arr_eth=[]
        for i in range (0,30):
            ret_arr_eth.append(data[i]['close'])
        url = 'https://min-api.cryptocompare.com/data/v2/histoday?fsym=LTC&tsym=USD&limit=30'
        headers = {}
        response = requests.request('GET', url, headers=headers, allow_redirects=False)
        ret = json.loads(response.text)
        data=ret['Data']['Data']
        ret_arr_ltc=[]
        for i in range (0,30):
            ret_arr_ltc.append(data[i]['close'])
        url = 'https://min-api.cryptocompare.com/data/v2/histoday?fsym=BTC&tsym=USD&limit=30'
        headers = {}
        response = requests.request('GET', url, headers=headers, allow_redirects=False)
        ret = json.loads(response.text)
        data = ret['Data']['Data']
        ret_arr_btc = []
        for i in range(0, 30):
            ret_arr_btc.append(data[i]['close'])

        ret_dict={'LTC':ret_arr_ltc,'BTC':ret_arr_btc,'ETH':ret_arr_eth}
        return Response(ret_dict, 200)


class MetalCurrencyAPI(APIView):

    def get(self, request, *args, **kwargs):
        serializer=MetalsSerializer(Metals.objects.last())
        return Response(serializer.data, 200)


class StockCurrencyAPI(APIView):

    def get(self, request, *args, **kwargs):
        serializer=StockSerializer(Stocks.objects.last())
        return Response(serializer.data, 200)


class StockLastMonth(ListAPIView):

    def get(self, request, *args, **kwargs):
        '''
        find this request in
        https://financialmodelingprep.com/developer/docs/ read the documentation
        :param request:
        :param args:
        :param kwargs:
        :return:
        '''
        start_date = datetime.now() - timedelta(days=30)
        one_month=start_date.strftime('%Y-%m-%d')
        today=datetime.today().strftime('%Y-%m-%d')
        comp=request.data.get('company_symbol',None)
        if comp is None:
            raise ValidationError({"detail":"You have to give company symbol"})
        url = 'https://financialmodelingprep.com/api/v3/historical-price-full/'+comp+'?from='+one_month+'&to='+today
        headers = {}
        response = requests.request('GET', url, headers=headers, allow_redirects=False)
        a=response.content
        ret = json.loads(a)
        return Response(ret, 200)


class TraceIndicesAPIView(APIView):

    def get(self, request, *args, **kwargs):
        serializer=TradeIndicesSerializer(TraceIndices.objects.last())
        return Response(serializer.data, 200)


class TraceIndicesGainers(ListAPIView):

    def get(self, request, *args, **kwargs):
        '''
        find this request in
        https://financialmodelingprep.com/developer/docs/#Most-of-the-majors-indexes-(Dow-Jones,-Nasdaq,-S&P-500) read the documentation
        :param request:
        :param args:
        :param kwargs:
        :return:
        '''
        url = 'https://financialmodelingprep.com/api/v3/stock/gainers'
        headers = {}
        response = requests.request('GET', url, headers=headers, allow_redirects=False)
        a=response.content
        ret = json.loads(a)
        return Response(ret, 200)


class ETFsListAPIView(ListAPIView):

    def get(self, request, *args, **kwargs):
        serializer=ETFInfoSerializer(ETFInformation.objects.last())
        return Response(serializer.data, 200)


class BondListAPIView(ListAPIView):

    def get(self, request, *args, **kwargs):
        '''
        find this request in
        It seems not quite what requested will be discussed in meeting
        https://www.quandl.com/databases/BD/documentation read the documentation
        :param request:
        :param args:
        :param kwargs:
        :return:
        eod api 5dc9331f614e72.22510470
        '''
        url = 'https://www.quandl.com/api/v3/datatables/CHORD7/BD?api_key=foyqf3jX2rBjEsXsCgRX'
        headers = {}
        response = requests.request('GET', url, headers=headers, allow_redirects=False)
        a=response.content
        ret = json.loads(a)
        return Response(ret, 200)


class ETFDeatilistAPIView(ListAPIView):
    serializer_class = ETFDetailSerializer
    queryset = ETFPrice.objects.filter()


class CurrencyList(ListAPIView):
    serializer_class = CurrencySerializer
    queryset = Currencies.objects.all()


class CryptoCurrencyList(ListAPIView):
    serializer_class = CryptoCurrencySerializer
    queryset = CryptoCurrencies.objects.all()


class MetalListAPIView(ListAPIView):
    serializer_class = MetalsSerializer
    queryset = Metals.objects.all()


class StockListAPIView(ListAPIView):
    serializer_class = StockSerializer
    queryset = Stocks.objects.all()


class ETFListAPIView(ListAPIView):
    serializer_class = ETFInfoSerializer
    queryset = ETFInformation.objects.all()


class TraceListAPIView(ListAPIView):
    serializer_class = TradeIndicesSerializer
    queryset = TraceIndices.objects.all()

