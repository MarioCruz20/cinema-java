package paquete.play.excepcion;

import paquete.play.contenido.Pelicula;

public class PeliculaExistenteException extends RuntimeException { //RuntimeException dice que es una excepcion que puede lanzarse desde mi aplicacion
//RuntimeException muestra excepciones en tiempor de ejecucion
    //constructor
    public PeliculaExistenteException(String titulo) {
        super("La pelicula " + titulo + " ya existe.");
    }
}
