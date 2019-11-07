from django.conf.urls import url

from . import views


urlpatterns = [
    url(r'^currency/', views.CurrencyAPI.as_view(), name="update_de"),
    url(r'^cryptocurrency/', views.CryptoCurrencyAPI.as_view(), name="update_de"),
]