package paquete.play.excepcion;

public class PeliculaExistenteException extends RuntimeException { //RuntimeException dice que es una excepcion que puede lanzarse desde mi aplicacion
//RuntimeException muestra excepciones en tiempor de ejecucion
    //constructor
    public PeliculaExistenteException(String titulo) {
        super("El contenido ya existe " + titulo + " ya existe.");
    }
}
