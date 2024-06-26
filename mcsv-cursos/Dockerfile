# Etapa de construcción
FROM openjdk:17-jdk-alpine as builder

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app/mcsv-cursos

# Copia el archivo pom.xml a la carpeta /app
COPY ./pom.xml /app

# Copia el directorio .mvn y el archivo mvnw al directorio de trabajo actual
COPY ./mcsv-cursos/.mvn ./.mvn
COPY ./mcsv-cursos/mvnw .
COPY ./mcsv-cursos/pom.xml .

# Ejecuta el comando de Maven para limpiar y empaquetar el proyecto, omitiendo pruebas, empaquetado principal y el reempaquetado de Spring Boot. Luego, elimina la carpeta target.
#RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/

# Opción comentada para descargar dependencias sin conexión (no ejecutada actualmente)
RUN ./mvnw dependency:go-offline

# Copia el directorio src al directorio de trabajo actual
COPY ./mcsv-cursos/src ./src

# Ejecuta el comando de Maven para limpiar y empaquetar el proyecto, omitiendo las pruebas.
RUN ./mvnw clean package -DskipTests

# Etapa de despliegue
FROM openjdk:17-jdk-alpine

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app
RUN mkdir ./logs
# Copia el archivo JAR generado desde la etapa de construcción
COPY --from=builder /app/mcsv-cursos/target/mcsv-cursos-0.0.1-SNAPSHOT.jar .

# Expone el puerto 8001 para que la aplicación pueda ser accesible
EXPOSE 8002

# Define el comando de inicio para ejecutar el archivo JAR
CMD ["java", "-jar", "mcsv-cursos-0.0.1-SNAPSHOT.jar"]



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

# COMANDOS PARA COPIAR ARCHIVOS Y COMPILAR/EXECUTAR JAVA EN UN CONTENEDOR:
    # - cd .. : Cambia al directorio padre en el sistema de archivos del host.
    # - ls : Lista los archivos y directorios en el directorio actual en el sistema de archivos del host.
    # - docker cp ./Login.java "container_id":/app/Login.java : Copia el archivo Login.java desde el host al directorio /app del contenedor especificado por su ID.
    # - javac Login.java : Compila el archivo Login.java en el contenedor, generando el archivo Login.class.
    # - java Login : Ejecuta el archivo compilado Login.class en el contenedor.
    # - docker cp "container_id":/app/Login.java ./Login2.java : Copia el archivo Login.java desde el contenedor al host, renombrándolo como Login2.java.

# COMANDOS PARA COPIAR LOGS Y EDITARLOS:
    # - docker cp "id_contenedor":/app/logs ./logs : Copia el directorio de logs desde el contenedor especificado por su ID a un directorio llamado logs en tu máquina host.
    # - vim ./logs/spring.log : Abre el archivo de log spring.log con el editor vim desde la ubicación donde copiaste los logs.

# COMANDOS PARA INSPECCIONAR IMÁGENES Y CONTENEDORES:
    # - docker images inspect "id" : Proporciona información detallada sobre una imagen Docker especificada por su ID. Incluye detalles sobre la configuración de la imagen, las capas, las etiquetas, etc.
    # - docker container inspect "id" : Proporciona información detallada sobre un contenedor Docker especificado por su ID. Incluye detalles sobre la configuración del contenedor, los volúmenes montados, las variables de entorno, el estado actual, etc.


# Construcción de la imagen del servicio de cursos:
    # docker build -t cursos . -f .\mcsv-cursos\Dockerfile
        # - docker build: Construye una imagen de Docker a partir de un Dockerfile.
        # - -t cursos: Etiqueta la imagen con el nombre 'cursos'.
        # - .: Indica el directorio actual como contexto de construcción.
        # - -f .\mcsv-cursos\Dockerfile: Especifica la ruta al Dockerfile que se usará para construir la imagen.

    # Construcción de la imagen del servicio de usuarios:
    # docker build -t usuarios . -f .\msvc-usuarios\Dockerfile
    # - Similar al comando anterior, pero construye una imagen etiquetada como 'usuarios' usando el Dockerfile ubicado en .\msvc-usuarios\Dockerfile.


# Ejecución del contenedor del servicio de usuarios:
    # docker run -p 8001:8001 -d --rm --name msvc-usuarios --network spring usuarios
        # - docker run: Ejecuta un contenedor basado en una imagen de Docker.
        # - -p 8001:8001: Mapea el puerto 8001 del host al puerto 8001 del contenedor.
        # - -d: Ejecuta el contenedor en segundo plano (modo "detached").
        # - --rm: Elimina el contenedor automáticamente cuando se detiene.
        # - --name msvc-usuarios: Asigna el nombre 'msvc-usuarios' al contenedor.
        # - --network spring: Conecta el contenedor a la red Docker llamada 'spring'.
        # - usuarios: Utiliza la imagen etiquetada como 'usuarios'.

    # Ejecución del contenedor del servicio de cursos:
        # docker run -p 8002:8002 -d --rm --name mcsv-cursos --network spring cursos
        # - Similar al comando anterior, pero ejecuta el contenedor basado en la imagen 'cursos' y mapea el puerto 8002.


# Este archivo Dockerfile no se utiliza directamente para ejecutar los comandos
# a continuación, pero te proporcionará una guía comentada sobre cómo ejecutar
# los comandos en la línea de comandos para dockerizar MySQL.









# DOCKERIZACION DE BASE DE DATOS
# Paso 1: Descargar la imagen de MySQL versión 8
# Comando:
    # docker pull mysql:8
    # - docker pull: Descarga la imagen especificada desde Docker Hub.
    # - mysql:8: Especifica la imagen de MySQL versión 8.

# Paso 2: Ejecutar un contenedor de MySQL en segundo plano
# Comando:
    # docker run -d -p 3307:3306 --name mysql8 --network spring -e MYSQL_ROOT_PASSWORD=sasa -e MYSQL_DATABASE=msvc-usuarios mysql:8
    # - docker run: Ejecuta un contenedor basado en una imagen de Docker.
    # - -d: Ejecuta el contenedor en segundo plano (modo "detached").
    # - -p 3307:3306: Mapea el puerto 3307 del host al puerto 3306 del contenedor (puerto por defecto de MySQL).
    # - --name mysql8: Asigna el nombre 'mysql8' al contenedor.
    # - --network spring: Conecta el contenedor a la red Docker llamada 'spring'.
    # - -e MYSQL_ROOT_PASSWORD=sasa: Establece la contraseña del usuario root de MySQL a 'sasa'.
    # - -e MYSQL_DATABASE=msvc_usuarios: Crea una base de datos llamada 'msvc_usuarios' en MySQL.
    # - mysql:8: Utiliza la imagen de MySQL versión 8.

# Paso 3: Ver los logs del contenedor de MySQL para asegurarse de que se ha iniciado correctamente
# Comando:
    # docker logs mysql8
    # - docker logs: Muestra los logs de un contenedor en ejecución.
    # - mysql8: Nombre del contenedor cuyos logs queremos ver.

#docker run -p 5432:5432 --name postgres14 --network spring -e POSTGRES_PASSWORD=sas -e POSTGRES_DB=mscv_cursos -d postgres:14-alpine
#docker run: Ejecuta un contenedor basado en una imagen de Docker.
#-p 5432:5432: Mapea el puerto 5432 del host al puerto 5432 del contenedor (puerto por defecto de PostgreSQL).
#--name postgres14: Asigna el nombre postgres14 al contenedor.
#--network spring: Conecta el contenedor a la red Docker llamada spring.
#-e POSTGRES_PASSWORD=sas: Establece la contraseña del usuario postgres a sas.
#-e POSTGRES_DB=mscv_cursos: Crea una base de datos llamada mscv_cursos en PostgreSQL.
#-d: Ejecuta el contenedor en segundo plano (modo "detached").
#postgres:14-alpine: Utiliza la imagen de PostgreSQL versión 14 en Alpine Linux.

#VOLUMENES:
# docker run -d -p 3307:3306 --name mysql8 --network spring -e MYSQL_ROOT_PASSWORD=sasa -e MYSQL_DATABASE=msvc-usuarios -v data-mysql:/var/lib/mysql --restart=always mysql:8
#docker run -p 5432:5432 --name postgres14 --network spring -e POSTGRES_PASSWORD=sas -e POSTGRES_DB=mscv_cursos -d -v data-postgres:/var/lib/postgresql/data --restart=always postgres:14-alpine

# docker volume ps

#ACCEDER ALA BASE DE DATOS
# Accede al contenedor de MySQL de forma interactiva y elimínalo al detenerlo
#docker run -it --rm --network spring mysql:8 bash

# Conecta a MySQL usando el cliente de MySQL con la dirección IP del contenedor (-hmysql8), el usuario root (-uroot) y solicita la contraseña (-p)
#mysql -hmysql8 -uroot -p


# Accede al contenedor de PostgreSQL de forma interactiva y elimínalo al detenerlo
#docker run -it --rm --network spring postgres:14-alpine psql -h postgres14 -U postgres

# Una vez dentro de PostgreSQL, cambia a la base de datos 'mscv_cursos'
# \c mscv_cursos;

# Muestra la lista de tablas en la base de datos actual
# \dt;

# Muestra la estructura de la tabla 'cursos'
# \d+ cursos;
