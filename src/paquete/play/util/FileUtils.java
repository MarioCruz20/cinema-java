package paquete.play.util;

import paquete.play.contenido.Pelicula;
import paquete.play.plataforma.Genero;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//FileUtils es el encargado de leer la lista de contenido.txt
//contenido.txt es un archivo plano
public class FileUtils {

    //Definir valores en constantes
    public static final String NOMBRE_ARCHIVO = "contenido.txt";
    public static final String SEPARADOR = "|";


    public static List<Pelicula> leerContenido() {
        //Crear lista vacia de peliculas e inicializar con new ArrayList
        List<Pelicula> contenidoDesdeArchivo = new ArrayList<>();

        try {
            //Como leer un archivo plano (Proyecto Java>contenido.txt):
            List<String> lineas = Files.readAllLines(Paths.get(NOMBRE_ARCHIVO)); //Obtener lineas en una lista para lectura de archivo plano

            lineas.forEach(linea -> {
                String[] datos = linea.split("\\" + SEPARADOR);

                if (datos.length == 5) { //validar que datos tenga 5 elementos, de lo contrario no se tendra en cuenta
                    //Capturar datos

                    String titulo = datos[0];
                    int duracion = Integer.parseInt(datos[1]);
                    Genero genero = Genero.valueOf(datos[2].toUpperCase());
                    // captura de calificacion. Si No tiene, se asigna valor 0
                    //datos[3].isBlank() ? 0         Pregunta con ?
                    double calificacion = datos[3].isBlank() ? 0 : Double.parseDouble(datos[3]);
                    //y SI tiene calificacion se parsea a Double
                    //Double.parseDouble(datos[3])
                    LocalDate fechaEstreno = LocalDate.parse(datos[4]);

                    //Instanciar pelicula y fecha de estreno
                    Pelicula pelicula = new Pelicula(titulo, duracion, genero, calificacion);
                    pelicula.setFechaEstreno(fechaEstreno);

                    contenidoDesdeArchivo.add(pelicula); //agrega pelicula
                }
            });
        } catch(IOException e) {
            System.out.println("Error leyendo el archivo. " + e.getMessage());
        }

        return contenidoDesdeArchivo; //Luego de try catch retorna lista de contenido
    }

}
