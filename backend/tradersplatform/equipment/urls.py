from django.conf.urls import url

from . import views


urlpatterns = [
    url(r'^currency/', views.CurrencyAPI.as_view(), name="update_de"),
    url(r'^cryptocurrency/', views.CryptoCurrencyAPI.as_view(), name="update_de"),
    url(r'^metalcurrency/', views.MetalCurrencyAPI.as_view(), name="update_de"),
    url(r'^stock/', views.StockCurrencyAPI.as_view(), name="update_de"),
]