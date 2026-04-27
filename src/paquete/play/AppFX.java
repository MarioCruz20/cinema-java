package paquete.play;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import paquete.play.contenido.Documental;
import paquete.play.contenido.Pelicula;
import paquete.play.dao.ContenidoDAO;
import paquete.play.dao.DocumentalDAO;
import paquete.play.dao.PeliculaDAO;
import paquete.play.plataforma.Genero;

public class AppFX extends Application {

    @Override
    public void start(Stage stage) {
        //1. UI (Labels, botones)
        //Encabezado con título
        Label tituloCinema = new Label("Cinema Play");
        tituloCinema.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        //Botones
        Button btnMostrar = new Button("Mostrar contenido");
        Button btnAgregar = new Button("Agregar contenido");
        Button btnBuscar = new Button("Buscar contenido");
        Button btnEliminar = new Button("Eliminar contenido");
        Button btnEjemplo = new Button("Mostrar Input");

        Button btnGuardar = new Button("Guardar");
        Button btnInput = new Button("Guardar Input");

        //2. Inputs
        //Inputs para ingreso de datos
        TextField txtTitulo = new TextField();
        txtTitulo.setPromptText("Titulo");

        TextField txtDuracion = new TextField();
        txtDuracion.setPromptText("Duracion");

        TextField txtGenero = new TextField();
        txtGenero.setPromptText("Genero");

        TextField txtCalificacion = new TextField();
        txtCalificacion.setPromptText("Calificacion");

        TextField txtDirector = new TextField();
        txtDirector.setPromptText("Director");

        TextField txtNarrador = new TextField();
        txtNarrador.setPromptText("Narrador");

        TextField txtInput = new TextField();
        txtInput.setPromptText("Input");

        //Combobox
        ComboBox<String> comboTipo = new ComboBox<>();
        comboTipo.getItems().addAll("Pelicula", "Documental");
        comboTipo.setValue("Pelicula");


        //3. Layouts (VBox, HBox)
        //Área donde se muestran los resultados
        TextArea area = new TextArea();
        area.setPrefHeight(400);

        HBox botones = new HBox(10, btnMostrar, btnAgregar, btnBuscar, btnEliminar, btnEjemplo);
        botones.setAlignment(Pos.CENTER);

        VBox formularioInput = new VBox(10,
                txtInput,
                btnInput
        );

        //Muestra los elementos al usar el boton "Agregar contenido"
        VBox formularioAgregar = new VBox(10,
                comboTipo,
                txtTitulo,
                txtDuracion,
                txtGenero,
                txtCalificacion,
                txtDirector,
                txtNarrador,
                btnGuardar
        );

        //4. Eventos (setOnAction)
        //Acción del botón mostrar contenido
        btnMostrar.setOnAction(e -> {
            ContenidoDAO dao = new ContenidoDAO();
            area.setText(dao.listar());
        });

        //Acción del btn guardar
        btnGuardar.setOnAction(e -> {
            try {
                String titulo = txtTitulo.getText();
                int duracion = Integer.parseInt(txtDuracion.getText());
                String genero = txtGenero.getText().toUpperCase();
                double calificacion = Double.parseDouble(txtCalificacion.getText());
                String director = txtDirector.getText();
                String tipo = comboTipo.getValue();

                if (tipo.equals("Pelicula")) {
                    Pelicula pelicula = new Pelicula(
                            titulo,
                            duracion,
                            Genero.valueOf(genero),
                            calificacion,
                            director
                    );

                    new PeliculaDAO().guardar(pelicula);

                } else {
                    String narrador = txtNarrador.getText();

                    Documental doc = new Documental(
                            titulo,
                            duracion,
                            Genero.valueOf(genero),
                            calificacion,
                            director,
                            narrador
                    );

                    new DocumentalDAO().guardar(doc);
                }

                //Area donde mustra contenido ingresado
                //PS: Cambiar luego para mostrarlo en una tabla
                area.setText(titulo + " | " + duracion + " | " + genero  + " | " +  calificacion  + " | " +  director  + " | " +  tipo);

            } catch (Exception ex) {
                area.setText("Error: " + ex.getMessage());
            }
        });

        //btn input ---------------
        btnInput.setOnAction(e -> {
            String texto = txtInput.getText();
            area.setText(texto);
        });

        //5. Root y botones que dependen de root
        //Mostrar elementos en ventana principal
        VBox root = new VBox(15, tituloCinema, botones, area);
        root.setAlignment(Pos.TOP_CENTER);
        root.setStyle("-fx-padding: 20;-fx-background-color: white;");

        //Acción del btn agregar contenido
        btnAgregar.setOnAction(e -> {
            area.clear();
            root.getChildren().setAll(tituloCinema, botones, formularioAgregar, area);
        });

        btnEjemplo.setOnAction(e -> {
            area.clear();
            root.getChildren().setAll(tituloCinema, botones, formularioInput, area);
            txtInput.clear();
        });

        //6. Scene Ventana principal
        //                             v: anchura   v1: altura
        Scene scene = new Scene(root, 800, 400);

        stage.setTitle("Cinema Play");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}