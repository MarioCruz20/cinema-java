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

    //Método para buscar contenido por título
    public void buscarPorTitulo(String titulo) {

        String sql = "SELECT * FROM Contenido WHERE titulo LIKE ?";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + titulo + "%");

            ResultSet rs = stmt.executeQuery();

            boolean encontrado = false;

            while (rs.next()) {
                encontrado = true;

                System.out.println(
                        rs.getInt("contenidoID") + " - " +
                                rs.getString("titulo") + " - " +
                                rs.getString("genero") + " - " +
                                rs.getInt("duracion") + " - " +
                                rs.getString("tipo") + " - " +
                                rs.getDouble("calificacion")
                );
            }

            if (!encontrado) {
                System.out.println("No se encontró contenido con ese título");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Método para buscar contenido por género
    public void buscarPorGenero(String genero) {

        String sql = "SELECT * FROM Contenido WHERE genero LIKE ?";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + genero.toUpperCase() + "%" );

            ResultSet rs = stmt.executeQuery();

            boolean encontrado = false;

            while (rs.next()) {
                encontrado = true;

                System.out.println(
                        rs.getInt("contenidoID") + " - " +
                                rs.getString("titulo") + " - " +
                                rs.getString("genero") + " - " +
                                rs.getInt("duracion") + " - " +
                                rs.getString("tipo") + " - " +
                                rs.getDouble("calificacion")
                );
            }

            if (!encontrado) {
                System.out.println("No se encontró contenido con ese género");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Método para ver contenido filtrado por populares
    public void verPopulares(int limite) {
        String sql = "SELECT TOP (?) * FROM Contenido ORDER BY calificacion DESC";

        try (Connection conn = ConexionDB.conectar();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, limite);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println(
                        rs.getString("titulo") + " - " +
                                rs.getDouble("calificacion")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Método para eliminar contenido, director y narrador de la base de datos
    public void eliminar(int id) {
        String sqlPelicula = "DELETE FROM Pelicula WHERE contenidoID = ?";
        String sqlDocumental = "DELETE FROM Documental WHERE contenidoID = ?";
        String sqlContenido = "DELETE FROM Contenido WHERE contenidoID = ?";

        try (Connection conn = ConexionDB.conectar()) {

            PreparedStatement stmt1 = conn.prepareStatement(sqlPelicula);
            stmt1.setInt(1, id);
            stmt1.executeUpdate();

            PreparedStatement stmt2 = conn.prepareStatement(sqlDocumental);
            stmt2.setInt(1, id);
            stmt2.executeUpdate();

            PreparedStatement stmt3 = conn.prepareStatement(sqlContenido);
            stmt3.setInt(1, id);
            stmt3.executeUpdate();

            System.out.println("Contenido eliminado");
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
