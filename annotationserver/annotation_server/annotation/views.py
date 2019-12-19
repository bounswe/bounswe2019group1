import requests
from django.shortcuts import render
import json
from rest_framework.response import Response
from datetime import datetime, timedelta
from collections import OrderedDict


from rest_framework.exceptions import ValidationError
# Create your views here.
from rest_framework.generics import ListAPIView, RetrieveAPIView
from datetime import datetime

from rest_framework.views import APIView

from annotation.models import Annotation, Body
from annotation.serializers import RefinedBySerializer, SelectorSerializer, TargetSerializer, CreatorSerializer, \
    BodySerializer, AnnotationSerializer, AnnotationViewSerializer


# Create your views here.


class AnnotationCreate(APIView):

    def post(self, request, *args, **kwargs):
        target=request.data.get('target', None)
        selector=target.get('selector',None)
        refinedBy = selector.get('refinedBy', None)
        creator = request.data.get('creator', None)
        body = request.data.get('body', None)
        if target is None or selector is None or refinedBy is None or creator is None or body is None:
            raise ValidationError({"detail": "wrong data format"})
        serializer = RefinedBySerializer(data=refinedBy)
        serializer.is_valid(raise_exception=True)
        serializer.save()
        refined_by_id=serializer.data["id"]
        selector['refinedBy']=refined_by_id
        serializer = SelectorSerializer(data=selector)
        serializer.is_valid(raise_exception=True)
        serializer.save()
        selector_id = serializer.data["id"]
        target['selector']=selector_id
        serializer = TargetSerializer(data=target)
        serializer.is_valid(raise_exception=True)
        serializer.save()
        target_id = serializer.data["id"]
        serializer = CreatorSerializer(data=creator)
        serializer.is_valid(raise_exception=True)
        serializer.save()
        creator_id = serializer.data["id"]
        serializer = BodySerializer(data=body,many=True)
        serializer.is_valid(raise_exception=True)
        serializer.save()
        body_id = serializer.data
        body_list=[0] * len(body_id)
        for i in range (0,len(body_id)):
            body_list[i]=body_id[i]['id']
        data=request.data
        data['target'] = target_id
        data['creator'] = creator_id
        del data['body']
        #data['body'] = body_list
        serializer = AnnotationSerializer(data=data)
        serializer.is_valid(raise_exception=True)
        serializer.save()
        id=serializer.data["id"]
        annotation=Annotation.objects.get(id=id)
        annotation.save()
        for i in range (0,len(body_list)):
            annotation.body.add(body_list[i])
        annotation.save()
        serializer=AnnotationViewSerializer(annotation)
        return Response(serializer.data, status=200)
