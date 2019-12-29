from django.conf.urls import url

from . import views


urlpatterns = [
    url(r'^like/', views.LikeArticleAPIView.as_view(), name="create"),
    url(r'^dislike/', views.DislikeArticleAPIView.as_view(), name="list"),
    url(r'^likedArticlesByUserId/(?P<pk>[0-9_]+)/', views.ListLikedArticlesAPIView.as_view(), name="list"),
    url(r'^dislikedArticlesByUserId/(?P<pk>[0-9_]+)/', views.ListDislikedArticlesAPIView.as_view(), name="list"),
    url(r'^likesByArticleId/(?P<pk>[0-9_]+)/', views.ArticleLikesAPIView.as_view(), name="get"),
    url(r'^dislikesByArticleId/(?P<pk>[0-9_]+)/', views.ArticleDislikesAPIView.as_view(), name="get"),
    url(r'^likeCountByArticleId/(?P<pk>[0-9_]+)/', views.CountArticleLikeAPIView.as_view(), name="get"),
    url(r'^dislikeCountByArticleId/(?P<pk>[0-9_]+)/', views.CountArticleDislikeAPIView.as_view(), name="get"),
    url(r'^isLikedByUser/(?P<pk>[0-9_]+)/', views.IsLikedAPIView.as_view(), name="get"),
    url(r'^isDislikedByUser/(?P<pk>[0-9_]+)/', views.IsDislikedAPIView.as_view(), name="get"),
]