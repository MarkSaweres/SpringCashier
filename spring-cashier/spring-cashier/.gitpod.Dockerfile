FROM mysql:8.0.27

ENV MYSQL_DATABASE=midterm \
    MYSQL_USER=admin \
    MYSQL_PASSWORD=welcome \
    MYSQL_ROOT_PASSWORD=root

EXPOSE 3306
