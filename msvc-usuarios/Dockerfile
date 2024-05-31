# Etapa de construcción
FROM openjdk:17-jdk-alpine as builder

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app/msvc-usuarios

# Copia el archivo pom.xml a la carpeta /app
COPY ./pom.xml /app

# Copia el directorio .mvn y el archivo mvnw al directorio de trabajo actual
COPY ./msvc-usuarios/.mvn ./.mvn
COPY ./msvc-usuarios/mvnw .
COPY ./msvc-usuarios/pom.xml .

# Ejecuta el comando de Maven para limpiar y empaquetar el proyecto, omitiendo pruebas, empaquetado principal y el reempaquetado de Spring Boot. Luego, elimina la carpeta target.
RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/

# Opción comentada para descargar dependencias sin conexión (no ejecutada actualmente)
#RUN ./mvnw dependency:go-offline

# Copia el directorio src al directorio de trabajo actual
COPY ./msvc-usuarios/src ./src

# Ejecuta el comando de Maven para limpiar y empaquetar el proyecto, omitiendo las pruebas.
RUN ./mvnw clean package -DskipTests

# Etapa de despliegue
FROM openjdk:17-jdk-alpine

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR generado desde la etapa de construcción
COPY --from=builder /app/msvc-usuarios/target/msvc-usuarios-0.0.1-SNAPSHOT.jar .

# Expone el puerto 8001 para que la aplicación pueda ser accesible
EXPOSE 8001

# Define el comando de inicio para ejecutar el archivo JAR
ENTRYPOINT ["java", "-jar", "msvc-usuarios-0.0.1-SNAPSHOT.jar"]



# COMANDOS DOCKER:
# docker --help : Muestra la ayuda para el comando Docker
# docker ps : Muestra las imágenes que están corriendo
# docker ps -a : Muestra todas las imágenes, incluyendo las detenidas
# docker start "nombre o id" : Inicia un contenedor detenido
# docker run -p 8081:8001 "nombre" : Ejecuta un contenedor con puerto mapeado
# docker run -d -p 8081:8001 "nombre" : Ejecuta un contenedor en modo detached
# docker attach "nombre" : Adjunta la consola a un contenedor
# docker stop "id o nombre" : Detiene un contenedor en ejecución
# docker logs "nombre usuario" : Muestra los logs de un contenedor
# docker logs -f "nombre" : Muestra los logs en tiempo real
# docker start -a "nombre" : Inicia un contenedor con la consola adjunta
# COMANDOS DOCKER PARA ELIMINAR CONTENEDORES DETENIDOS:
    # docker rm "id o nombre" : Elimina un contenedor detenido especificado por su ID o nombre
    # docker rm "nombre" "nombre" ... : Elimina varios contenedores detenidos especificados por sus nombres
    # docker container prune : Elimina todos los contenedores detenidos

# Otros comandos útiles:
    # docker images : Lista las imágenes en el sistema
    # docker rmi "id" : Elimina una imagen especificada por su ID
    # docker "id" "id" ... : Elimina varias imágenes especificadas por sus IDs
    # docker image prune : Elimina imágenes que no están en uso
