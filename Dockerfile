# Usamos imagen oficial de Maven + JDK
FROM maven:3.9.3-eclipse-temurin-17 AS build

# Carpeta de trabajo
WORKDIR /app

# Copiamos pom.xml y descargamos dependencias
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiamos el resto del proyecto y construimos
COPY src ./src
RUN mvn clean package -DskipTests

# Imagen final con solo JDK para correr la app
FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copiamos JAR del build
COPY --from=build /app/target/*.jar app.jar

# Exponemos el puerto 8080
EXPOSE 8080

# Comando para ejecutar la app
ENTRYPOINT ["java","-jar","app.jar"]
