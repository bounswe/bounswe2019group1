import decimal

from notification.models import SetNotification, Notification, BuyOrder, SellOrder
from django.apps import apps
from rest_framework.exceptions import ValidationError
from datetime import datetime

from wallet.models import Wallet
from wallet.views import update_wealth


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


def buy_order():
    query = BuyOrder.objects.all()
    for buy_order in query:
        currency = buy_order.currency
        amount = buy_order.amount
        buy_amount=buy_order.buy_amount
        equipment = get_equipment(currency)
        last = equipment.objects.all().last()
        currency_value = getattr(last, currency)
        curr_wallet = Wallet.objects.filter(owner=buy_order.owner).first()
        if not curr_wallet:
            raise ValidationError({"detail": "You don't have wallet"})
        if amount>currency_value:
            subtract_usd = currency_value * buy_amount
            curr_usd = curr_wallet.USD
            if curr_usd < subtract_usd:
                raise ValidationError({"detail": "You can not afford this amount"})
            else:
                wallet_amount = getattr(curr_wallet, currency)
                curr_wallet.USD = curr_usd - subtract_usd
                setattr(curr_wallet, currency, (wallet_amount + buy_amount))
                update_wealth(curr_wallet)
                curr_wallet.save()


def sell_order():
    query = SellOrder.objects.all()
    for sell_order in query:
        currency = sell_order.currency
        amount = sell_order.amount
        sell_amount=sell_order.sell_amount
        equipment = get_equipment(currency)
        last = equipment.objects.all().last()
        currency_value = getattr(last, currency)
        curr_wallet = Wallet.objects.filter(owner=sell_order.owner).first()
        if not curr_wallet:
            raise ValidationError({"detail": "You don't have wallet"})
        if amount>currency_value:
            wallet_amount = getattr(curr_wallet, currency)
            addition_usd = currency_value * sell_amount
            curr_usd = curr_wallet.USD
            if wallet_amount < sell_amount:
                raise ValidationError({"detail": "You don't have this much equipment"})
            else:
                curr_wallet.USD = curr_usd + addition_usd
                setattr(curr_wallet, currency, (wallet_amount - sell_amount))
                update_wealth(curr_wallet)
                curr_wallet.save()


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
