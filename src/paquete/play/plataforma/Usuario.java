package paquete.play.plataforma;

//Importar paquete para usar atributos y metodos de Pelicula.java
import paquete.play.contenido.Pelicula;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Usuario {
    public String nombre;
    public String email;
    public LocalDateTime fechaRegistro;

    public Usuario(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
        this.fechaRegistro = LocalDateTime.now(); //fechaRegistro no se coloca en la primera
        // linea del constructor porque ese valor ya lo da por defecto LacolDateTime
    }

    //void es para que no retorne nada
    //La clase Pelicula puede ser usada como un tipo de dato a pesar de ser una clase
    public void ver(Pelicula pelicula) {
        //Manda a traer el titulo de la pelicula con la importacion de pelicula y usando pelicula.titulo

        //System.out.println(nombre + " está viendo: " + pelicula.titulo);
        System.out.println(nombre + " está viendo: ");
        pelicula.reproducir();
    }
}
