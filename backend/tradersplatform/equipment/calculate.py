import requests
from django.shortcuts import render
import json
from rest_framework.response import Response
from datetime import datetime, timedelta
from collections import OrderedDict


from rest_framework.exceptions import ValidationError
# Create your views here.
from rest_framework.generics import ListAPIView
from datetime import datetime

from equipment.models import ETFDetail, ETFs, ETFPrice, ETFInformation, CryptoCurrencies
from equipment.serializers import CryptoCurrencySerializer, MetalsSerializer, StockSerializer, CurrencySerializer, \
    ETFDetailSerializer, ETFMultSerializer, TradeIndicesSerializer

from celery.schedules import crontab
from celery.task import periodic_task

from myuser.models import TemplateUser

from django_cron import CronJobBase, Schedule


def calculate_currency():
    '''
    find this request in
    https://www.exchangerate-api.com sign up and read the documentation
    :param request:
    :param args:
    :param kwargs:
    :return:
    '''
    url = 'https://api.exchangerate-api.com/v4/latest/USD'
    headers = {}
    response = requests.request('GET', url, headers=headers, allow_redirects=False)
    ret = json.loads(response.text)
    # my_scheduled_job()
    ser = ret['rates']
    created_time = datetime.now()
    ser['changed_time']=created_time
    serializer = CurrencySerializer(data=ser)
    serializer.is_valid(raise_exception=True)
    serializer.save()


def calculate_cryptocurrency():
    '''
    find this request in
    https://coinlayer.com/ sign up and read the documentation
    :param request:
    :param args:
    :param kwargs:
    :return:
    '''
    url = 'https://min-api.cryptocompare.com/data/pricemulti?fsyms=BTC,ETH,LTC&tsyms=USD'
    headers = {}
    response = requests.request('GET', url, headers=headers, allow_redirects=False)
    ret = json.loads(response.content)
    created_time = datetime.now()
    new_crypto=CryptoCurrencies(
        BTC=ret['BTC']['USD'],
        ETH=ret['ETH']['USD'],
        LTC=ret['LTC']['USD'],
        changed_time=created_time,
    )
    new_crypto.save()


def calculate_metal_currency():
    '''
    find this request in
    https://metals-api.com/ sign up and read the documentation
    :param request:
    :param args:
    :param kwargs:
    :return:
    '''
    url = 'https://metals-api.com/api/latest?access_key=u7dd7xonqypenlp0680ycft68e2li9z5l7v8lnkp5882bv8a34hg54k70jv90862'
    headers = {}
    response = requests.request('GET', url, headers=headers, allow_redirects=False)
    a = response.content
    ret = json.loads(a)
    data_temp = ret.get('rates', None)
    created_time = datetime.now()
    data_temp['changed_time'] = created_time
    serializer = MetalsSerializer(data=data_temp)
    serializer.is_valid(raise_exception=True)
    serializer.save()


def calculate_stock_currency():
    '''
    find this request in
    https://financialmodelingprep.com/developer/docs/ read the documentation
    '''
    url = 'https://financialmodelingprep.com/api/v3/stock/real-time-price'
    headers = {}
    response = requests.request('GET', url, headers=headers, allow_redirects=False)
    a = response.content
    ret = json.loads(a)
    data_temp = ret.get('stockList', None)
    ret = list(filter(lambda stock: stock['symbol'] == 'AAPL' or stock['symbol'] == 'GOOGL' or stock['symbol'] == 'GM',
                      data_temp))
    dict = {}
    for i in range(0, len(ret)):
        dict[ret[i]['symbol']] = ret[i]['price']
    created_time = datetime.now()
    dict['changed_time'] = created_time
    serializer = StockSerializer(data=dict)
    serializer.is_valid(raise_exception=True)
    serializer.save()


def calculate_trace_indices():
    '''
    find this request in
    https://financialmodelingprep.com/developer/docs/#Most-of-the-majors-indexes-(Dow-Jones,-Nasdaq,-S&P-500) read the documentation
    :param request:
    :param args:
    :param kwargs:
    :return:
    '''
    url = 'https://financialmodelingprep.com/api/v3/majors-indexes'
    headers = {}
    response = requests.request('GET', url, headers=headers, allow_redirects=False)
    a = response.content
    ret = json.loads(a)
    arr = ret['majorIndexesList']
    dict = {}
    for i in range(0, len(arr)):
        dict[arr[i]['ticker'][1:]] = arr[i]['price']
    created_time = datetime.now()
    dict['changed_time'] = created_time
    serializer = TradeIndicesSerializer(data=dict)
    serializer.is_valid(raise_exception=True)
    serializer.save()


def calculate_etf():
    '''
    find this request in
    I find this with examining a node js method and look its backend
    '''
    url = 'https://etfdb.com/api/screener/'
    payload = "{\n\t\"page\": 1,\n\t\"perPage\": 5\n}"
    headers = {
        'Content-Type': 'application/json'
    }
    boo=True
    iterate_count=0
    while(boo):
        response = requests.request('POST', url, headers=headers, data=payload, allow_redirects=False)
        status_code=response.status_code
        if status_code==200 or iterate_count > 10:
            boo=False
        iterate_count+=1
        print("")
    if iterate_count < 10:
        a = response.content
        ret = json.loads(a)
        data = ret['data']
        created_time = datetime.now()
        for i in range(0, 3):
            curr = ETFPrice.objects.filter(id=i + 1).first()
            if not curr:
                serializer = ETFDetailSerializer(data=data[i])
                serializer.is_valid(raise_exception=True)
                serializer.save()
            else:
                serializer = ETFDetailSerializer(curr, data=data[i])
                serializer.is_valid(raise_exception=True)
                serializer.save()
        new_EFT = ETFInformation(
            SPY=ETFPrice.objects.get(id=1).price,
            IVV=ETFPrice.objects.get(id=2).price,
            VTI=ETFPrice.objects.get(id=3).price,
            changed_time=created_time,
        )
        new_EFT.save()