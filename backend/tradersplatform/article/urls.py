from django.conf.urls import url

from . import views


urlpatterns = [
    url(r'^create/', views.CreateArticleAPIView.as_view(), name="create"),
    url(r'^list/', views.ListArticleAPIView.as_view(), name="list"),
    url(r'^listPublicArticles/', views.ListPublicArticleAPIView.as_view(), name="list"),
    url(r'^listArticleByUserId/(?P<pk>[0-9_]+)/', views.ListArticleWithUserIdAPIView.as_view(), name="list"),
    url(r'^listArticlesOfFollowingUsers/', views.ListArticleOfFollowingUsersAPIView.as_view(), name="list"),
    url(r'^delete/', views.DeleteArticleAPIView.as_view(), name="delete"),
    url(r'^update/', views.UpdateArticleAPIView.as_view(), name="update"),
    url(r'^getById/(?P<pk>[0-9_]+)/', views.GetArticleAPIView.as_view(), name="get"),
]