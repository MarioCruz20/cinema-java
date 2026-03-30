package paquete.play.contenido;

import java.time.LocalDate;

//Declaración de la clase junto a sus atributos y metodos
public class Pelicula {
    private String titulo;
    private String descripcion;
    private int duracion;
    private String genero;
    //LocalDate solo puede almacenar año, mes y día, no toma hora, minutos ni segundos por si solo
    private LocalDate fechaEstreno;
    private double calificacion;
    private boolean disponible;

    //Constructor usualmente va despues de los atributos
    public Pelicula(String titulo, int duracion, String genero){
        this.titulo = titulo;
        this.duracion = duracion;
        this.genero = genero;
        this.fechaEstreno = LocalDate.now();
        this.disponible = true;
    }

    public Pelicula(String titulo, int duracion, String genero, double calificacion) {
        //Esto puede definirse tambien usando
        //otro cosntructor, en este caso el anterior Pelicula
        //this.titulo = titulo;
        //this.duracion = duracion;
        //this.genero = genero;
        this(titulo, duracion, genero); //constructor dentro de otro constructor
        //This calificar recibe el parametro calificacion
        this.calificar(calificacion);
    }

    //Metodo para reproducir pelicula con un mensaje + el nombre de la pelicula
    public void reproducir() {
        System.out.println("Reproduciendo " + titulo);
    }

    //Metodo que retorna informacion de la pelicula
    public String obtenerFichaTecnica() {
        return "🎥🍿🥤" + titulo + " (" + fechaEstreno + ")\n" +
                "Genero: " + genero + "\n" +
                "Calificación: " + calificacion + "/5";

    }

    //Metodo para obtener calificacion de la pelicula
    public void calificar(double calificacion) {
        if (calificacion >= 0 && calificacion <= 5) {
            this.calificacion = calificacion;
        }
    }

    //Metodo que retorna si es popular
    public boolean esPopular() {

        return calificacion >= 4;
    }

    //Getters y Setters

    //La misma clase si puede acceder a sus atributos privados
    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getDuracion() {
        return duracion;
    }

    public String getGenero() {
        return genero;
    }

    public LocalDate getFechaEstreno() {
        return fechaEstreno;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public void setFechaEstreno(LocalDate fechaEstreno) {
        this.fechaEstreno = fechaEstreno;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}