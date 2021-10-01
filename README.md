Curso: Certified Tech Developer (by Digital House)  
Materia: Backend I  
Alumno: Agustín Nicolás Bravo Pérez.  
Camada 2 durante Agosto/Septiembre 2021

# Proyecto Final Clinica Odontológica

El proyecto es una API RESTful que ofrece un ABM de pacientes, odontologos y turnos. Fue armado con  **Maven** y **
Spring Boot** durante el transcurso de la cursada. Las dependencias se encuentran en el *pom.xml*.

## Overview

Para examinarlo rápidamente:
Usa un sistema de login: con el usuario admin@admin.com y la contraseña *admin* se tiene acceso a todos los endpoints,
que se pueden encontrar en el archivo *endpoints_postman.json*, que es un JSON que se puede importar a Postman para tenerlos
cargados (antes de cualquier petición, primero ejecutar el GET de login y segundo el POST de login, luego tenemos acceso
libre ya que la JSESSIONID queda guardada).  
También desde el navegador se ofrece una vista sencilla que permite un ABM
de turnos, pacientes y odontologos.  
La base de datos será creada en la dirección *jdbc:h2:~
/ctd_back1_clinica_odontologica*.

## Partes del Proyecto

### Dao

El patrón DAO lo usamos al inicio, para la persistencia de datos, haciendo a mano las consultas SQL con JDBC. Luego
pasamos a usar un ORM, asi que la capa  `dao` del proyecto está en desuso. El archivo *create.sql* también está en
desuso. El motor de base de datos utilizado es **H2**.

### Repository

Spring Boot y JPA nos ofrecen **Hibernate** como ORM para la persistencia de datos de forma que crea automáticamente la
base de datos y las consultas (también fueron creadas algunas consultas personalizadas utilizando HQL). Fue usado en la
capa `repository`.

### Modelo

La capa `service` se encarga de contener la lógica de negocio y encapsular el dominio. Se comunica al mundo exterior con
DTOs (se usó *modelmapper* como librería para mapear del modelo al DTO). Desde el `service` para abajo representamos la
información con los `model`.

### Controller

La capa `controller` se encarga de conectar al `service` con las peticiones que reciba desde el cliente. Con los
distintos *@RequestMapping* mapeamos el Controller a los distintos **endpoints** de la API, y con *ResponseEntity*
devolvemos respuestas HTTP. En *peticiones_postman.json* están los distintos endpoints con sus formatos, escrito en **
Postman**.

### Vistas

En realidad, el proyecto es 90% una API RESTful. El otro 10% utiliza el **patrón MVC**. Tiene también tres endpoints
especiales: */turno*, */paciente* y */odontologo*. Esos endpoints devuelven un *ModelAndView* (de Servlet), que son
vistas realizadas con **Thymeleaf**. Van acompañadas de unos archivos *.js* que simplemente permiten realizar
peticiones (add, remove y modify) al resto de endpoints de la API. Fueron brevemente estilizadas con la ayuda de
Bootstrap, un framework de CSS.

### Log In

Con **Spring Security** se implementó un sistema de autenticación que protege al servicio. Para logearse como admin,
usar el usuario *admin@admin.com* y la contraseña *admin*. Guarda una JSESSIONID como cookie.

### Testing

Se utilizó **JUnit**, integrado con Spring Test, para automatizar testeos unitarios y de integración (con *MockMvc*).
Con *maven test*, los podemos ejecutar en conjunto.

### Excepciones

La anotación *@RestControllerAdvice* nos permite definir varios *@ExceptionHanlder* globales que resolverán las
excepciones de toda la API. Con **Log4j** se loggean esos mensajes de error en el archivo *logfile.log*.
