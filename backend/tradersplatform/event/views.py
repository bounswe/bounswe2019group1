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
        response = urllib.urlopen(url)
        data = json.loads(response.read())

        return Response(data, 200)
