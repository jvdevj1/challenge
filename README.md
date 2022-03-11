# Bienvenidos!

La prueba consiste en agregar nueva funcionalidad a la API REST que corre en este repositorio. Para eso vamos a guiarnos por los siguientes puntos:

1) Hacer un fork del repositorio, crear un nuevo branch y realizar las tareas enunciadas a continuación.

2) Proveer servicios para la administración de la compra de productos. Los mismos deberán incluir:
- ABM de productos.
- ABM de clientes.
- Consulta de transacciones de compra.
- Aprobación de compras.
 
3) Los servicios deben contar con logs que indiquen si el servicio respondió correctamente o no.
  
4) Documentar brevemente los servicios implementados.
 
5) Todos los servicios deben contar, al menos, con test unitarios.
 
6) Enviar un Pull Request con todos los cambios realizados.

Para correr la aplicación se puede utilizar maven:

mvn spring-boot:run -Dspring-boot.run.profiles=local

Pueden probar el servicio de prueba con un curl de la siguiente forma:

`curl -X POST -H 'Content-Type: application/json' -H 'Accept: application/json' -d '{"message":"mensaje de prueba"}' localhost:8080/custom/echo `

Bonus

1) Hostear la app en un cloud computing libre (Cloudfoudry o APP Engine) y enviar la URL para consultar.

2) ABM de vendedores.

3) Agregar test de integración.

4) Correr pruebas con base de datos en memoria.

5) Calcular la cobertura de los tests.


# Prueba implementada
Librerías/utilidades empleadas en la implementación:

- **Lombok**: para la autogeneración de código repetitivo (getters, setters, ...).
- **Mapstruct**: para el mapeo entre objetos (típico para conversión DTO's / entidades). 
- **AspectJ**: AOP para trazas de logs transversales.
- **Springfox**: para para la especificación Swagger.
- **JaCoCo**: para el análisis de cobertura de los tests 

**Gestión de errores**: se ha implementado con RestControllerAdvice de Spring.

**Página Swagger de la aplicación**: http://localhost:8080/swagger-ui/

**Informes de cobertura de tests de JaCoCo**: se generan en la ruta _target/site/jacoco_


**FUNCIONALIDADES PARA UNA APLICACIÓN REAL**

En una aplicación real, generalmente se deberían implementar algunas de las siguientes funcionalidades (según los requisitos):
- **Validación de datos de entrada**: realizar validación sobre los datos de entrada que aplique (por ejemplo validar NIF, formato de fechas, ...)
- **Paginación de resultados**: en los endpoints que obtiene listas, realizar las consultas con paginación
- **Gestión de errores mejorada**: gestión de errores genérica y con códigos de error
- **Optimización de la base de datos**: creación de índices, ...
- **Seguridad**: autenticación y autorización
- **Caché**: espcialmente para la obtención de listados de objetos
- **Monitorización/métricas**: recolección de datos para poder generar gráficas y alertas (por ejemplo con Prometheus y Grafana)