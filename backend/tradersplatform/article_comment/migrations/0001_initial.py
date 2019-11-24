# Generated by Django 2.2.6 on 2019-11-24 10:29

from django.db import migrations, models
import django.db.models.deletion
import django.utils.timezone


class Migration(migrations.Migration):

    initial = True

    dependencies = [
        ('article', '0001_initial'),
        ('myuser', '0005_templateuser_is_public'),
    ]

    operations = [
        migrations.CreateModel(
            name='ArticleComment',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('text', models.CharField(max_length=200)),
                ('created_date', models.DateTimeField(default=django.utils.timezone.now)),
                ('article', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, related_name='article', to='article.Article')),
                ('user', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, related_name='user', to='myuser.TemplateUser')),
            ],
        ),
    ]