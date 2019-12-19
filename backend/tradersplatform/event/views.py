import urllib
import json
from datetime import date
from rest_framework.response import Response
from rest_framework.generics import ListAPIView


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

        search_item = kwargs.get("pk")
        semantics_url = "https://api.datamuse.com/words?ml=" + str(search_item) + "&max=100"
        semantics_response = urllib.request.urlopen(semantics_url)
        semantics = json.loads(semantics_response.read())
        semantics.insert(0, {"word": str(search_item), "score": 1000000, "tags": []})

        search_results = {"count": 0,
                          "results": []}
        for word in semantics:
            for event in data:
                if word['word'] in event['title'].lower():

                    search_results['results'].append(event)
                    search_results['count'] += 1
        
        return Response(search_results, 200)
