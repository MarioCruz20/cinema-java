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

        //Menu
        //1. Agregar contenido
        //2. Mostrar Todo en la plataforma
        //3. Buscar por titulo
        //4. Eliminar
        //5. Salir

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
                case MOSTRAR_TODO ->plataforma.mostrarTitutlo();
                case BUSCAR_POR_TITULO -> {

                }
                case ELIMINAR -> {

                }
                case SALIR -> System.exit(0);
            }

            if (opcionElegida == AGREGAR) {
                //Instanciar ScannerUtil para estaticas
            } else if (opcionElegida == MOSTRAR_TODO) {
            } else if (opcionElegida == BUSCAR_POR_TITULO) {
                //falta
            } else if (opcionElegida == ELIMINAR) {

            }
            //Si el usuario elige la opcion 5(SALIR)
            //sale del sistema gracias a System.exit()
            else if (opcionElegida == SALIR) {
                System.exit(0); //status por defecto 0
            }
        }

        //16: ANTES FUNCIONABA CON LO SIGUIETE:
        //Instanciar ScannerUtil para estaticas
//        String nombre = ScannerUtils.capturaTexto("Nombre del contenido");
//        String genero = ScannerUtils.capturaTexto("Genero del contenido");
//        int duracion = ScannerUtils.capturarNumero("Duracion del contenido");
//        double calificacion = ScannerUtils.capturarDecimal("Calificacion del contenido");
//
//        //Instanciar pelicua importada DE src/paquete.play/contenido/Pelicula.java
//
//        // Pelicula(Los valores que recibira ese objeto)
//        Pelicula pelicula = new Pelicula(nombre, duracion, genero, calificacion);
//
//        Pelicula pelicula2 = new Pelicula("F1 the movie", 220, "Accion");
//
//        //QUE SE MUESTRA EN CONSOLA:
//
//        //Agregar pelicula con metodo agregar
//        //y se manda la pelicula
//        plataforma.agregar(pelicula);
//        plataforma.agregar(pelicula2);
//        System.out.println("Numero de elementos en la plataforma: " + plataforma.getContenido().size());
//
//        plataforma.eliminar(pelicula2);
//
//        plataforma.mostrarTitutlo();
//
//        pelicula.setDescripcion("Pelicula de fantasia");
//
//        Usuario usuario = new Usuario("Mario", "mario0534@gmail.com");
//
//        System.out.println("🍿🎞️Cinema Blazing Star🎬📽️🖐️😐🤚");
//
//        //Muestra año, mes, dia, hora, minutos, segundos de la fecha de registro de la pelicula
//        //usa sout porque es una varibale con valores
//        System.out.println(usuario.fechaRegistro);
//
//        //Ejecuta metodo ver de usuario.java. Se ejecuta asi porque es un metodo
//        usuario.ver(pelicula);


        //ANTIGUA FORMA DE HACERLO SIN CONSTRUCTORES Y DEMAS CLASES.java
        //pelicula.titulo = nombre;
        //pelicula.fechaEstreno = LocalDate.of(2018, 10, 15);
        //pelicula.genero = genero;
        //pelicula.calificar(calificacion);
        //pelicula.duracion = duracion;

        //Como convertir un tipo de valor a otro
        //Por ejemplo entero a decimal
        //Esto se llama casteo (casting)
        //puede ser explicito o implicito

        //pelicula.duracion = 120;

        //long duracionLong = pelicula.duracion;

        //Como se hace un casting:
        //Se dice que el valor "calificacion" se muestre como entero
        //apesar de ser un valor decimal originalmente

        //int calificacionInt = (int) pelicula.calificacion;
        //long numeroDePremios = Long.parseLong("25");

        //Casting implicito: es el que hace java por si solo
        //Casting explicito: es el que uno mismo declara como debe ser con codigo

        //System.out.println("Duracion Long: " + duracionLong);
        //System.out.println("Calificacion Int: " + calificacionInt);
        //System.out.println("Numero de premios: " + numeroDePremios);

        //Imprimir ficha tecnica de la pelicula
        //System.out.println(pelicula.obtenerFichaTecnica());
        //Mostrar pero usuario
        //usuario.nombre = "Mario";  ESTAS 2 LINEAS SOBRAN GRACIAS AL CONSTRUCTOR EN Usuario en Usuario.java
        //usuario.fechaRegistro = LocalDateTime.of(2025, 12, 24, 17, 15, 14);

    }
}
