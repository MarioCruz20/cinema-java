# Cinema_Star

Sistema de gestión de cine desarrollado en **Java** con conexión a **SQL Server**.  
Permite al usuario interactuar mediante un menú en consola para gestionar películas y contenido.

---

## Requisitos

- Java JDK 8 o superior  
- SQL Server  
- SQL Server Management Studio (SSMS)  

---

## Configuración de la Base de Datos

El proyecto incluye dos opciones para crear la base de datos:

### Opción 1: Restaurar base de datos (.bak) *(Recomendada)*

1. Abrir **SQL Server Management Studio (SSMS)**  
2. Conectarse al servidor  
3. Click derecho en **Databases** → **Restore Database…**  
4. Seleccionar **Device**  
5. Click en **…** → **Add**  
6. Buscar y seleccionar el archivo:  Cinema_Star.bak

7. Presionar **OK**  
8. Verificar el nombre de la base de datos  
9. Presionar **OK** para restaurar  

La base de datos quedará lista con toda su estructura y datos.

---

### Opción 2: Ejecutar script SQL (.sql)

1. Abrir **SQL Server Management Studio (SSMS)**  
2. Crear una nueva base de datos llamada: Cinema_Star
3. Abrir el archivo: QUERY_Cinema_Star.sql
4. Ejecutar el script  

 Esto creará las tablas, relaciones y datos necesarios.

---

##  Configuración de conexión en Java

Asegúrate de configurar correctamente la conexión en el proyecto en paquete.play > util > ConexionDB.java:

```java
private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=Cinema_Star;encrypt=false";
private static final String USER = "tu_usuario"; 
private static final String PASSWORD = "tu_contrasena";


