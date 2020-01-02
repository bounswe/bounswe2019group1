from django.test import TestCase
from article.models import Article
from myuser.models import TemplateUser
import django.utils.timezone
from freezegun import freeze_time


# Create your tests here.

@freeze_time("2020-01-01")
class ArticleModelTest(TestCase):
    @classmethod
    def setUpTestData(cls):
        # Set up non-modified objects used by all test methods
        user = TemplateUser.objects.create(username="irem üstün", email="iremustun@testmail.com",
                                           phone_number="1234567890")
        Article.objects.create(title='Article Test 1', content='Test Content 1', author=user)

    def test_author_name_label(self):
        article = Article.objects.get(id=1)
        author = article.__getattribute__('author')
        username = author.__getattribute__("username")
        self.assertEquals(username, 'irem üstün')

    def test_title_label(self):
        article = Article.objects.get(id=1)
        title = article.__getattribute__('title')
        self.assertEquals(title, 'Article Test 1')

    def test_content_label(self):
        article = Article.objects.get(id=1)
        content = article.__getattribute__('content')
        self.assertEquals(content, 'Test Content 1')

    def test_created_date(self):
        article = Article.objects.get(id=1)
        date = article.__getattribute__('created_date')
        self.assertEquals(date, django.utils.timezone.datetime(2020, 1, 1))
