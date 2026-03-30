package paquete.play;

import paquete.play.contenido.Pelicula;
import paquete.play.plataforma.Plataforma;
import paquete.play.plataforma.Usuario;
import paquete.play.util.ScannerUtils;

import java.util.Scanner;

public class Main {

    //Clase 12: Constantes
    //Las constantes son valores que se definen 1 vez
    // y nunca pueden cambiar

    //Los nombres de las constantes se
    //escriben en mayuscula
    public static final String NOMBRE_PLATAFORMA = "CINEMA PLAY 🖐️😐🤚";
    public static final String VERSION = "1.0.0";
    //Constantes de opciones de menu while
    public static final int AGREGAR = 1;
    public static final int MOSTRAR_TODO = 2;
    public static final int BUSCAR_POR_TITULO = 3;
    public static final int ELIMINAR = 4;
    public static final int SALIR = 5;

    static void main(String[] args) {

        Plataforma plataforma = new Plataforma(NOMBRE_PLATAFORMA);
        System.out.println(NOMBRE_PLATAFORMA + " v" + VERSION);

        //Ejecuta 1 vez en lugar de hasta que termine el programa
        //porque esta antes que el while(true)
        cargarPeliculas(plataforma);

        //while true hace que el loop se ejecute
        //de manera infinita hasta que el usauario
        //salga del sistema
        while (true) {
            //Agregar opciones con triple comillas """
            int opcionElegida = ScannerUtils.capturarNumero("""
                    Ingrese una de las siguientes opciones:
                    1. Agregar contenido
                    2. Mostrar todo
                    3. Buscar por titulo
                    4. Eliminar
                    5. Salir
                    """);

            //Definir continuacion
            //Mostrar mensaje + la opcion elegida del usuario
            System.out.println("Opción elegida: " + opcionElegida);

            switch (opcionElegida) {
                case AGREGAR -> {
                    String nombre = ScannerUtils.capturaTexto("Nombre del contenido");
                    String genero = ScannerUtils.capturaTexto("Genero del contenido");
                    int duracion = ScannerUtils.capturarNumero("Duracion del contenido");
                    double calificacion = ScannerUtils.capturarDecimal("Calificacion del contenido");

                    //Instanciar pelicua importada DE src/paquete.play/contenido/Pelicula.java
                    // Pelicula(Los valores que recibira ese objeto)
                    plataforma.agregar(new Pelicula(nombre, duracion, genero, calificacion));

                }
                case MOSTRAR_TODO -> plataforma.mostrarTitutlo();
                //
                case BUSCAR_POR_TITULO -> {
                    String nombreBuscado = ScannerUtils.capturaTexto("Nombre del contenido a buscar: ");
                    Pelicula pelicula = plataforma.buscarPorTitulo(nombreBuscado);

                    if (pelicula != null) {
                        System.out.println(pelicula.obtenerFichaTecnica());
                    } else {
                        System.out.println(nombreBuscado + " no existe dentro de " + plataforma);
                    }
                }
                case ELIMINAR -> {
                    String nombreAEliminar = ScannerUtils.capturaTexto("Nombre del contenido a buscar: ");
                    Pelicula contenido = plataforma.buscarPorTitulo(nombreAEliminar);

                    if (contenido != null) {
                        plataforma.eliminar(contenido);
                        System.out.println(nombreAEliminar + " eliminado!");
                    } else {
                        System.out.println(nombreAEliminar + " no existe dentro de " + plataforma.getNombre());
                    }
                }
                case SALIR -> System.exit(0);
            }
        }
    }


    private static void cargarPeliculas(Plataforma plataforma) {
        plataforma.agregar(new Pelicula("Shrek", 90, "Animada"));
        plataforma.agregar(new Pelicula("Incepcion", 148, "Ciencia Ficción"));
        plataforma.agregar(new Pelicula("Titanic", 195, "Drama"));
        plataforma.agregar(new Pelicula("John Wick", 101, "Acción"));
        plataforma.agregar(new Pelicula("Coco", 105, "Animada"));
        plataforma.agregar(new Pelicula("Piratas del Caribe", 120, "Acción"));
        plataforma.agregar(new Pelicula("Star Wars", 110, "Ciencia Ficción"));
        plataforma.agregar(new Pelicula("Avengers", 140, "Acción"));
        plataforma.agregar(new Pelicula("Pulp Fiction", 124, "Policial"));
        plataforma.agregar(new Pelicula("Batman", 105, "da"));
    }
}
