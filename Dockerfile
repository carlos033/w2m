# Usa una imagen base de OpenJDK para Java 17
FROM openjdk:17-jdk-alpine

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el JAR generado por Maven a la ubicación /app en el contenedor
COPY target/W2M-1-0.0.1-SNAPSHOT.jar /app/W2M-1.jar

# Expone el puerto 8080 para que pueda ser accedido externamente
EXPOSE 8080

# Comando para ejecutar la aplicación Spring Boot cuando el contenedor se inicia
CMD ["java", "-jar", "W2M-1.jar"]
