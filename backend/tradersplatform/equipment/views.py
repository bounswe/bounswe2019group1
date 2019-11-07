import requests
from django.shortcuts import render
import json
from rest_framework.response import Response

# Create your views here.
from rest_framework.generics import ListAPIView

from equipment.serializers import CryptoCurrencySerializer


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
