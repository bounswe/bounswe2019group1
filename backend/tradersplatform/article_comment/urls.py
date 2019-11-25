from django.conf.urls import url

from . import views


urlpatterns = [
    url(r'^create/', views.CreateArticleCommentAPIView.as_view(), name="create"),
    url(r'^delete/', views.DeleteArticleCommentAPIView.as_view(), name="delete"),
    url(r'^update/', views.UpdateArticleCommentAPIView.as_view(), name="update"),
    url(r'^list/(?P<pk>[0-9_]+)/', views.ListArticleCommentAPIView.as_view(), name="list"),
]