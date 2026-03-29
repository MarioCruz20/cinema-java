package paquete.play;

import paquete.play.contenido.Pelicula;
import paquete.play.plataforma.Plataforma;
import paquete.play.plataforma.Usuario;
import paquete.play.util.ScannerUtils;

public class Main {
    //
    //Clase 12: Constantes
    //Las constantes son valores que se definen 1 vez
    // y nunca pueden cambiar

    //Los nombres de las constantes se
    //escriben en mayuscula
    public static final String NOMBRE_PLATAFORMA = "CINEMA PLAY 🖐️😐🤚";
    public static final String VERSION = "1.0.0";

    static void main(String[] args) {
        //Clase 15 Operaciones basicas con listas

        Plataforma plataforma = new Plataforma(NOMBRE_PLATAFORMA);
        System.out.println(NOMBRE_PLATAFORMA + " v" + VERSION);

        //Instanciar ScannerUtil para estaticas
        String nombre = ScannerUtils.capturaTexto("Nombre del contenido");
        String genero = ScannerUtils.capturaTexto("Genero del contenido");
        int duracion = ScannerUtils.capturarNumero("Duracion del contenido");
        double calificacion = ScannerUtils.capturarDecimal("Calificacion del contenido");

        //Instanciar pelicua importada DE src/paquete.play/contenido/Pelicula.java

        // Pelicula(Los valores que recibira ese objeto)
        Pelicula pelicula = new Pelicula(nombre, duracion, genero, calificacion);

        Pelicula pelicula2 = new Pelicula("F1 the movie", 220, "Accion");

        //Agregar pelicula con metodo agregar
        //y se manda la pelicula
        plataforma.agregar(pelicula);
        plataforma.agregar(pelicula2);
        System.out.println("Numero de elementos en la plataforma: " + plataforma.getContenido().size());

        plataforma.eliminar(pelicula2);

        plataforma.mostrarTitutlo();

        pelicula.setDescripcion("Pelicula de fantasia");

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
        Usuario usuario = new Usuario("Mario", "mario0534@gmail.com");
        //usuario.nombre = "Mario";  ESTAS 2 LINEAS SOBRAN GRACIAS AL CONSTRUCTOR EN Usuario en Usuario.java
        //usuario.fechaRegistro = LocalDateTime.of(2025, 12, 24, 17, 15, 14);

        //Que se ejecuta en terminal:

        //Mensaje
        System.out.println("🍿🎞️Cinema Blazing Star🎬📽️🖐️😐🤚");

        //Muestra año, mes, dia, hora, minutos, segundos de la fecha de registro de la pelicula
        //usa sout porque es una varibale con valores
        System.out.println(usuario.fechaRegistro);

        //Ejecuta metodo ver de usuario.java. Se ejecuta asi porque es un metodo
        usuario.ver(pelicula);

        //System.out.println(pelicula.obtenerFichaTecnica());



//        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("Cual es tu nombre?");
//        String nombre = scanner.nextLine();
//
//        System.out.println("Hola " + nombre + " esto es un msg");
//        System.out.println("Cuántos años tienes?");
//
//        int edad = scanner.nextInt();
//        System.out.println("Puedes ver contenido +" + edad);


    }
}
