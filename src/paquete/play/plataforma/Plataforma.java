package paquete.play.plataforma;

import paquete.play.contenido.Pelicula;

import java.util.List;

public class Plataforma {
    private String nombre;
    private List<Pelicula> contenido;

    //Constructor que recibe el nombre
    public Plataforma(String nombre) {
        this.nombre = nombre;
    }

    //metodo que no retorne nada
    public void agregar(Pelicula elemento) {
        this.contenido.add(elemento);
    }
}
