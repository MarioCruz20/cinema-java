package paquete.play.util;

import paquete.play.contenido.Contenido;
import paquete.play.contenido.Documental;
import paquete.play.contenido.Pelicula;
import paquete.play.plataforma.Genero;

import javax.print.Doc;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//FileUtils es el encargado de leer la lista de contenido.txt
//contenido.txt es un archivo plano
public class FileUtils {

    //Definir valores en constantes
    public static final String NOMBRE_ARCHIVO = "contenido.txt";
    public static final String SEPARADOR = "|";


    //Como convertir texto capturado para escribir un nuevo contenido dentro del archivo plano
    public static void escribirContenido(Contenido contenido) {
        String linea = String.join(SEPARADOR,
                contenido.getTitulo(),
                String.valueOf(contenido.getDuracion()), //Por ser numero se pasa a string
                contenido.getGenero().name(),
                String.valueOf(contenido.getCalificacion()),
                contenido.getFechaEstreno().toString()
        );

        String lineaFinal;

        if(contenido instanceof Documental documental) { //Si el contenido a guardar es un documental
            lineaFinal = "DOCUMENTAL" + SEPARADOR + linea + SEPARADOR + documental.getNarrador();

        } else { //si no es un documental a agregar
            lineaFinal = "PELICULA" + SEPARADOR + linea;
        }

        try {
            Files.writeString(Paths.get(NOMBRE_ARCHIVO), //abre el path donde se quiere escribir la pelicula
                    lineaFinal + System.lineSeparator(), //Para agregar ENTER despues de cada linea
                    StandardOpenOption.CREATE, //si el archivo no existe lo crea
                    StandardOpenOption.APPEND //manda el contenido al final de la lista
            );
        } catch (IOException e) {
            System.out.println("Error escribiendo al archivo. " + e.getMessage());
    }
    }

    public static List<Contenido> leerContenido() {
        //Crear lista vacia de peliculas e inicializar con new ArrayList
        List<Contenido> contenidoDesdeArchivo = new ArrayList<>();

        try {
            //Como leer un archivo plano (Proyecto Java>contenido.txt):
            List<String> lineas = Files.readAllLines(Paths.get(NOMBRE_ARCHIVO)); //Obtener lineas en una lista para lectura de archivo plano

            lineas.forEach(linea -> {
                String[] datos = linea.split("\\" + SEPARADOR);

                //Capturar primero el tipo de dato
                String tipoContenido = datos[0];

                //Validar si es pelicula y tenga 6 campos. Validar si es documental y tenga 7 campos
                if (("PELICULA".equals(tipoContenido) && datos.length == 6) || ("DOCUMENTAL".equals(tipoContenido) && datos.length == 7)) {
                    //Capturar datos

                    String titulo = datos[1];
                    int duracion = Integer.parseInt(datos[2]);
                    Genero genero = Genero.valueOf(datos[3].toUpperCase());
                    // captura de calificacion. Si No tiene, se asigna valor 0
                    //datos[3].isBlank() ? 0         Pregunta con ?
                    double calificacion = datos[4].isBlank() ? 0 : Double.parseDouble(datos[4]);
                    //y SI tiene calificacion se parsea a Double
                    //Double.parseDouble(datos[3])
                    LocalDate fechaEstreno = LocalDate.parse(datos[5]);

                    //Instanciar contenido y fecha de estreno
                    Contenido contenido;

                    if("PELICULA".equals(tipoContenido)) {
                        //inicializacion de varibale para pelicula
                        contenido = new Pelicula(titulo, duracion, genero, calificacion);
                    } else {
                        String narrador = datos[6]; //6 en indice de lista porque en esa posicion
                                                    //se encuentra el narrador
                        //inicializacion para documental
                        contenido = new Documental(titulo,duracion,genero,calificacion,narrador);
                    }
                    contenido.setFechaEstreno(fechaEstreno);

                    contenidoDesdeArchivo.add(contenido); //agrega contenido dentro de la plataforma
                }
            });
        } catch(IOException e) {
            System.out.println("Error leyendo el archivo. " + e.getMessage());
        }

        return contenidoDesdeArchivo; //Luego de try catch retorna lista de contenido
    }

}
