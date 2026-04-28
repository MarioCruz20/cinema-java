package paquete.play.contenido;

import paquete.play.plataforma.Genero;

import java.time.LocalDate;

//Declaración de la clase junto a sus atributos y metodos
public abstract class Contenido {
    //variable de ID de la tabla Contenido en DB
    private int contenidoID;

    private String titulo;
    private String descripcion;
    private int duracion;
    private Genero genero;
    //LocalDate solo puede almacenar año, mes y día, no toma hora, minutos ni segundos por si solo
    private LocalDate fechaEstreno;
    private double calificacion;
    private boolean disponible;

    //Constructor usualmente va despues de los atributos
    public Contenido(String titulo, int duracion, Genero genero){
        this.titulo = titulo;
        this.duracion = duracion;
        this.genero = genero;
        this.fechaEstreno = LocalDate.now();
        this.disponible = true;
    }

    public Contenido(String titulo, int duracion, Genero genero, double calificacion) {
        //Esto puede definirse tambien usando
        //otro cosntructor, en este caso el anterior Contenido
        //this.titulo = titulo;
        //this.duracion = duracion;
        //this.genero = genero;
        this(titulo, duracion, genero); //constructor dentro de otro constructor
        //This calificar recibe el parametro calificacion
        this.calificar(calificacion);
    }

    //Metodo para reproducir pelicula con un mensaje + el nombre de la pelicula
    public abstract void reproducir();

    //Metodo que retorna informacion de la pelicula
    public String obtenerFichaTecnica() {
        return "🎦🎥🍿🥤" + titulo + " (" + fechaEstreno + ")\n" +
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

    //getter para ID del Contenido (nuevo)
    public int getContenidoID() {
        return contenidoID;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getDuracion() {
        return duracion;
    }

    public Genero getGenero() {
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

    //SETTERS:
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    //setter para ID del Contenido (nuevo)
    public void setContenidoID(int contenidoID) {
        this.contenidoID = contenidoID;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
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