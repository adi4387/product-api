FROM openjdk:15-oracle

WORKDIR /app

ADD "build/libs/product-api.jar" "/app"

ENTRYPOINT ["java", "-jar", "/app/product-api.jar"]