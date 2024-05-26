# Etapa de construcción
FROM maven AS buildjar

WORKDIR /opt/app

COPY . /opt/app

RUN mvn clean package


# Contenedor final
FROM icr.io/appcafe/open-liberty:kernel-slim-java21-openj9-ubi-minimal

COPY --chown=1001:0 /src/main/liberty/config /config

RUN features.sh

COPY --chown=1001:0 target/*.war /config/apps

EXPOSE 9080

RUN configure.sh
