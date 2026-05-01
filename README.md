# Cinema_Star

Sistema de gestión de cine desarrollado en **Java** con conexión a **SQL Server**.  
Permite al usuario interactuar mediante un menú para gestionar películas y contenido.

---

## Tecnologías

- Java SE  
- JavaFX (Interfaz gráfica)  
- JDBC (Conexión a base de datos)  
- SQL Server  
- IntelliJ IDEA  

---

## Requisitos

- Java JDK 8 o superior (recomendado JDK 17+)  
- SQL Server  
- SQL Server Management Studio (SSMS)  
- JavaFX SDK  
- Driver JDBC para SQL Server   

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

## Configuración JDBC (Conexión a SQL Server)

### 1. Descargar Driver JDBC

- Descargar el .zip desde:
  https://learn.microsoft.com/sql/connect/jdbc/download-microsoft-jdbc-driver-for-sql-server

- Descomprime el .zip y pegalo en el disco C:


### 2. Agregar el .jar al proyecto

En IntelliJ:

File > Project Structure > Libraries > +

Seleccionar el .jar en la carpeta que descomprimiste, seleccionando el .jar:

C:\sqljdbc_13.4\enu\jars\mssql-jdbc-xx.x.x.jre8.jar


### 3. Configuración de conexión en Java

Asegúrate de configurar correctamente la conexión en el proyecto en paquete.play > util > ConexionDB.java:

```java
private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=Cinema_Star;encrypt=false";
private static final String USER = "tu_usuario"; 
private static final String PASSWORD = "tu_contrasena";
```

---

## Configuración de JavaFX

### 1. Descargar JavaFX SDK

https://www.oracle.com/java/technologies/downloads/javafx/#javafx26-windows

### 2. Agregar JavaFX al proyecto

En IntelliJ:

File > Project Structure > Libraries > + 

Seleccionar la carpeta:

javafx-sdk-XX/lib

Debe verse como en la imagen:
<img width="1575" height="544" alt="image" src="https://github.com/user-attachments/assets/cb74eca8-c5e9-4533-8003-35dd260019d8" />

### 3. 3. Configurar VM Options

Ir a:

Run → Edit Configurations > + (Add new configuration)

<img width="449" height="136" alt="image" src="https://github.com/user-attachments/assets/12b632ca-dbed-4acc-bcdd-65b2bbb14ea5" />

Ir a:

Modify options > Add VM options

<img width="1183" height="688" alt="image" src="https://github.com/user-attachments/assets/6edbf657-587d-43ad-af64-e24ca332a833" />

Pegar en el cuadro nuevo de VM options:

--module-path "C:\javafx-sdk-26.0.1\lib" --add-modules javafx.controls,javafx.fxml --enable-native-access=javafx.graphics

Deberia quedar así:

<img width="1141" height="880" alt="image" src="https://github.com/user-attachments/assets/bab52fa3-5a3d-4095-8011-870806bc03af" />

Has clic en Apply > Ok y cerrar esa ventana

Arriba cambiar Main por JavaFX y hacer clic en Run:

<img width="593" height="297" alt="image" src="https://github.com/user-attachments/assets/fefcfee5-26a1-44dd-b511-e0200aedb002" />








