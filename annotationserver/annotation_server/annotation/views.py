import requests
from django.shortcuts import render
import json
from rest_framework.response import Response
from datetime import datetime, timedelta
from collections import OrderedDict


from rest_framework.exceptions import ValidationError
# Create your views here.
from rest_framework.generics import ListAPIView, RetrieveAPIView, CreateAPIView
from datetime import datetime

from rest_framework.views import APIView

from annotation.models import Annotation, Body, Creator, Target
from annotation.serializers import RefinedBySerializer, SelectorSerializer, TargetSerializer, CreatorSerializer, \
    BodySerializer, AnnotationSerializer, AnnotationViewSerializer


# Create your views here.


class AnnotationCreate(APIView):

    def post(self, request, *args, **kwargs):
        last_annotation=Annotation.objects.last()
        if not last_annotation:
            annotation_id = "http://khajiittraders.tk/annotation_id1"
        else:
            last_id=last_annotation.id
            arr=last_id.split("id")
            value=int(arr[1])+1
            annotation_id="http://khajiittraders.tk/annotation_id"+str(value)
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
        if isinstance(creator,str):
            creator_id=creator
        elif isinstance(creator,dict):
            serializer = CreatorSerializer(data=creator)
            serializer.is_valid(raise_exception=True)
            serializer.save()
            creator_id = serializer.data["id"]
        else :
            raise ValidationError({"detail": "You should either give an existing creators id or give credentials to create new user"})
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
        data['id']=annotation_id
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


class AddBody(APIView):

    def put(self, request, *args, **kwargs):
        id=request.data.get('id',None)
        if id is None:
            raise ValidationError({"detail": "Give id of annotation"})
        annot=Annotation.objects.filter(id=id).first()
        if not annot:
            raise ValidationError({"detail": "This annotation does not exist"})
        body = request.data.get('body', None)
        if body is None:
            raise ValidationError({"detail": "Give id of annotation"})
        serializer = BodySerializer(data=body, many=True)
        serializer.is_valid(raise_exception=True)
        serializer.save()
        body_id = serializer.data
        body_list = [0] * len(body_id)
        for i in range(0, len(body_id)):
            annot.body.add(body_id[i]['id'])
        annot.save()
        serializer = AnnotationViewSerializer(annot)
        return Response(serializer.data, status=200)


class AnnotationListAPIView(APIView):

    def get(self, request, *args, **kwargs):
        source = request.GET.get('source', None)
        if source is None:
            raise ValidationError({"detail": "Give id of annotation"})

        query_target=Target.objects.filter(source=source)
        annotation_query=Annotation.objects.none()
        query = Annotation.objects.filter(target=43)
        for target in query_target:
            id=target.id
            query=Annotation.objects.filter(target=target.id)
            annotation_query=annotation_query | query
        serializer= AnnotationViewSerializer(annotation_query,many=True)
        return Response(serializer.data, status=200)


class AnnotationListMobileAPIView(APIView):

    def get(self, request, *args, **kwargs):
        source = request.GET.get('source', None)
        if source is None:
            raise ValidationError({"detail": "Give id of annotation"})

        query_target=Target.objects.filter(source=source)
        annotation_query=Annotation.objects.none()
        query = Annotation.objects.filter(target=43)
        for target in query_target:
            id=target.id
            query=Annotation.objects.filter(target=target.id)
            annotation_query=annotation_query | query
        serializer= AnnotationViewSerializer(annotation_query,many=True)
        dict={"result":serializer.data}
        return Response(dict, status=200)


class CreatorAPIView(CreateAPIView):
    serializer_class = CreatorSerializer
    queryset = Creator.objects.filter()


class AnnotationAllListAPIView(ListAPIView):
    serializer_class = AnnotationViewSerializer
    queryset = Annotation.objects.all()


class CreatorListAPIView(ListAPIView):
    serializer_class = CreatorSerializer
    queryset = Creator.objects.all()


class CreatorListMobileAPIView(ListAPIView):

    def get(self, request, *args, **kwargs):
        queryset = Creator.objects.all()
        serializer_class = CreatorSerializer(queryset,many=True)
        dict={"result":serializer_class.data}
        return Response(dict, status=200)


class IsCreator(APIView):

    def get(self, request, *args, **kwargs):
        id = request.GET.get('id', None)
        if id is None:
            raise ValidationError({"detail": "Give id of creator"})
        creator = Creator.objects.filter(id=id).first()
        if creator:
            return Response({"message":"creator exists"}, status=200)
        return Response({"message": "creator does not exist"}, status=200)


class DeleteAnnotation(APIView):

    def delete(self, request, *args, **kwargs):
        id=request.data.get('id',None)
        if id is None:
            raise ValidationError({"detail": "Give id of annotation"})
        annot=Annotation.objects.filter(id=id).first()
        if not annot:
            raise ValidationError({"detail": "This annotation does not exist"})
        annot.delete()
        return Response({}, status=200)