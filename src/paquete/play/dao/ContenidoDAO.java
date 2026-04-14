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
                System.out.println( //Imprime en un sout la informacion de la tabla "Contenido"
                        rs.getInt("contenidoID") + " - " +
                                rs.getString("titulo") + " - " +
                                rs.getString("genero") + " - " +
                                rs.getInt("duracion") + " - " +
                                rs.getString("tipo") + " - " +
                                rs.getDouble("calificacion")
                );

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Metodo para buscar contenido por tipo: pelicula o documental
    public void buscarPorTipo(String tipo) {
        String sql = "SELECT * FROM Contenido WHERE tipo = ?";

        try(Connection conn = ConexionDB.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tipo);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println( //Imprime la informacion del contenido segun su tipo
                rs.getInt("contenidoID") + " - " +
                        rs.getString("titulo") + " - " +
                        rs.getString("genero") + " - " +
                        rs.getInt("duracion") + " - " +
                        rs.getDouble("calificacion")
                        );
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }


    }


}
