package paquete.play.dao;

import paquete.play.util.ConexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContenidoDAO {

    //Metodo para mostrar de tabla "contenido"
    public void listar() {

        String sql = "SELECT * FROM Contenido";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                System.out.println(
                        rs.getInt("contenidoID") + " - " +
                                rs.getString("titulo") + " - " +
                                rs.getString("genero") + " - " +
                                rs.getDouble("calificacion")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
