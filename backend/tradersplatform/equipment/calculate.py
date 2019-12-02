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

from equipment.models import ETFDetail, ETFs, ETFPrice
from equipment.serializers import CryptoCurrencySerializer, MetalsSerializer, StockSerializer, CurrencySerializer, \
    ETFDetailSerializer, ETFMultSerializer, TradeIndicesSerializer

from celery.schedules import crontab
from celery.task import periodic_task

from myuser.models import TemplateUser

from django_cron import CronJobBase, Schedule


def calculate_currency():
    url = 'https://api.exchangerate-api.com/v4/latest/USD'
    headers = {}
    response = requests.request('GET', url, headers=headers, allow_redirects=False)
    ret = json.loads(response.text)
    # my_scheduled_job()
    ser = ret['rates']
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
    print("aaaaaa")
    url = 'http://api.coinlayer.com/api/live?access_key=9fdc61fa75c3cfce8e1e5fd50f362113'
    headers = {}
    response = requests.request('GET', url, headers=headers, allow_redirects=False)
    ret = json.loads(response.text)
    data_temp = ret.get('rates', None)
    serializer = CryptoCurrencySerializer(data=data_temp)
    serializer.is_valid(raise_exception=True)
    serializer.save()


def calculate_metal_currency():
    '''
    find this request in
    https://metals-api.com/ sign up and read the documentation
    :param request:
    :param args:
    :param kwargs:
    :return:
    '''
    url = 'https://metals-api.com/api/latest?access_key=vy1akt4bparc3chths20zo329vl86n5t1pcjka35ix2xlq1scvr9nevstu19u1hy'
    headers = {}
    response = requests.request('GET', url, headers=headers, allow_redirects=False)
    a = response.content
    ret = json.loads(a)
    data_temp = ret.get('rates', None)
    serializer = MetalsSerializer(data=data_temp)
    serializer.is_valid(raise_exception=True)
    serializer.save()


def calculate_stock_currency():
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
    serializer = StockSerializer(data=dict)
    serializer.is_valid(raise_exception=True)
    serializer.save()


def calculate_trace_indices():
    url = 'https://financialmodelingprep.com/api/v3/majors-indexes'
    headers = {}
    response = requests.request('GET', url, headers=headers, allow_redirects=False)
    a = response.content
    ret = json.loads(a)
    arr = ret['majorIndexesList']
    dict = {}
    for i in range(0, len(arr)):
        dict[arr[i]['ticker'][1:]] = arr[i]['price']
    serializer = TradeIndicesSerializer(data=dict)
    serializer.is_valid(raise_exception=True)
    serializer.save()


def calculate_etf():
    url = 'https://etfdb.com/api/screener/'
    payload = "{\n\t\"page\": 1,\n\t\"perPage\": 5\n}"
    headers = {
        'Content-Type': 'application/json'
    }
    response = requests.request('POST', url, headers=headers, data=payload, allow_redirects=False)
    a = response.content
    ret = json.loads(a)
    data = ret['data']
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
    new_EFT = ETFs(
        SPY=ETFPrice.objects.get(id=1),
        IVV=ETFPrice.objects.get(id=2),
        VTI=ETFPrice.objects.get(id=3),
    )
    new_EFT.save()