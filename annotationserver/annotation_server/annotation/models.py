from django.db import models

# Create your models here.


class RefinedBy(models.Model):
    type = models.CharField(max_length=300, blank=True, null=True, unique=False, default='')
    start = models.IntegerField(blank=True,null=True,unique=False,default=0)
    end = models.IntegerField(blank=True,null=True,unique=False,default=0)


class Selector(models.Model):
    type = models.CharField(max_length=300, blank=True, null=True, unique=False, default='')
    value = models.CharField(max_length=300, blank=True, null=True, unique=False, default='')
    refinedBy = models.ForeignKey(RefinedBy, on_delete=models.CASCADE, default='',related_name='refined_by')


class Target(models.Model):
    id_image = models.URLField(max_length=300, unique=False,blank=True, null=True)
    type = models.CharField(max_length=300, blank=True, null=True, unique=False, default='')
    styleClass = models.CharField(max_length=300, blank=True, null=True, unique=False, default='')
    source = models.CharField(max_length=300, blank=True, null=True, unique=False, default='')
    selector = models.ForeignKey(Selector, on_delete=models.CASCADE, default='',related_name='Selector')


class Creator(models.Model):
    id = models.URLField(max_length=300,unique=False,primary_key=True)
    type = models.CharField(max_length=300, blank=True, null=True, unique=False, default='')
    name = models.CharField(max_length=300, blank=True, null=True, unique=False, default='')
    nickname = models.CharField(max_length=300, blank=True, null=True, unique=False, default='')


class Body(models.Model):
    type = models.CharField(max_length=300, blank=True, null=True, unique=False, default='')
    purpose = models.CharField(max_length=300, blank=True, null=True, unique=False, default='')
    value = models.CharField(max_length=300, blank=True, null=True, unique=False, default='')


class Annotation(models.Model):
    id = models.URLField(max_length=300,unique=False,primary_key=True)
    type = models.CharField(max_length=300, blank=True, null=True, unique=False, default='')
    motivation = models.CharField(max_length=300, blank=True, null=True, unique=False, default='')
    created = models.DateTimeField( blank=True, null=True, unique=False, default=None)
    creator = models.ForeignKey(Creator, on_delete=models.CASCADE, default='',related_name='Selector')
    target = models.ForeignKey(Target, on_delete=models.CASCADE, default='',related_name='Target')
    body = models.ManyToManyField(Body, blank=True)
