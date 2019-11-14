from django.conf.urls import url

from . import views


urlpatterns = [
    url(r'^currency/', views.CurrencyAPI.as_view(), name="update_de"),
    url(r'^currencyconvert/', views.CurrencyConverterAPI.as_view(), name="update_de"),
    url(r'^cryptocurrency/', views.CryptoCurrencyAPI.as_view(), name="update_de"),
    url(r'^metalcurrency/', views.MetalCurrencyAPI.as_view(), name="update_de"),
    url(r'^stock/', views.StockCurrencyAPI.as_view(), name="update_de"),
    url(r'^traceindices/', views.TraceIndices.as_view(), name="update_de"),
    url(r'^traceindicesgainers/', views.TraceIndicesGainers.as_view(), name="update_de"),
    url(r'^etfs/', views.ETFsListAPIView.as_view(), name="update_de"),
    url(r'^bonds/', views.BondListAPIView.as_view(), name="update_de"),
    url(r'^lastmonth/', views.StockLastMonth.as_view(), name="update_de"),
]