from django.conf.urls import url

from . import views


urlpatterns = [
    url(r'^registertrader/', views.TempUserCreateAPIView.as_view(), name="register"),
    url(r'^registerbasic/', views.TempUserCreateAPIView.as_view(), name="register"),
    url(r'^login/', views.UserLoginAPIView.as_view(), name="register"),
    url(r'^profile/', views.UserAutoLogin.as_view(), name="register"),
    #url(r'^logs/', views.NoParsingFilter(), name="register"),
]