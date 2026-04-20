package paquete.play.dao;

import paquete.play.contenido.Documental;
import paquete.play.contenido.Pelicula;
import paquete.play.util.ConexionDB;

import java.sql.*;

public class DocumentalDAO {

    public void guardar(Documental documental) {

        String sqlContenido = "INSERT INTO Contenido (titulo, genero, duracion, tipo, calificacion) VALUES (?, ?, ?, ?, ?)";
        String sqlDocumental = "INSERT INTO Documental (contenidoID, director, narrador) VALUES (?, ?, ?)";

        try (Connection conn = ConexionDB.conectar()) {

            // 1. Insertar en Contenido
            PreparedStatement stmtContenido = conn.prepareStatement(sqlContenido, Statement.RETURN_GENERATED_KEYS);

            stmtContenido.setString(1, documental.getTitulo());
            stmtContenido.setString(2, documental.getGenero().toString());
            stmtContenido.setInt(3, documental.getDuracion());
            stmtContenido.setString(4, "Documental");
            stmtContenido.setDouble(5,  documental.getCalificacion());

            stmtContenido.executeUpdate();

            // 2. Obtener ID generado
            ResultSet rs = stmtContenido.getGeneratedKeys();
            int contenidoID = 0;

            if (rs.next()) {
                contenidoID = rs.getInt(1);
            }

            // 3. Insertar en Documental
            PreparedStatement stmtDocumental = conn.prepareStatement(sqlDocumental);
            stmtDocumental.setInt(1, contenidoID);
            stmtDocumental.setString(2, documental.getDirector());
            stmtDocumental.setString(3, documental.getNarrador());

            stmtDocumental.executeUpdate();

            System.out.println("Documental guardado");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
