//Funcion de SannerUtil
// Sirve para recibir informacion y luego mostrarla en el sistema

//Una clase utilitaria no depende de ningun objeto
//solo de la clase en general, en este caso "ScannerUtils"
package paquete.play.util;

import paquete.play.plataforma.Genero;

import java.util.Scanner;

//Para crear clases utilitarias
//y que no dependan del contexto del sitema (en este caso CINE)
public class ScannerUtils {
    public static final Scanner SCANNER = new Scanner(System.in);
    //Static es para decirle que no depende de
    // otro objeto sino de la clase en general

    //Metodo para capturar texto
    public static String capturaTexto(String mensaje) {
        System.out.println(mensaje + ": ");
        return SCANNER.nextLine();
    }

    //Metodo para capturar un numero
    public static int capturarNumero(String mensaje) {
        System.out.println(mensaje + ": ");

        //Despues de preguntar al usuario
        while (!SCANNER.hasNextInt()) {
            System.out.println("Dato no aceptado" + mensaje + ": ");
            SCANNER.next();
        }

        int dato = SCANNER.nextInt();
        SCANNER.nextLine();
        return dato;
    }

    //Metodo para capturar decimales
    public static double capturarDecimal(String mensaje){
        System.out.println(mensaje + ": ");

        //Despues de preguntar al usuario
        while (!SCANNER.hasNextDouble()) {
            System.out.println("Dato no aceptado" + mensaje + ": ");
            SCANNER.next();
        }

        double dato = SCANNER.nextDouble();
        SCANNER.nextLine();
        return dato;
    }

    //Scanner para capturar generos
    public static Genero capturarGenero(String mensaje) {
        while(true) { //Repite bucle hasta que se ingrese un genero correcto
            System.out.println(mensaje + "... Opciones:");
            for (Genero genero : Genero.values()) { //values() retorna lista de enums
                                                    //en el orden que se declararon
                System.out.println("-🎥🍿" + genero.name());//imprimir nombre de cada genero
            }

            System.out.println("Cuál quieres?");

            String entrada = SCANNER.nextLine(); //capturar texto

            try { //Trata de procesar retorno de genero
                return Genero.valueOf(entrada.toUpperCase()); //toUpperCase() convierte el texto a mayusculas
                                                            //accion = ACCION
            } catch (IllegalArgumentException e) { //captura error y muestra mensaje
                System.out.println("Genero no aceptado."); //Si da error vuelve a pedir genero de pelicula
            }
        }
    }
}
