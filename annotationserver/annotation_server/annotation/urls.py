from django.conf.urls import url

from . import views


urlpatterns = [
    url(r'^createannotation/', views.AnnotationCreate.as_view(), name="create"),
    url(r'^creatorregister/', views.CreatorAPIView.as_view(), name="create"),
    url(r'^creatorlist/', views.CreatorListAPIView.as_view(), name="create"),
    url(r'^creatorexist/', views.IsCreator.as_view(), name="create"),
    url(r'^addbody/', views.AddBody.as_view(), name="create"),
    url(r'^getannotations/', views.AnnotationListAPIView.as_view(), name="create"),
    url(r'^annotationsalllist/', views.AnnotationAllListAPIView.as_view(), name="create"),
    url(r'^deleteannotation/', views.DeleteAnnotation.as_view(), name="create"),
]