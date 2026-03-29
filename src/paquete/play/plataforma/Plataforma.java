package paquete.play.plataforma;

import paquete.play.contenido.Pelicula;

import java.util.ArrayList;
import java.util.List;

public class Plataforma {
    private String nombre;
    private List<Pelicula> contenido;

    //Constructor que recibe el nombre
    public Plataforma(String nombre) {
        this.nombre = nombre;
        //Inicializar contenido, y como inicializar 1 lista
        this.contenido = new ArrayList<>();
    }

    //metodo que no retorne nada
    public void agregar(Pelicula elemento) {
        this.contenido.add(elemento);
    }

    public void mostrarTitutlo(){
        //for para recorrer los elementos de la lista contenido
        for (Pelicula pelicula : contenido) {
            System.out.println(pelicula.getTitulo());
        }
    }

    //metodo
    public void eliminar(Pelicula elemento) {
        this.contenido.remove(elemento);
    }

    //Declaracion de getters
    public String getNombre() {
        return nombre;
    }

    public List<Pelicula> getContenido() {
        return contenido;
    }
}
