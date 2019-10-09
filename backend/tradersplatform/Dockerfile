FROM python:3.7.3
ENV PYTHONUNBUFFERED 1
RUN mkdir /app

WORKDIR /app

COPY . /app/
# COPY docker_resources/wait-for-it.sh /
# RUN rm -rf /app/docker_resources

RUN pip install -r requirements.txt

EXPOSE 8000
ENV LC_ALL=en_US.UTF8
CMD ["python3", "manage.py", "makemigrations"]
CMD ["python3", "manage.py", "migrate"]
CMD ["python3", "manage.py", "runserver", "0.0.0.0:8000"]


# Make Entrypoint executable
# RUN chmod +x /app/docker-entrypoint.sh

#CMD [ "python", "manage", "runserver", ":8000" ]
#CMD [ "flask", "run", "-h", "0.0.0.0", "-p", "5003" ]
#CMD ["/bin/bash", "/app/docker-entrypoint.sh"]

