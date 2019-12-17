from django.conf.urls import url

from . import views


urlpatterns = [
    url(r'^follow/', views.CreatePortfolioFollowAPIView.as_view(), name="register"),
    url(r'^list/', views.ListPortfolioFollowAPIView.as_view(), name="list"),
    url(r'^(?P<pk>[0-9_]+)/', views.GetPortfolioFollowAPIView.as_view(), name="get"),
    url(r'^listFollowersWithPortfolioId/(?P<pk>[0-9_]+)/', views.ListPortfolioFollowerByPortfolioIdAPIView.as_view(), name="list"),
    url(r'^listPortfoliosWithFollowerId/(?P<pk>[0-9_]+)/', views.ListPortfolioFollowerByFollowerIdAPIView.as_view(), name="list"),
    url(r'^isFollowing/(?P<pk>[0-9_]+)/', views.IsFollowingAPIView.as_view(), name="list"),
    url(r'^unfollow/', views.DeletePortfolioFollowAPIView.as_view(), name="list"),
]