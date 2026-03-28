//Funcion de SannerUtil
// Sirve para recibir informacion y luego mostrarla en el sistema

//Una clase utilitaria no depende de ningun objeto
//solo de la clase en general, en este caso "ScannerUtils"
package paquete.play.util;

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

        int dato = SCANNER.nextInt();
        SCANNER.nextLine();
        return dato;
    }

    //Metodo para capturar decimales
    public static double capturarDecimal(String mensaje){
        System.out.println(mensaje + ": ");

        double dato = SCANNER.nextDouble();
        SCANNER.nextLine();
        return dato;
    }
}
