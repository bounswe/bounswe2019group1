# Generated by Django 2.2.6 on 2019-11-23 14:02

from django.db import migrations


class Migration(migrations.Migration):

    dependencies = [
        ('equipment', '0019_traceindices'),
    ]

    operations = [
        migrations.RenameField(
            model_name='traceindices',
            old_name='EUR',
            new_name='DJI',
        ),
        migrations.RenameField(
            model_name='traceindices',
            old_name='GBP',
            new_name='INX',
        ),
        migrations.RenameField(
            model_name='traceindices',
            old_name='TRY',
            new_name='IXIC',
        ),
    ]