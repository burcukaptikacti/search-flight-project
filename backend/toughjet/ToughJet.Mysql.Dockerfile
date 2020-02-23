FROM mysql:latest

ENV MYSQL_ROOT_PASSWORD Strong_Passw0rd 
ENV MYSQL_DATABASE toughjet

COPY ./src/main/resources/static/sql/data-seed.sql /docker-entrypoint-initdb.d/