from django.conf.urls import url

from . import views


urlpatterns = [
    url(r'^predict/', views.PredictAPIView.as_view(), name="create"),
]