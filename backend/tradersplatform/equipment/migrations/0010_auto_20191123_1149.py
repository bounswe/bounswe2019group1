# Generated by Django 2.2.6 on 2019-11-23 11:49

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('equipment', '0009_auto_20191113_1328'),
    ]

    operations = [
        migrations.AlterField(
            model_name='stocks',
            name='AAPL',
            field=models.DecimalField(blank=True, decimal_places=3, max_digits=10, null=True),
        ),
        migrations.AlterField(
            model_name='stocks',
            name='GM',
            field=models.DecimalField(blank=True, decimal_places=3, max_digits=10, null=True),
        ),
        migrations.AlterField(
            model_name='stocks',
            name='GOOGL',
            field=models.DecimalField(blank=True, decimal_places=3, max_digits=10, null=True),
        ),
    ]