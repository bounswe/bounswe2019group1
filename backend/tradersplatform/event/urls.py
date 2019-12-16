from django.conf.urls import url

from . import views


urlpatterns = [
  url(r'^list/', views.EventCalendar.as_view(), name="update_de"),
  url(r'^search/', views.SearchEvent.as_view(), name="update_de"),
]
