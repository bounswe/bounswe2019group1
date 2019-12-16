import urllib
import json
from datetime import date

from rest_framework.response import Response
from rest_framework.generics import ListAPIView


# Create your views here.

class EventCalendar(ListAPIView):
    def get(self, request, *args, **kwargs):
        """
        find this request in
        https://www.forexfactory.com/calendar.php
        :param request:
        :param args:
        :param kwargs:
        :return:
        """
        today = str(date.today())
        year = str(today)[0:4]
        day = str(today)[-2:]

        url = "https://cdn-nfs.faireconomy.media/ff_calendar_thisweek.json?date=" + day + "." + year
        response = urllib.request.urlopen(url)
        data = json.loads(response.read())

        return Response(data, 200)


class SearchEvent(ListAPIView):
    def get(self, request, *args, **kwargs):

        today = str(date.today())
        year = str(today)[0:4]
        day = str(today)[-2:]

        url = "https://cdn-nfs.faireconomy.media/ff_calendar_thisweek.json?date=" + day + "." + year
        response = urllib.request.urlopen(url)
        data = json.loads(response.read())

        searchResults = []
        searchItem = kwargs.get("pk")
        semanticsUrl = "https://api.datamuse.com/words?ml=" + str(searchItem) + "&max=100"
        semanticsResponse = urllib.request.urlopen(semanticsUrl)
        semantics = json.loads(semanticsResponse.read())

        for word in semantics:
            for event in data:
                if word['word'] in event['title'].lower():
                    searchResults.append(j)

        return Response(searchResults, 200)
