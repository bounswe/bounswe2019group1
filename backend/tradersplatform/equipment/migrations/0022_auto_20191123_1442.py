# Generated by Django 2.2.6 on 2019-11-23 14:42

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('equipment', '0021_auto_20191123_1414'),
    ]

    operations = [
        migrations.AlterField(
            model_name='etfdetail',
            name='price',
            field=models.DecimalField(blank=True, decimal_places=10, max_digits=20, null=True),
        ),
    ]