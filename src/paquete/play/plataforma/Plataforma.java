package paquete.play.plataforma;

import paquete.play.contenido.Contenido;
import paquete.play.contenido.ResumenContenido;
import paquete.play.excepcion.PeliculaExistenteException;
import paquete.play.util.FileUtils;

import java.util.*;

public class Plataforma {
    private String nombre;
    private List<Contenido> contenido; //Relacion por Agregacion
    private Map<Contenido, Integer> visualizaciones; //Map que recibe <Key, Value>

    //Constructor que recibe el nombre
    public Plataforma(String nombre) {
        this.nombre = nombre;
        //Inicializar contenido, y como inicializar 1 lista
        this.contenido = new ArrayList<>();
        this.visualizaciones = new HashMap<>();
    }

    //metodo que no retorne nada
    public void agregar(Contenido elemento) {
        //se le manda el titulo del elemento a a agregar
        Contenido contenido = this.buscarPorTitulo(elemento.getTitulo());
        if (contenido != null) { //si encuentra contenido
            throw new PeliculaExistenteException(elemento.getTitulo());//manda el titulo de esa pelicula
            //throw para lanzar la excepcion si la pelicula YA EXISTE
        }//si la excepcion ocurre no se crea el contenido

        FileUtils.escribirContenido(elemento);
        this.contenido.add(elemento);
    }

    //Metodo para controlar cuantas visualizaciones ha tenido una pelicula
    public void reproducir(Contenido contenido) {
        int conteoActual = visualizaciones.getOrDefault(contenido, 0);
        System.out.println(contenido.getTitulo() + " ha sido reproducido " + conteoActual + " veces.");
        // .put para poner clave dentro del map,
        // enviando Key(pelicula conteo y conteo actual + 1)
        this.contarVisualiacion(contenido);
        contenido.reproducir();
    }

    //Metodo auxiliar que solo funcione en Plataforma.java
    public void contarVisualiacion(Contenido contenido) {
        int conteoActual = visualizaciones.getOrDefault(contenido, 0);
        visualizaciones.put(contenido, conteoActual + 1);

    }


    //Lista de strings
    public List<String>getTitulos() {
        return contenido.stream() //retornar lista de titulos
                //Tranforma una lista en un objeto distinto
                .map(Contenido::getTitulo)
                .toList();
    }

    //Para retornar lista de resumenes de contenido
    public List<ResumenContenido> getResumenes() {
       return contenido.stream()
               .map(c -> new ResumenContenido(c.getTitulo(), c.getDuracion(), c.getGenero()))
               .toList();
       //Se crea un nuevo resumen de contenido con titulo, duracion y genero
        //toList() lo agrega a una lista para retornarla
    }

    //metodo para eliminar peliculas
    public void eliminar(Contenido elemento) {
        this.contenido.remove(elemento);
    }

    //Método para cargar 1 sola pelicula por título
    public Contenido buscarPorTitulo(String titulo) {
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

    public List<Contenido> buscarPorGenero(Genero genero) {
        return contenido.stream()
                .filter(contenido -> contenido.getGenero().equals(genero))
                .toList(); //para convertir el filtro en una lista de pelicula

    }

    //Metodo para ordenar peliculas por calificacion
    public List<Contenido> getPopulares(int cantidad) {
        return contenido.stream()
                .sorted(Comparator.comparingDouble(Contenido::getCalificacion).reversed())
                .limit(cantidad)
                .toList();
    }

    public int getDuracionTotal() {
        return contenido.stream()
                .mapToInt(Contenido::getDuracion) //Convierte
                .sum(); //Suma las horas de las
                        //todas las peliculas en la lista
    }

    //Declaracion de getters para acceder a estos valores
    public String getNombre() {
        return nombre;
    }

    public List<Contenido> getContenido() {
        return contenido;
    }
}
