# Generated by Django 2.2.6 on 2019-11-24 14:37

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('myuser', '0006_auto_20191124_1435'),
    ]

    operations = [
        migrations.AlterField(
            model_name='templateuser',
            name='phone_number',
            field=models.CharField(blank=True, default='', max_length=300, null=True),
        ),
    ]
