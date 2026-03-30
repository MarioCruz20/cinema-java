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

    public void mostrarTitutlo() {
        //for para recorrer los elementos de la lista contenido
        //reccore todo el contenido y muestra cada una de las peliculas
        //for (Pelicula pelicula : contenido) {
        //  System.out.println("🍿🥤" + pelicula.getTitulo());
        //}

        //Expresion lambda -> para usar una funcion en 1 sola linea
        contenido.forEach(contenido -> System.out.println(contenido.getTitulo()));

    }

    //metodo para eliminar peliculas
    public void eliminar(Pelicula elemento) {
        this.contenido.remove(elemento);
    }

    //Método para cargar 1 sola pelicula por título
    public Pelicula buscarPorTitulo(String titulo) {
        //Enfoque funcional
        //stream es como una linea transportadora
        //que toma cada elemento y aplica condicionales
        //El filtro es que el nombre coincida el titulo que recibe el metodo
        return contenido.stream()
                .filter(contenido -> contenido.getTitulo().equalsIgnoreCase(titulo))
                .findFirst()
                .orElse(null); //else, si no existe ese titulo retorna null

    }

    //metodo para buscar genero por mensaje del usuario2

    public List<Pelicula> buscarPorGenero(String genero) {
        return contenido.stream()
                .filter(contenido -> contenido.getGenero().equalsIgnoreCase(genero))
                .toList(); //para convertir el filtro en una lista de pelicula

    }

    //Declaracion de getters para acceder a estos valores
    public String getNombre() {
        return nombre;
    }

    public List<Pelicula> getContenido() {
        return contenido;
    }
}
