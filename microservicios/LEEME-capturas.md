# Lab S11-B — Microservicios (guía rápida)

## Cómo correrlo
1. phpMyAdmin/XAMPP -> importar o pegar `00-crear-bases-de-datos.sql`.
2. IntelliJ -> abrir la carpeta `microservicios` (cada subcarpeta es un proyecto Maven; "Add as Maven project" si no los detecta).
3. Correr los 4 `...ServiceApplication` (puertos: cliente 8081, categoria 8082, producto 8083, pedido 8084).
4. Postman -> Import -> `Lab-S11-B.postman_collection.json` (ya trae todos los JSON del lab).

Si Maven falla por Java: el lab pide Java 25 / Spring Boot 4.1.0 (así está en los pom). Si tu IntelliJ tiene otro JDK, cambia `<java.version>` en el pom.

## Capturas a tomar (en este orden)
1. **BD**: phpMyAdmin con las 4 bases (bd_categoria, bd_producto, bd_cliente, bd_pedido) y las 4 `_dev`.
2. **Estructura**: IntelliJ con los 4 proyectos expandidos (controller, model, dto, exception, repository, services).
3. **application.properties** de un servicio (p. ej. cliente con puerto 8081).
4. **Consola** de los 4 servicios arrancados ("Started ...Application", puerto en "Tomcat started on port ...").
5. **Postman Categoría**: POST (201) de Laptops, Accesorios, Impresoras + GET listar.
6. **Postman error 400** de Categoría (body vacío) y **error 404** (GET /api/categorias/999).
7. **Postman Cliente**: POST Juan y María (201) + GET listar.
8. **Postman Producto**: POST de los 3 productos (201) + GET listar.
9. **Postman Pedido**: POST de los 2 pedidos + GET listar (se ven los detalles).
10. **phpMyAdmin**: tablas `pedido` y `detalle_pedido` con datos.
11. **YAML**: carpeta resources de categoria-service con `application.yml`, `application-dev.yml`, `application-prod.yml`.
12. **Consola categoria-service con perfil dev** ("The following 1 profile is active: dev", puerto 8082) + Postman GET/POST contra él (datos en `bd_categoria_dev`).

## Conclusiones (5)
1. Una arquitectura de microservicios divide la aplicación en servicios independientes (categoría, producto, cliente, pedido), cada uno con su propio proyecto, puerto y base de datos, lo que facilita el mantenimiento y la escalabilidad frente a un monolito.
2. La arquitectura por capas (controller, service, repository, model, dto, exception) mantiene bajo acoplamiento y alta cohesión: cada clase tiene una única responsabilidad.
3. Los DTO con anotaciones de validación (@NotBlank, @Valid) y el GlobalExceptionHandler permiten responder códigos HTTP correctos (201, 400, 404) con mensajes claros en cada servicio.
4. Probar con Postman confirmó que cada endpoint REST funciona de forma independiente y que el pedido guarda sus detalles en cascada con la relación @OneToMany/@ManyToOne.
5. Usar archivos YAML con perfiles (dev y prod) permite cambiar la configuración (base de datos, puerto, ambiente) sin modificar el código, algo clave para desplegar en distintos entornos.
