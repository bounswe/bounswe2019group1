from django.core.management.base import BaseCommand, no_translations

from equipment.calculate import calculate_cryptocurrency


class Command(BaseCommand):
    ...

    @no_translations
    def handle(self, *args, **options):
        calculate_cryptocurrency()