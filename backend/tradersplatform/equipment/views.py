import requests
from django.shortcuts import render
import json
from rest_framework.response import Response
import datetime


# Create your views here.
from rest_framework.generics import ListAPIView

from equipment.serializers import CryptoCurrencySerializer, MetalsSerializer, StockSerializer


class CurrencyAPI(ListAPIView):

    def get(self, request, *args, **kwargs):
        '''
        find this request in
        https://fixer.io sign up and read the documentation
        :param request:
        :param args:
        :param kwargs:
        :return:
        '''
        url = 'http://data.fixer.io/api/latest?access_key=1c569a006dc128d15b612d8d1ac04f96'
        headers = {}
        response = requests.request('GET', url, headers=headers, allow_redirects=False)
        ret=json.loads(response.text)
        return Response(ret, 200)


class CryptoCurrencyAPI(ListAPIView):

    def get(self, request, *args, **kwargs):
        '''
        find this request in
        https://coinlayer.com/ sign up and read the documentation
        :param request:
        :param args:
        :param kwargs:
        :return:
        '''
        url = 'http://api.coinlayer.com/api/live?access_key=9fdc61fa75c3cfce8e1e5fd50f362113'
        headers = {}
        response = requests.request('GET', url, headers=headers, allow_redirects=False)
        ret=json.loads(response.text)
        data_temp = ret.get('rates', None)
        serializer=CryptoCurrencySerializer(data=data_temp)
        serializer.is_valid(raise_exception=True)
        return Response(serializer.data, 200)


class MetalCurrencyAPI(ListAPIView):

    def get(self, request, *args, **kwargs):
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
        a=response.content
        ret = json.loads(a)
        data_temp = ret.get('rates', None)
        serializer=MetalsSerializer(data=data_temp)
        serializer.is_valid(raise_exception=True)
        return Response(serializer.data, 200)


class StockCurrencyAPI(ListAPIView):

    def get(self, request, *args, **kwargs):
        '''
        find this request in
        https://financialmodelingprep.com/developer/docs/ read the documentation
        :param request:
        :param args:
        :param kwargs:
        :return:
        '''
        url = 'https://financialmodelingprep.com/api/v3/stock/real-time-price'
        headers = {}
        response = requests.request('GET', url, headers=headers, allow_redirects=False)
        a=response.content
        ret = json.loads(a)
        data_temp = ret.get('stockList', None)
        ret=list(filter(lambda stock: stock['symbol'] == 'AAPL' or stock['symbol'] == 'GOOGL' or stock['symbol'] == 'GM', data_temp))
        return Response(ret, 200)


class TraceIndices(ListAPIView):

    def get(self, request, *args, **kwargs):
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
        a=response.content
        ret = json.loads(a)
        return Response(ret, 200)


class ETFsListAPIView(ListAPIView):

    def get(self, request, *args, **kwargs):
        '''
        find this request in
        https://www.quandl.com/databases/ETFG/documentation read the documentation
        :param request:
        :param args:
        :param kwargs:
        :return:
        '''
        url = 'https://www.quandl.com/api/v3/datatables/ETFG/FUND?api_key=foyqf3jX2rBjEsXsCgRX'
        headers = {}
        response = requests.request('GET', url, headers=headers, allow_redirects=False)
        a=response.content
        ret = json.loads(a)
        return Response(ret, 200)


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
        '''
        url = 'https://www.quandl.com/api/v3/datatables/CHORD7/BD?api_key=foyqf3jX2rBjEsXsCgRX'
        headers = {}
        response = requests.request('GET', url, headers=headers, allow_redirects=False)
        a=response.content
        ret = json.loads(a)
        return Response(ret, 200)

