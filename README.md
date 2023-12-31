# Técnicas de Documentación y Validación: Proyecto Integrador. TUDAI

Proyecto de aplicación de técnicas de documentación y validación para Java vistas en la cátedra.  
Se trabajaron sobre distintos formas de testeo y documentación de componentes.  
Las herramientas utilizadas fueron: 
 - Para el testeo de unitario: **TestNG, JUnit**.
 - Para la documentación de código: **JavaDoc, Doxygen**.
 - Para la documentación, implementación y testeo de API: **OpenAPI, Swagger, SpringBoot, Postman**.
 - Para el testeo web automático: **Selenium, TestNG**.
 - Para el testeo de mutación: **PITest, TestNG, JUnit**.

El proyecto consta de un módulo principal ideado para gestionar las actividades y socios de un club. El mismo fue documentado con JavaDoc y se utilizó para generar un sitio navegable con doxygen.  
También se generaron algunas pruebas unitarias mínimas con TestNG y JUnit.  
Sobre esta base se documentó una API mínima con OpenAPI para luego generar la interfaz con Swagger y añadir la lógica necesaria para completar la implementación. Para testear los endpoints implementados de la API se utilizó Postman.  
Se generó un frontend con HTML y Javascript conectado a dicha API que contempla la funcionalidad de asociar una persona al club e inscribirlo a una actividad. Este frontend se utilizó para ejecutar pruebas automatizadas con Selenium.  
Por último, se ejecutó un test de mutación sobre los test creados inicialmentes para evaluar su calidad. En base a estos resultados se generaron test adicionales para apreciar el impacto en la cobertura.


## Testing 
Para realizar las pruebas unitarias se utilizó TestNG 7.8.0 y JUnit 5.10.1.  
Se creó la clase de test ActividadTest.java que contiene: 
- Datos de una Actividad ficticia.
- Un método destinado a cargar socios a la nómina previo a ejecutar cualquier test, anotado con @BeforeClass
- Un @DataProvider de TestNG que brinda una lista de socios utilizada por otros tests de la clase.
- Un método destinado a cargar los datos de la actividad de prueba antes de ejecutar cualquier test (@BeforeTest).
- 


