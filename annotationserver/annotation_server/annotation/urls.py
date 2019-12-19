from django.conf.urls import url

from . import views


urlpatterns = [
    url(r'^createannotation/', views.AnnotationCreate.as_view(), name="create"),
]