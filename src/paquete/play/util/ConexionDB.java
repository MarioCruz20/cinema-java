package paquete.play.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    //Enlace de conexion del servidor con usuario y contraseña
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=Cinema_Star;encrypt=false";
    private static final String USER = "cruz";
    private static final String PASSWORD = "cruz2";

    public static Connection conectar() {
        Connection conexion = null;

        try {
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexion exitosa");
        } catch (SQLException e) {
            System.out.println("Error al conectar");
            e.printStackTrace();
        }
        return conexion;
    }
}
