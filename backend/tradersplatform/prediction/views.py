from django.shortcuts import render

# Create your views here.
from rest_framework.generics import CreateAPIView

from article.views import check_if_user
from prediction.serializers import PredictionSerializer
from rest_framework.response import Response


class PredictAPIView(CreateAPIView):

    def post(self, request, *args, **kwargs):
        check_if_user(request)
        request_data = request.data
        request_data['user']=request.user.id
        serializer=PredictionSerializer(data=request_data)
        serializer.is_valid(raise_exception=True)
        serializer.save()
        return Response(serializer.data, status=200)