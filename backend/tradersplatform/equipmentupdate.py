import django
django.setup()
from django.core.management.base import BaseCommand, no_translations

from equipment.calculate import calculate_cryptocurrency

calculate_cryptocurrency()


'''from equipment.calculate import calculate_currency, calculate_cryptocurrency, calculate_metal_currency, \
    calculate_stock_currency, calculate_trace_indices, calculate_etf

print("aaaa")
calculate_currency()
calculate_cryptocurrency()
calculate_metal_currency()
calculate_stock_currency()
calculate_trace_indices()
calculate_etf()'''