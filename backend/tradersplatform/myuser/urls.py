from django.conf.urls import url

from . import views


urlpatterns = [
    url(r'^registertrader/', views.TempUserCreateAPIView.as_view(), name="register"),
    url(r'^registerbasic/', views.TempUserCreateAPIView.as_view(), name="register"),
    url(r'^login/', views.UserLoginAPIView.as_view(), name="register"),
    url(r'^profile/', views.UserAutoLogin.as_view(), name="register"),
    url(r'^retrieve/(?P<pk>[0-9_]+)/', views.UserRetrieveAPI.as_view(), name="register"),
    url(r'^retrievemobile/', views.UserRetrieveMobileAPI.as_view(), name="register"),
    url(r'^search_user/', views.SearchUserListAPIView.as_view(), name="update_de"),
    url(r'^updateuser/', views.UserUpdateAPIView.as_view(), name="update_de"),
    url(r'^updatepass/', views.PasswordUpdateAPIView.as_view(), name="update_de"),
    url(r'^logarrange/', views.ArrangeLog.as_view(), name="update_de"),
    #url(r'^logs/', views.NoParsingFilter(), name="register"),
]