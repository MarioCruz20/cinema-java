package paquete.play.contenido;

import paquete.play.plataforma.Genero;

public class Documental extends Contenido implements Promocionales{
    private String narrador; //atributo unico de Documental

    public Documental(String titulo, int duracion, Genero genero) {
        super(titulo, duracion, genero);
    }

    public Documental(String titulo, int duracion, Genero genero, double calificacion, String narrador) {
        super(titulo, duracion, genero, calificacion);
        this.narrador = narrador; //Inicializar atributo unico de Documental
                                  //despues del super
    }

    @Override
    public void reproducir() {
        System.out.println("Reproduciendo documental " + getTitulo() + " narrado por: " + getNarrador() + "\n");

    }

    @Override
    public String promocionar() {
        return "⭐🌟Descubre el documental " + this.getTitulo() + " narrador por " + narrador + ". Ahora en Cinema Play💫✨";
    }

    public String getNarrador() {
        return narrador;
    }
}
