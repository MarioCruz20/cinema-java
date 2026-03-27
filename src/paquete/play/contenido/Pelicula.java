package paquete.play.contenido;

import java.time.LocalDate;

//Declaración de la clase junto a sus atributos y metodos
public class Pelicula {
    public String titulo;
    public String descripcion;
    public int duracion;
    public String genero;
    //LocalDate solo puede almacenar año, mes y día, no toma hora, minutos ni segundos por si solo
    public LocalDate fechaEstreno;
    public double calificacion;
    public boolean disponible;

    //Metodo para reproducir pelicula con un mensaje + el nombre de la pelicula
    public void reproducir() {
        System.out.println("Reproduciendo " + titulo);
    }

    //Metodo que retorna informacion de la pelicula
    public String obtenerFichaTecnica() {
        return titulo + " (" + fechaEstreno + ")\n" +
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
}
