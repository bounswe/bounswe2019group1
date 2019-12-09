"""tradersplatform URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/2.0/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""
from django.conf.urls import url
from django.contrib import admin
from django.urls import path, include
from django.conf import settings
from django.conf.urls.static import static

urlpatterns = [
    path('admin/', admin.site.urls),
    url(r'^auth/', include('auth.urls')),
    url(r'^user/', include('myuser.urls')),
    url(r'^event/', include('event.urls')),
    url(r'^follow/', include('follow.urls')),
    url(r'^equipment/', include('equipment.urls')),
    url(r'^wallet/', include('wallet.urls')),
    url(r'^portfolio/', include('portfolio.urls')),
    url(r'^article/', include('article.urls')),
    url(r'^article-like/', include('article_like.urls')),
    url(r'^article-comment/', include('article_comment.urls')),
]
if settings.DEBUG:
  urlpatterns += static(settings.MEDIA_URL, document_root=settings.MEDIA_ROOT)
