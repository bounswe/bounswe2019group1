from django.conf.urls import url

from . import views


urlpatterns = [
  url(r'^list/', views.EventCalendar.as_view(), name="update_de"),
  url(r'^search/(?P<pk>[a-zA-Z]+)/', views.SearchEvent.as_view(), name="update_de"),
]
