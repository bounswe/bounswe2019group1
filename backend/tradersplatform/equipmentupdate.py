import django
django.setup()

from notification.functions import buy_order, sell_order, set_notification, predict

from django.core.management.base import BaseCommand, no_translations

from equipment.calculate import calculate_cryptocurrency, calculate_currency, calculate_etf, calculate_metal_currency, \
    calculate_stock_currency, calculate_trace_indices

calculate_currency()
calculate_cryptocurrency()
calculate_stock_currency()
calculate_trace_indices()
calculate_metal_currency()
calculate_etf()
buy_order()
sell_order()
set_notification()
predict()
