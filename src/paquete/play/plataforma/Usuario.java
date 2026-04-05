package paquete.play.plataforma;

//Importar paquete para usar atributos y metodos de Contenido.java
import paquete.play.contenido.Contenido;

import java.time.LocalDateTime;

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
    //La clase Contenido puede ser usada como un tipo de dato a pesar de ser una clase
    public void ver(Contenido contenido) {
        //Manda a traer el titulo de la contenido con la importacion de contenido y usando contenido.titulo

        //System.out.println(nombre + " está viendo: " + contenido.titulo);
        System.out.println(nombre + " está viendo: ");
        contenido.reproducir();
    }
}
