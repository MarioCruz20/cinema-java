package paquete.play;

import paquete.play.contenido.Contenido;
import paquete.play.contenido.Documental;
import paquete.play.contenido.Pelicula;
import paquete.play.contenido.ResumenContenido;
import paquete.play.dao.ContenidoDAO;
import paquete.play.dao.DocumentalDAO;
import paquete.play.dao.PeliculaDAO;
import paquete.play.excepcion.PeliculaExistenteException;
import paquete.play.plataforma.Genero;
import paquete.play.plataforma.Plataforma;
import paquete.play.util.ConexionDB;
import paquete.play.util.FileUtils;
import paquete.play.util.ScannerUtils;

import java.sql.Connection;
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
    public static  final int REPRODUCIR = 6;
    public static final int BUSCAR_POR_TIPO = 7;
    public static final int ELIMINAR = 8;
    public static final int SALIR = 9;

    static void main(String[] args) {

        Plataforma plataforma = new Plataforma(NOMBRE_PLATAFORMA);
        System.out.println(NOMBRE_PLATAFORMA + " v" + VERSION);

        //Ejecuta 1 vez en lugar de hasta que termine el programa
        //porque esta antes que el while(true)
        cargarPeliculas(plataforma);

        System.out.println("Más de " + plataforma.getDuracionTotal() + " minutos de contenido! \n");

        //Mostrar mensaje de contenido promocional(documentales, por ahora)
        plataforma.getContenidoPromocionable(). forEach(promocionales -> System.out.println(promocionales.promocionar()));

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
                    6. Reproducir
                    7. Buscar por tipo
                    8. Eliminar
                    9. Salir
                    """);


            //Definir continuacion
            //Mostrar mensaje + la opcion elegida del usuario
            //System.out.println("Opción elegida: " + opcionElegida);

            switch (opcionElegida) {
                case AGREGAR -> {
                    String nombre = ScannerUtils.capturaTexto("Nombre del contenido");
                    int tipoDeContenido = ScannerUtils.capturarNumero("Que tipo de contenido quieres agregar?\n1. Pelicula\n2. Documental");
                    int duracion = ScannerUtils.capturarNumero("Duracion del contenido");
                    Genero genero = ScannerUtils.capturarGenero("Genero del contenido");
                    double calificacion = ScannerUtils.capturarDecimal("Calificacion del contenido");
                    String director = ScannerUtils.capturaTexto("Nombre del director");

                    //Intentar agregar la plataforma
                    try {
                        if(tipoDeContenido == 1) {
                            Pelicula pelicula = new Pelicula(
                                    nombre,
                                    duracion,
                                    genero,
                                    calificacion,
                                    director
                            );

                            PeliculaDAO peliculaDAO = new PeliculaDAO();
                            peliculaDAO.guardar(pelicula);

                        } else {
                            String narrador = ScannerUtils. capturaTexto("Narrador del documental"); //preguntar por narrador

                            Documental documental = new Documental(
                                    nombre,
                                    duracion,
                                    genero,
                                    calificacion,
                                    director,
                                    narrador
                            );


                            DocumentalDAO documentalDAO = new DocumentalDAO();
                            documentalDAO.guardar(documental);

                        }
                    } catch(PeliculaExistenteException e) { //si no se pudo agregar plataforma
                        System.out.println(e.getMessage()); //se captura la excepcion y retorna mensaje de la excepcion
                    }
                }
                case MOSTRAR_TODO -> {
                    //Imprime la lista por cada elemento con todos sus valores
                    ContenidoDAO contenidoDAO = new ContenidoDAO();
                    contenidoDAO.listar();
                }
                case BUSCAR_POR_TITULO -> {
                    String nombreBuscado = ScannerUtils.capturaTexto("Nombre del contenido a buscar: ");

                    ContenidoDAO contenidoDAO = new ContenidoDAO();
                    contenidoDAO.buscarPorTitulo(nombreBuscado);

                    //Contenido pelicula = plataforma.buscarPorTitulo(nombreBuscado);

                    //if (pelicula != null) {
                      //  System.out.println(pelicula.obtenerFichaTecnica());
                    //} else {
                      //  System.out.println(nombreBuscado + " no existe dentro de " + plataforma);
                    //}
                }
                case BUSCAR_POR_GENERO -> {
                    //Para permitir a usuario ingresar genero a buscar
                    String generoBuscado = ScannerUtils.capturaTexto("Genero del contenido a buscar");

                    ContenidoDAO contenidoDAO = new ContenidoDAO();
                    contenidoDAO.buscarPorGenero(generoBuscado);

                    //Lista de peliculas
                    //List<Contenido> contenidoPorGenero = plataforma.buscarPorGenero(generoBuscado);
                    //contenidoPorGenero.forEach(contenido -> System.out.println(contenido.obtenerFichaTecnica() + "\n"));
                }
                case VER_POPULARES -> { //Devuelve lista de peliculas ordenadas por calificacion
                    //captura cantidad y la manda a getPopulares y la aplica como limite de cuantas peliculas mostrar
                    int cantidad = ScannerUtils.capturarNumero("Cantidad de resultados a mostrar");

                    ContenidoDAO contenidoDAO = new ContenidoDAO();
                    contenidoDAO.verPopulares(cantidad);

                    //List<Contenido> contenidoPopulares = plataforma.getPopulares(cantidad); //Ordena peliculas de mayor a menor calificacion
                    //contenidoPopulares.forEach(contenido -> System.out.println(contenido.obtenerFichaTecnica() + "\n"));
                }
                case REPRODUCIR ->  {
                    //nombre a buscar de pelicula
                    String nombre = ScannerUtils.capturaTexto("Nombre del contenido a reproducir");
                    Contenido contenido = plataforma.buscarPorTitulo(nombre); //Para buscar la pelicula

                    if(contenido != null) { //controlar que la pelicula existe
                        plataforma.reproducir(contenido); //si existe la reproduce
                    } else {
                        System.out.println(nombre + " no existe");
                        //si no existe muestra nombre de pelicua a buscar + mensaje
                    }
                }

                case BUSCAR_POR_TIPO -> {
                    //Obtener tipo de contenido
                    int tipoDeContenido = ScannerUtils.capturarNumero(
                            "Que tipo de contenido quieres agregar?\n1. Pelicula\n2. Documental");

                    ContenidoDAO contenidoDAO = new ContenidoDAO();

                    if(tipoDeContenido == 1) {
                        contenidoDAO.buscarPorTipo("Pelicula");

                        //List<Pelicula> peliculas = plataforma.getPeliculas();
                        //peliculas.forEach(pelicula -> System.out.println(pelicula.obtenerFichaTecnica() + "\n"));
                    } else {
                        contenidoDAO.buscarPorTipo("Documental");
                        //List<Documental> documentales = plataforma.getDocumental();
                        //documentales.forEach(pelicula -> System.out.println(pelicula.obtenerFichaTecnica() + "\n"));

                    }

                }

                case ELIMINAR -> {
                    String nombreAEliminar = ScannerUtils.capturaTexto("Nombre del contenido a buscar: \n");
                    Contenido contenido = plataforma.buscarPorTitulo(nombreAEliminar);

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

    private static void cargarPeliculas(Plataforma plataforma) { //Nueva forma de cargar las peliculas

        //Al iniciar el programa en Main y cuando se llama a cargarPeliculas
        //Se muestra el listado de peliculas linea por linea de contenido.txt
        plataforma.getContenido().addAll(FileUtils.leerContenido());
    }
}

