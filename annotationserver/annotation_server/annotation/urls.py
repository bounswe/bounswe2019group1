from django.conf.urls import url

from . import views


urlpatterns = [
    url(r'^createannotation/', views.AnnotationCreate.as_view(), name="create"),
    url(r'^addbody/', views.AddBody.as_view(), name="create"),
    url(r'^getannotations/', views.AnnotationListAPIView.as_view(), name="create"),
    url(r'^deleteannotation/', views.DeleteAnnotation.as_view(), name="create"),
]