#Servicio de inventario Ambiente Local
---------------------------------------------

1. Genera el archivo JAR

Ejecuta el siguiente comando en la terminal en la raíz del proyecto:

mvn clean package

Esto creará el archivo JAR en el directorio target del directorio del proyecto

2. Ejecuta el archivo JAR, copar el archivo jar de la carpeta target en el directorio destnado para despliegues y ejecuta:

java -jar nombre-del-archivo.jar

Asegúrate de reemplazar nombre-del-archivo.jar con el nombre real de tu archivo JAR.

Esto iniciar tu aplicación Spring Boot. Acceder en el navegador en localhost seguido del puerto en el que se está ejecutando tu aplicación, para el caso como localhost:8003

El ambiente debe tener instalado java jdk 19


#Servicio de inventario Ambiente kuberntes
-------------------------------------------------

En el servidor destinado deberia tener instalado y configurado:

- Docker, Kubernetes, kubctl (linea de comandos)

Pasos:

1. Empaqueta en docker en un archivo Dockerfile con el siguiente contenido:

FROM openjdk:11-jdk
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]


2. Constuir la imagen docker con el siguiente comando:

docker build -t inventario_prueba .

3. Subir la imagen a algun servicio de regisgtro de docker, con esto Kuberntes puede encontrarla, hacerlo de la siguiente manera:

    - Loguearse
      docker login

    - Subir imagen docker
      docker push inventario_prueba

4. Desplegra en Kubernetes:
   
   Para esto lo primero es tener un archivo de manifesto, en YAML donde se haga la descripción des despliegue de la siguiente manera:

    apiVersion: apps/v1
    kind: Deployment
    metadata:
    name: inventario_prueba_hub
    spec:
    replicas: 2
    selector:
        matchLabels:
        app: inventario_prueba
    template:
        metadata:
        labels:
            app: inventario_prueba
        spec:
        containers:
        - name: inventario_prueba
            image: inventario_prueba
            ports:
            - containerPort: 8003


   Con este archivo creado, se configura el despeligue con 'kubectl' asi:

   kubectl apply -f manifesto.yaml


5. Estabkcer un servicio de kubernetes

Se debe crear otro YAML de la siguiente manera:

apiVersion: v1
kind: Service
metadata:
  name: inventario_prueba_service
spec:
  type: LoadBalancer
  ports:
  - port: 8003
  selector:
    app: inventario_prueba

6. Crear y acceder al servicio, para esto se debe ejecutar:

kubectl apply -f   name: inventario_prueba_service.yaml

Con esto ya se podria acceder al servicio en el puerto configurado.


