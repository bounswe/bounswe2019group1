from django.conf.urls import url

from . import views


urlpatterns = [
    url(r'^follow/', views.CreateFollowAPIView.as_view(), name="register"),
    url(r'^followList/', views.ListFollowAPIView.as_view(), name="list"),
    url(r'^listFollower/', views.ListFollowerAPIView.as_view(), name="list"),
    url(r'^listFollowerWithId/', views.ListFollowerWithIdAPIView.as_view(), name="list"),
    url(r'^listFollowingWithId/', views.ListFollowingWithIdAPIView.as_view(), name="list"),
    url(r'^listFollowing/', views.ListFollowingAPIView.as_view(), name="list"),
    url(r'^delete/', views.DeleteFollowAPIView.as_view(), name="list"),
]