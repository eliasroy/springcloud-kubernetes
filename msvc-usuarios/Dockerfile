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
CMD ["java", "-jar", "msvc-usuarios-0.0.1-SNAPSHOT.jar"]



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

# COMANDOS PARA ELIMINAR CONTENEDORES DETENIDOS:
# - docker ps -a : Muestra una lista de todos los contenedores en el sistema, incluyendo los detenidos.
# - docker run -p 8001:8001 -d --rm "nombre" : Crea y ejecuta un contenedor a partir de una imagen especificada por "nombre", mapeando el puerto 8001 del contenedor al puerto 8001 del host (-p 8001:8001), ejecutándolo en segundo plano (-d) y eliminándolo automáticamente cuando se detiene (--rm).
# - docker stop "id" : Detiene un contenedor en ejecución especificado por su ID.

# MODO INTERACTIVO:
    # - docker run -p 8001:8001 --rm -it usuarios /bin/sh : Crea y ejecuta un contenedor de forma interactiva a partir de la imagen "usuarios", mapeando el puerto 8001 del contenedor al puerto 8001 del host, eliminando el contenedor automáticamente cuando se detiene (--rm), y abriendo una sesión de shell interactiva (/bin/sh).
    # - Se cambia ENTRYPOINT por CMD : En el Dockerfile, ENTRYPOINT se utiliza para definir el comando que se ejecutará cuando el contenedor se inicie. CMD se puede utilizar para proporcionar parámetros predeterminados a ENTRYPOINT o para ejecutar un comando específico si no se define ENTRYPOINT.
    # - docker stop ee : Detiene el contenedor con el nombre o ID "ee".
    # - docker run -p 8001:8001 --rm -it usuarios /bin/sh : (Repetido) Crea y ejecuta un contenedor de forma interactiva a partir de la imagen "usuarios", mapeando el puerto 8001 del contenedor al puerto 8001 del host, eliminando el contenedor automáticamente cuando se detiene, y abriendo una sesión de shell interactiva.
    # - Nos encontramos en consola : Estando dentro del contenedor en modo interactivo.
    # - java -jar mscv-usuarios-0.1.1-SNAPSHOT.jar : Ejecuta una aplicación Java especificada por el archivo JAR "mscv-usuarios-0.1.1-SNAPSHOT.jar" dentro del contenedor.
