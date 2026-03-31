package paquete.play;

import paquete.play.contenido.Pelicula;
import paquete.play.excepcion.PeliculaExistenteException;
import paquete.play.plataforma.Genero;
import paquete.play.plataforma.Plataforma;
import paquete.play.plataforma.Usuario;
import paquete.play.util.ScannerUtils;

import java.util.List;
import java.util.Scanner;

public class Main {

    //Constantes en MAYUSCULAS
    public static final String NOMBRE_PLATAFORMA = "CINEMA PLAY 🖐️😐🤚";
    public static final String VERSION = "1.0.0";
    //Constantes de opciones de menu while
    public static final int AGREGAR = 1;
    public static final int MOSTRAR_TODO = 2;
    public static final int BUSCAR_POR_TITULO = 3;
    public static final int BUSCAR_POR_GENERO = 4;
    public static final int VER_POPULARES = 5;
    public static final int ELIMINAR = 8;
    public static final int SALIR = 9;

    static void main(String[] args) {

        Plataforma plataforma = new Plataforma(NOMBRE_PLATAFORMA);
        System.out.println(NOMBRE_PLATAFORMA + " v" + VERSION);

        //Ejecuta 1 vez en lugar de hasta que termine el programa
        //porque esta antes que el while(true)
        cargarPeliculas(plataforma);

        System.out.println("Más de " + plataforma.getDuracionTotal() + " minutos de contenido! \n");
        //while true hace que el loop se ejecute
        //de manera infinita hasta que el usuario
        //salga del sistema
        while (true) {
            //Agregar opciones con triple comillas """
            int opcionElegida = ScannerUtils.capturarNumero("""
                    Ingrese una de las siguientes opciones:
                    1. Agregar contenido
                    2. Mostrar todo
                    3. Buscar por titulo
                    4. Buscar por genero
                    5. Ver populares
                    8. Eliminar
                    9. Salir
                    """);

            //Definir continuacion
            //Mostrar mensaje + la opcion elegida del usuario
            //System.out.println("Opción elegida: " + opcionElegida);

            switch (opcionElegida) {
                case AGREGAR -> {
                    String nombre = ScannerUtils.capturaTexto("Nombre del contenido");
                    Genero genero = ScannerUtils.capturarGenero("Genero del contenido");
                    int duracion = ScannerUtils.capturarNumero("Duracion del contenido");
                    double calificacion = ScannerUtils.capturarDecimal("Calificacion del contenido");

                    //Intentar agregar la plataforma
                    try {
                        //Instanciar pelicula importada DE src/paquete.play/contenido/Pelicula.java
                        // Pelicula(Los valores que recibira ese objeto)
                        plataforma.agregar(new Pelicula(nombre, duracion, genero, calificacion));

                    } catch(PeliculaExistenteException e) { //si no se pudo agregar plataforma
                        System.out.println(e.getMessage()); //se captura la excepcion y retorna mensaje de la excepcion
                    }
                }
                case MOSTRAR_TODO -> {
                    List<String> titulos = plataforma.getTitulos();
                    titulos.forEach(System.out::println); //Imprimir cada elemento por titulo
                }
                case BUSCAR_POR_TITULO -> {
                    String nombreBuscado = ScannerUtils.capturaTexto("Nombre del contenido a buscar: ");
                    Pelicula pelicula = plataforma.buscarPorTitulo(nombreBuscado);

                    if (pelicula != null) {
                        System.out.println(pelicula.obtenerFichaTecnica());
                    } else {
                        System.out.println(nombreBuscado + " no existe dentro de " + plataforma);
                    }
                }
                case BUSCAR_POR_GENERO -> {
                    //Para permitir a usuario ingresar genero a buscar
                    Genero generoBuscado = ScannerUtils.capturarGenero("Genero del contenido a buscar");

                    //Lista de peliculas
                    List<Pelicula> contenidoPorGenero = plataforma.buscarPorGenero(generoBuscado);
                    contenidoPorGenero.forEach(contenido -> System.out.println(contenido.obtenerFichaTecnica() + "\n"));
                }
                case VER_POPULARES -> { //Devuelve lista de peliculas ordenadas por calificacion
                    //captura cantidad y la manda a getPopulares y la aplica como limite de cuantas peliculas mostrar
                    int cantidad = ScannerUtils.capturarNumero("Cantidad de resultados a mostrar");
                    List<Pelicula> contenidoPopulares = plataforma.getPopulares(cantidad); //Ordena peliculas de mayor a menor calificacion
                    contenidoPopulares.forEach(contenido -> System.out.println(contenido.obtenerFichaTecnica() + "\n"));
                }

                case ELIMINAR -> {
                    String nombreAEliminar = ScannerUtils.capturaTexto("Nombre del contenido a buscar: \n");
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
        plataforma.agregar(new Pelicula("Shrek", 90, Genero.ANIMADA, 2.3)); //Cambio de genero por enum Genero
        plataforma.agregar(new Pelicula("Incepcion", 148, Genero.CIENCIA_FICCION,4));
        plataforma.agregar(new Pelicula("Titanic", 195, Genero.DRAMA,1));
        plataforma.agregar(new Pelicula("John Wick", 101, Genero.ACCION,5));
        plataforma.agregar(new Pelicula("Coco", 105, Genero.ANIMADA,3.5));
        plataforma.agregar(new Pelicula("Piratas del Caribe", 120, Genero.ACCION,3.4));
        plataforma.agregar(new Pelicula("Star Wars", 110, Genero.CIENCIA_FICCION,4.2));
        plataforma.agregar(new Pelicula("Avengers", 140, Genero.ACCION,3));
        plataforma.agregar(new Pelicula("Pulp Fiction", 124, Genero.ACCION,2.2));
        plataforma.agregar(new Pelicula("Batman", 105, Genero.ACCION,3.7));
    }
}
