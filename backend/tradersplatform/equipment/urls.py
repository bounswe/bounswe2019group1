from django.conf.urls import url

from . import views


urlpatterns = [
    url(r'^currency/', views.CurrencyAPI.as_view(), name="update_de"),
    url(r'^currencylastmonth/', views.CurrencyAPILastMonth.as_view(), name="update_de"),
    url(r'^currencyconvert/', views.CurrencyConverterAPI.as_view(), name="update_de"),
    url(r'^cryptocurrency/', views.CryptoCurrencyAPI.as_view(), name="update_de"),
    url(r'^cryptocurrencyhistorical/', views.CryptoCurrencyHistoricalAPI.as_view(), name="update_de"),
    url(r'^metalcurrency/', views.MetalCurrencyAPI.as_view(), name="update_de"),
    url(r'^stock/', views.StockCurrencyAPI.as_view(), name="update_de"),
    url(r'^traceindices/', views.TraceIndicesAPIView.as_view(), name="update_de"),
    url(r'^traceindicesgainers/', views.TraceIndicesGainers.as_view(), name="update_de"),
    url(r'^etfs/', views.ETFsListAPIView.as_view(), name="update_de"),
    url(r'^bonds/', views.BondListAPIView.as_view(), name="update_de"),
    url(r'^lastmonth/', views.StockLastMonth.as_view(), name="update_de"),
    url(r'^etfdetaillist/', views.ETFDeatilistAPIView.as_view(), name="update_de"),
    url(r'^currencyList/', views.CurrencyList.as_view(), name="update_de"),
    url(r'^cryptocurrencyList/', views.CryptoCurrencyList.as_view(), name="update_de"),
    url(r'^metalcurrencyList/', views.MetalListAPIView.as_view(), name="update_de"),
    url(r'^stockcurrencyList/', views.StockListAPIView.as_view(), name="update_de"),
    url(r'^etfList/', views.ETFListAPIView.as_view(), name="update_de"),
    url(r'^traceList/', views.ETFListAPIView.as_view(), name="update_de"),
]