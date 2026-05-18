# Manual de despliegue



## Requisitos Previos

* Docker Desktop (Windows/Mac) o Docker Engine (Linux)
* Docker Compose (incluido en Docker Desktop)

## Pasos de Instalación



1. ##### Clonar repositorio

git clone https://github.com/tu-usuario/huellas-asturias.git

cd huellas-Asturias



##### 2\. Construir y levantar todos los servicios

docker-compose up -–build



##### 3\. Verificar que los contenedores están funcionando

docker ps



##### 4\. Acceder a la aplicación

Frontend: http://localhost:4200

Backend API: http://localhost:8080

Base de datos: localhost:3306



##### 5\. Para detener la aplicación

docker ps

