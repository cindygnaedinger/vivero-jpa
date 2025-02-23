# Vivero Backend

Este es un proyecto de backend para la gestión de un vivero, desarrollado en **Java** con **JPA** y **MySQL**. Permite la administración de entidades como oficinas, pagos, pedidos y clientes.

## Tecnologías Utilizadas

- **Java 17+**
- **Jakarta Persistence API (JPA)**
- **MySQL**
- **Hibernate**
- **Maven**

## Estructura del Proyecto

```
📂 src/main/java/com/vivero
 ├── 📂 entidades          # Clases de entidad JPA (Oficina, Cliente, Pedido, Pago, etc.)
 ├── 📂 persistencia       # Clases DAO para acceso a la base de datos
 ├── 📂 servicios          # Lógica de negocio y validaciones
```

## Funcionalidades Implementadas

✔️ **CRUD de Oficinas**: Crear, leer, actualizar y eliminar oficinas.  
✔️ **Gestión de Pedidos**: Registrar pedidos con sus detalles.  
✔️ **Pagos**: Registrar pagos con validaciones en montos y fechas.  
✔️ **Clientes**: Administración de clientes y sus compras.  
✔️ **Uso de BigDecimal** para cálculos monetarios precisos.  

## Validaciones Implementadas

- **Cadenas**: No nulas, sin espacios en blanco.
- **Fechas**: Uso de `LocalDate`.
- **Valores numéricos**: `BigDecimal` con restricciones `@DecimalMin("0.01")`.

## Mejoras Futuras

- Implementación de **API REST con Spring Boot**.
- Uso de **DTOs** para mejorar la estructura de datos.
- Integración con **Swagger** para documentación.
- Implementación de **Spring Security** para autenticación.

---
## [Cindy Gnaedinger](https://portfolio-cindy-gnaedinger.vercel.app/) 2025
cyn.gnaedinger@gmail.com | 
[LinkedIn](linkedin.com/in/cindygnaedinger)
