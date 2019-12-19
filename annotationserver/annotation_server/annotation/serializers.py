from rest_framework.serializers import ModelSerializer

from annotation.models import RefinedBy,Selector,Target,Creator,Body,Annotation


class RefinedBySerializer(ModelSerializer):
    class Meta:
        model = RefinedBy
        fields = '__all__'


class SelectorSerializer(ModelSerializer):

    class Meta:
        model = Selector
        fields = '__all__'


class SelectorViewSerializer(ModelSerializer):
    refinedBy = RefinedBySerializer()

    class Meta:
        model = Selector
        fields = '__all__'


class TargetSerializer(ModelSerializer):

    class Meta:
        model = Target
        fields = '__all__'


class TargetViewSerializer(ModelSerializer):
    selector = SelectorViewSerializer()

    class Meta:
        model = Target
        fields = '__all__'


class CreatorSerializer(ModelSerializer):
    class Meta:
        model = Creator
        fields = '__all__'


class BodySerializer(ModelSerializer):

    class Meta:
        model = Body
        fields = '__all__'


class AnnotationSerializer(ModelSerializer):

    class Meta:
        model = Annotation
        fields = '__all__'


class AnnotationViewSerializer(ModelSerializer):
    creator = CreatorSerializer()
    body = BodySerializer(many=True)
    target = TargetViewSerializer()

    class Meta:
        model = Annotation
        fields = '__all__'