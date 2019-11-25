from django.conf.urls import url

from . import views


urlpatterns = [
    url(r'^createWallet/', views.WalletCreateAPIView.as_view(), name="list"),
    url(r'^ListAllWallets/', views.WalletListAPIView.as_view(), name="list"),
    url(r'^sendUSD/', views.SendUSDAPIView.as_view(), name="list"),
]