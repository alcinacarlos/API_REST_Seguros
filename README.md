
# Ejercicio 1: Gestión de Seguros en una Aplicación Spring Boot con Kotlin
# Entrega




# Enunciado
En este ejercicio, vamos a desarrollar una aplicación básica en **Spring Boot** utilizando **Kotlin** que permita gestionar entidades de tipo **Seguro**. La aplicación deberá implementar un conjunto de operaciones CRUD a través de métodos HTTP para interactuar con la tabla asociada al modelo de datos.

## Parte 1: Definición de la Entidad y Controlador

### 1. Entidad Seguro
Crea una clase de datos en Kotlin que represente la entidad **Seguro** con los siguientes atributos:

```kotlin
data class Seguro(
    val idSeguro: Int,
    val nif: String,
    val nombre: String,
    val ape1: String,
    val ape2: String?,
    val edad: Int,
    val numHijos: Int,
    val fechaCreacion: Date,
    val sexo: String,
    val casado: Boolean,
    val embarazada: Boolean
)
```

**Modelo de la base de datos (Tabla Seguro):**

```sql
CREATE TABLE seguros (
    idSeguro INTEGER PRIMARY KEY,
    nif VARCHAR(10) NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    ape1 VARCHAR(100) NOT NULL,
    ape2 VARCHAR(100),
    edad INTEGER NOT NULL CHECK (edad > 0),
    num_hijos INTEGER NOT NULL CHECK (num_hijos >= 0),
    fecha_creacion TIMESTAMP NOT NULL,
    sexo VARCHAR(10) NOT NULL,
    casado BOOLEAN NOT NULL,
    embarazada BOOLEAN NOT NULL
);
```

### 2. Validaciones a implementar en la capa service

- El campo `nif` debe ser válido (formato correcto).
- El campo `nombre` no puede estar vacío.
- El campo `ape1` no puede estar vacío.
- El campo `edad` debe ser mayor que 0.
    - Si `edad` está entre 0 y 17 años, deberá indicar que **"No es posible ser menor de edad para hacer un seguro"**.
- El campo `sexo` no puede ser `null`.
- El campo `numHijos` no puede ser menor que 0.
- Si el campo `casado` es `false`, el campo `numHijos` debe ser igual a 0.
- Si el campo `embarazada` es `true`, el campo `sexo` debe ser **"Mujer"**.

### 3. Controlador SeguroController

Implementa una clase `SeguroController` con los endpoints necesarios para realizar operaciones CRUD sobre la entidad **Seguro**. Cada endpoint deberá cumplir con las validaciones descritas anteriormente.

### Operaciones CRUD

- **GET** `/seguros`:  
  Devuelve una lista de todos los seguros registrados.

- **GET** `/seguros/{id}`:  
  Devuelve un seguro por su identificador `idSeguro`. Si no existe, retorna un error 404.

- **POST** `/seguros`:  
  Crea un nuevo seguro.
    - Valida los campos siguiendo las restricciones mencionadas.

- **PUT** `/seguros/{id}`:  
  Actualiza un seguro existente identificado por `idSeguro`.
    - Si no existe, retorna un error 404.
    - Aplica las validaciones antes de guardar los cambios.

- **DELETE** `/seguros/{id}`:  
  Elimina un seguro identificado por `idSeguro`. Si no existe, retorna un error 404.

### Requisitos del Entorno

1. Crea un proyecto en **Spring Boot** con las siguientes dependencias:
    - **Spring Web**
    - **Spring Data JPA**
    - **MySQL Database** (o el sistema de base de datos de tu elección)
2. Configura la base de datos en el archivo `application.properties`.

### Entrega

1. Código de la entidad `Seguro` con sus campos.
2. Código del service `SeguroService` con sus validaciones.
3. Código del controlador `SeguroController` con los endpoints definidos.
4. Pruebas funcionales utilizando Postman o Swagger para demostrar el correcto funcionamiento de las operaciones CRUD.
5. Realiza un documento donde incluyas todos los puntos anteriores, junto con las explicaciones, pantallazos y comentarios que creas convenientes para que esta parte de la aplicación quede correctamente explicada.

## Sugerencia de Validación en Spring Boot

Puedes usar anotaciones de validación estándar como `@NotNull`, `@Min`, `@Max`, `@Pattern`, o crear validadores personalizados según sea necesario.
