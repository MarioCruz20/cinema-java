//Funcion de SannerUtil
// Sirve para recibir informacion y luego mostrarla en el sistema

//Una clase utilitaria no depende de ningun objeto
//solo de la clase en general, en este caso "ScannerUtils"
package paquete.play.util;

import java.util.Scanner;

//Para crear clases utilitarias
//y que no dependan del contexto del sitema (en este caso CINE)
public class ScannerUtils {
    public static Scanner scanner = new Scanner(System.in);
    //Static es para decirle que no depende de
    // otro objeto sino de la clase en general

    //Metodo para capturar texto
    public static String capturaTexto(String mensaje) {
        System.out.println(mensaje + ": ");
        return scanner.nextLine();
    }

    //Metodo para capturar un numero
    public static int capturarNumero(String mensaje) {
        System.out.println(mensaje + ": ");

        int dato = scanner.nextInt();
        scanner.nextLine();
        return dato;
    }

    //Metodo para capturar decimales
    public static double capturarDecimal(String mensaje){
        System.out.println(mensaje + ": ");

        double dato = scanner.nextDouble();
        scanner.nextLine();
        return dato;
    }
}
