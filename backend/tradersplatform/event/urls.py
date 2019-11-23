from django.conf.urls import url

from . import views


urlpatterns = [
  url(r'^events/', views.EventCalendar.as_view(), name="update_de"),
]
