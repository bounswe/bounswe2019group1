from django.http import HttpResponse
from django.shortcuts import render

# Create your views here.
from rest_framework.exceptions import ValidationError
from rest_framework.generics import CreateAPIView, ListAPIView, DestroyAPIView
from rest_framework.response import Response
from rest_framework.views import APIView
import json

from portfolio_follow.models import PortfolioFollow
from portfolio_follow.serializers import PortfolioFollowCreateSerializer, PortfolioFollowerListSerializer, \
    FollowingPortfolioListSerializer, FollowPortfolioListSerializer
from myuser.models import TemplateUser
from portfolio.models import Portfolio


class CreatePortfolioFollowAPIView(CreateAPIView):

    def post(self, request, *args, **kwargs):
        check_if_user(request)
        id = request.user.id
        user = TemplateUser.objects.get(id=id)
        portfolio_id = request.data['portfolio_id']
        portfolio = Portfolio.objects.get(id=portfolio_id)
        if portfolio is None:
            raise ValidationError({"detail": 'This portfolio does not exist.'})
        if not portfolio['is_shared']:
            raise ValidationError({"detail": 'This portfolio is not shared with you.'})
        query = PortfolioFollow.objects.filter(follower=user, portfolio=portfolio)
        if query:
            raise ValidationError({"detail": 'You have already follow this portfolio'})
        data = {"follower": user, "portfolio": portfolio}
        serializer = PortfolioFollowCreateSerializer(data=data)
        serializer.is_valid(raise_exception=True)
        serializer.save()
        return Response(serializer.data, status=200)


class ListPortfolioFollowAPIView(ListAPIView):
    serializer_class = PortfolioFollowCreateSerializer
    queryset = PortfolioFollow.objects.all()


class DeletePortfolioFollowAPIView(DestroyAPIView):

    def delete(self, request, *args, **kwargs):
        check_if_user(request)
        id = request.user.id
        user = TemplateUser.objects.get(id=id)
        portfolio_id = request.data['portfolio_id']
        portfolio = Portfolio.objects.get(id=portfolio_id)
        if portfolio is None:
            raise ValidationError({"detail": 'This portfolio does not exist.'})
        if not portfolio['is_shared']:
            raise ValidationError({"detail": 'This portfolio is not shared with you.'})
        query = PortfolioFollow.objects.filter(portfolio=portfolio, follower=user)
        if not query:
            raise ValidationError({"detail": 'You do not follow this person'})
        follow = query.first()
        follow.delete()
        return Response({}, status=200)


class GetPortfolioFollowAPIView(ListAPIView):

    def get(self, request, *args, **kwargs):
        check_if_user(request)
        id = kwargs.get("pk")
        query = PortfolioFollow.objects.filter(id=id)
        serializer = FollowPortfolioListSerializer(query, many=True)
        return Response({"list": serializer.data}, status=200)


class ListPortfolioFollowerByPortfolioIdAPIView(ListAPIView):

    def get(self, request, *args, **kwargs):
        check_if_user(request)
        portfolio_id = kwargs.get("pk")
        portfolio = Portfolio.objects.get(id=portfolio_id)
        query = PortfolioFollow.objects.filter(portfolio=portfolio)
        serializer = PortfolioFollowerListSerializer(query, many=True)
        return Response({"list": serializer.data}, status=200)


class ListPortfolioFollowerByFollowerIdAPIView(ListAPIView):

    def get(self, request, *args, **kwargs):
        check_if_user(request)
        portfolio_id = kwargs.get("pk")
        portfolio = Portfolio.objects.get(id=portfolio_id)
        query = PortfolioFollow.objects.filter(portfolio=portfolio)
        serializer = PortfolioFollowerListSerializer(query, many=True)
        return Response({"list": serializer.data}, status=200)


class IsFollowingAPIView(APIView):

    def get(self, request, *args, **kwargs):
        check_if_user(request)
        id = request.user.id
        portfolio_id = request.data.get('portfolio_id', None)
        if portfolio_id is None:
            raise ValidationError({"detail": "give portfolio id"})
        user = TemplateUser.objects.get(id=id)
        portfolio = Portfolio.objects.get(id=portfolio_id)
        if not portfolio:
            raise ValidationError({"detail": "Portfolio with this portfolio id does not exist"})
        query = PortfolioFollow.objects.filter(follower=user, portfolio=portfolio).first()
        if query:
            return HttpResponse(json.dumps({'result': 'Found'}),
                                content_type="application/json")
        else:
            return HttpResponse(json.dumps({'result': 'Not Found'}),
                                content_type="application/json")


def check_if_user(request):
    if request.user.is_anonymous:
        raise ValidationError({"detail": "User is not authorized"})
