from django.conf.urls import url

from . import views


urlpatterns = [
    url(r'^createnotification/', views.CreateNotificationAPIView.as_view(), name="create"),
    url(r'^setnotification/', views.SetNotificationAPIView.as_view(), name="create"),
    url(r'^createbuyorder/', views.BuyOrderAPIView.as_view(), name="create"),
    url(r'^createsellorder/', views.SellOrderAPIView.as_view(), name="create"),
    url(r'^listsetnotification/', views.ListSetNotificationAPIView.as_view(), name="create"),
    url(r'^listnotification/', views.ListNotificationAPIView.as_view(), name="create"),
]