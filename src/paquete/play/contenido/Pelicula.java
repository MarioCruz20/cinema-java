package paquete.play.contenido;

import paquete.play.plataforma.Genero;

//Hereda los atributos y metodos de Contenido
public class Pelicula extends Contenido{

    //Crear constructor para que tenga los mismos atributos
    public Pelicula(String titulo, int duracion, Genero genero, double calificacion) {
        super(titulo, duracion, genero, calificacion);
    }

    //es para sobrescribir un metodo de la clase padre
    @Override
    public void reproducir() {
        System.out.printf("Reproduciendo la pelicula " + getTitulo() + "\n");

    }
}
