package paquete.play.plataforma;

import paquete.play.contenido.Pelicula;

import java.util.ArrayList;
import java.util.List;

public class Plataforma {
    private String nombre;
    private List<Pelicula> contenido; //Relacion por Agregacion

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
            System.out.println("🍿🥤" + pelicula.getTitulo());
        }
    }

    //metodo para eliminar peliculas
    public void eliminar(Pelicula elemento) {
        this.contenido.remove(elemento);
    }

    //Método para cargar 1 sola pelicula por título
    public Pelicula buscarPorTitulo(String titulo) {
        for (Pelicula pelicula : contenido) {
            //equalsIgnoreCase busca sin importar mayusculas o minusculas
            //del titulo que se manda como parametro
            if (pelicula.getTitulo().equalsIgnoreCase(titulo)) {
                return pelicula; //si se cumple la condicion retorna la pelicula
            }
        }
        return null; //Si no encuentra similitudes
                    // por titulo no retorna nada(null)
    }

    //Declaracion de getters para acceder a estos valores
    public String getNombre() {
        return nombre;
    }

    public List<Pelicula> getContenido() {
        return contenido;
    }
}
