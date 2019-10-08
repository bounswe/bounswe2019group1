from django.test import TestCase
from rest_framework.exceptions import ValidationError

from backend.tradersplatform.myuser.models import TemplateUser


# Create your tests here.
class SerializersTestCase(TestCase):

    def test_validate_existing_user(self):
        # given
        serializer_ = TempUserCreateSerializer;
        user = TemplateUser.objects.create(username="tester-1", first_name="first-name", password="password")

        # when
        serializer_.validate(self, user);

        # then
        self.assertEqual(user, 'The lion says "roar"')

    def test_validate_when_credentials_are_incorrect(self):
        # given
        serializer_ = TempUserCreateSerializer;
        user = TemplateUser.objects.create(username="tester-1", first_name="first-name", password="password")

        # when
        serializer_.validate(self, user);

        # then
        self.assertRaises(serializer_, ValidationError, "Incorrect credential")

    def test_validate_when_password_is_incorrect(self):
        # given
        user = TemplateUser.objects.get(username="tester-1")

        # when

        # then
        self.assertEqual(user.validate_unique(), 'The lion says "roar"')
