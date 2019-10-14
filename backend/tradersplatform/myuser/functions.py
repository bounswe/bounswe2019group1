import boto3
from botocore.exceptions import ClientError
from email.mime.text import MIMEText
from email.mime.application import MIMEApplication
from email.mime.multipart import MIMEMultipart

from myuser.htmlcontent import feedback_email_part1, feedback_email_part2, feedback_email_part3
from tradersplatform.settings.base import AWS_KEY, AWS_SECRET_KEY


def send_email_cv(feedback_instance):

    email = feedback_instance["email"]
    SENDER = "Traders Platform <abdullah@birlikte.al>"

    # Replace recipient@example.com with a "To" address. If your account
    # is still in the sandbox, this address must be verified.
    RECIPIENT = email


    # If necessary, replace us-west-2 with the AWS Region you're using for Amazon SES.
    AWS_REGION = "eu-west-1"

    # The subject line for the email.
    SUBJECT = "Your register have been completed"

    # The full path to the file that will be attached to the email.

    ATTACHMENT=""

    # The email body for recipients with non-HTML email clients.
    BODY_TEXT = "Hello,\r\nPlease see the attached file for a list of customers to contact."

    # The HTML body of the email.
    BODY_HTML = feedback_email_part1  + email + feedback_email_part2 + "Your account has created" + feedback_email_part3


    # The character encoding for the email.
    CHARSET = "utf-8"

    # Create a new SES resource and specify a region.
    client = boto3.client('ses', region_name=AWS_REGION,aws_access_key_id=AWS_KEY,aws_secret_access_key=AWS_SECRET_KEY)

    # Create a multipart/mixed parent container.
    msg = MIMEMultipart('mixed')
    # Add subject, from and to lines.
    msg['Subject'] = SUBJECT
    msg['From'] = SENDER
    msg['To'] = RECIPIENT

    msg_body = MIMEMultipart('alternative')

    textpart = MIMEText(BODY_TEXT.encode(CHARSET), 'plain', CHARSET)
    htmlpart = MIMEText(BODY_HTML.encode(CHARSET), 'html', CHARSET)

    # Add the text and HTML parts to the child container.
    msg_body.attach(textpart)
    msg_body.attach(htmlpart)

    msg.attach(msg_body)

    # Add the attachment to the parent container.
    # print(msg)
    try:
        # Provide the contents of the email.
        response = client.send_raw_email(
            Source=SENDER,
            Destinations=[
                RECIPIENT
            ],
            RawMessage={
                'Data': msg.as_string(),
            },
        )
    # Display an error if something goes wrong.
    except ClientError as e:
        print(e.response['Error']['Message'])
    else:
        print("Email sent! Message ID:"),
        print(response['MessageId'])
        #AKIAIWLESW3S7ESHUNFQ