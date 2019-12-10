from notification.models import SetNotification, Notification
from django.apps import apps
from rest_framework.exceptions import ValidationError
from datetime import datetime


def set_notification():
    query=SetNotification.objects.all()
    for set_notification in query:
        currency=set_notification.currency
        amount=set_notification.amount
        equipment=get_equipment(currency)
        last = equipment.objects.all().last()
        currency_value=getattr(last, currency)
        if set_notification.is_bigger:
            if currency_value>amount:
                print("create notification bigger")
                new_notification=Notification(
                    owner=set_notification.owner,
                    date=datetime.now(),
                    text=currency+" has risen wanted level"
                )
                new_notification.save()
                set_notification.delete()
        else:
            if currency_value<amount:
                new_notification = Notification(
                    owner=set_notification.owner,
                    date=datetime.now(),
                    text=currency + " has fallen wanted level"
                )
                new_notification.save()
                set_notification.delete()



def get_equipment(value):
    list_currencies = ['BTC', 'LTC', 'ETH', 'XAG', 'XAU', 'GOOGL', 'AAPL', 'GM', 'EUR', 'GBP', 'TRY', 'DJI', 'IXIC',
                       'INX', 'SPY', 'IVV', 'VTI']
    dict = {}
    for i in range(0, len(list_currencies)):
        if value == list_currencies[i]:
            if i < 3:
                model = 'CryptoCurrencies'
            elif i < 5:
                model = 'Metals'
            elif i < 8:
                model = 'Stocks'
            elif i < 11:
                model = 'Currencies'
            elif i < 14:
                model = 'TraceIndices'
            elif i < 17:
                model = 'ETFInformation'
            equipment = apps.get_model("equipment", model)
            return equipment
    raise ValidationError({"detail": "That currency does not exist"})