from django.conf.urls import url

from . import views


urlpatterns = [
    url(r'^like/', views.LikeArticleAPIView.as_view(), name="create"),
    url(r'^dislike/', views.DislikeArticleAPIView.as_view(), name="list"),
    url(r'^likedArticlesByUserId/', views.ListLikedArticlesAPIView.as_view(), name="list"),
    url(r'^dislikedArticlesByUserId/', views.ListDislikedArticlesAPIView.as_view(), name="list"),
    url(r'^likesByArticleId/', views.ArticleLikesAPIView.as_view(), name="delete"),
    url(r'^dislikesByArticleId/', views.ArticleDislikesAPIView.as_view(), name="delete"),
    url(r'^likeCountByArticleId/', views.CountArticleLikeAPIView.as_view(), name="delete"),
    url(r'^dislikeCountByArticleId/', views.CountArticleDislikeAPIView.as_view(), name="update"),
]