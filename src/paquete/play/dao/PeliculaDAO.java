package paquete.play.dao;

import paquete.play.contenido.Pelicula;
import paquete.play.util.ConexionDB;

import java.sql.*;

public class PeliculaDAO {

    public void guardar(Pelicula pelicula) {

        String sqlContenido = "INSERT INTO Contenido (titulo, genero, duracion, tipo, popularidad) VALUES (?, ?, ?, ?, ?)";
        String sqlPelicula = "INSERT INTO Pelicula (contenidoID, director) VALUES (?, ?)";

        try (Connection conn = ConexionDB.conectar()) {

            // 1. Insertar en Contenido
            PreparedStatement stmtContenido = conn.prepareStatement(sqlContenido, Statement.RETURN_GENERATED_KEYS);

            stmtContenido.setString(1, pelicula.getTitulo());
            stmtContenido.setString(2, pelicula.getGenero().toString());
            stmtContenido.setInt(3, pelicula.getDuracion());
            stmtContenido.setString(4, "Pelicula");
            stmtContenido.setDouble(5,  pelicula.getCalificacion());

            stmtContenido.executeUpdate();

            // 2. Obtener ID generado
            ResultSet rs = stmtContenido.getGeneratedKeys();
            int contenidoID = 0;

            if (rs.next()) {
                contenidoID = rs.getInt(1);
            }

            // 3. Insertar en Pelicula
            PreparedStatement stmtPelicula = conn.prepareStatement(sqlPelicula);
            stmtPelicula.setInt(1, contenidoID);
            stmtPelicula.setString(2, pelicula.getDirector());

            stmtPelicula.executeUpdate();

            System.out.println("Pelicula guardada en BD 🎬");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}