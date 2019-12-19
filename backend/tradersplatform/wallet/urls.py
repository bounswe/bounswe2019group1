from django.conf.urls import url

from . import views


urlpatterns = [
    url(r'^createWallet/', views.WalletCreateAPIView.as_view(), name="list"),
    url(r'^ListAllWallets/', views.WalletListAPIView.as_view(), name="list"),
    url(r'^retrieve/', views.WalletRetrieveAPIView.as_view(), name="list"),
    url(r'^delete/', views.WalletDeleteAPIView.as_view(), name="list"),
    url(r'^sendUSD/', views.SendUSDAPIView.as_view(), name="list"),
    url(r'^takeequipment/', views.PurchaseEquipmentAPIView.as_view(), name="list"),
    url(r'^sellequipment/', views.SellEquipmentAPIView.as_view(), name="list"),
]