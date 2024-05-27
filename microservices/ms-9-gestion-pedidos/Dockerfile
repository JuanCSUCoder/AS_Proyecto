FROM maven AS build
# Copiar el código fuente al contenedor
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app

# Establecer el directorio de trabajo
WORKDIR /usr/src/app

# Construir la aplicación usando Maven
RUN mvn clean package

FROM icr.io/appcafe/open-liberty:kernel-slim-java17-openj9-ubi

COPY --from=build --chown=1001:0 /usr/src/app/src/main/liberty/config /config

RUN features.sh

COPY --from=build --chown=1001:0 /usr/src/app/target/*.war /config/apps

EXPOSE 9080

RUN configure.sh
