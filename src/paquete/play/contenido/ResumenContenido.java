package paquete.play.contenido;

import paquete.play.plataforma.Genero;

//Definir atributos de record dentro de ()
public record ResumenContenido(
        String titulo,
        int duracion,
        Genero genero) {

}
