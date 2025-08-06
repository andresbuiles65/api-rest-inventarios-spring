# Imagen base de Java
FROM eclipse-temurin:21-jdk
# Metadata opcional
LABEL autor="Andres Builes" version="1.0"
# Crear carpeta de la app
WORKDIR /app
# Copiar el JAR compilado
ARG JAR_FILE=target/inventarios-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
#Exponer el puerto que usa tu API
EXPOSE 8080
# Comando para ejecutar la API
ENTRYPOINT ["java", "-jar", "app.jar"]

