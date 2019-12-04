from django.conf.urls import url

from . import views


urlpatterns = [
    url(r'^createportfolio/', views.CreatePortfolioAPIView.as_view(), name="create"),
    url(r'^retrieveportfolio/(?P<pk>[0-9_]+)/', views.RetrievePortfolioAPIView.as_view(), name="create"),
]