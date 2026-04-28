package paquete.play;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import paquete.play.contenido.Contenido;
import paquete.play.contenido.Documental;
import paquete.play.contenido.Pelicula;
import paquete.play.dao.ContenidoDAO;
import paquete.play.dao.DocumentalDAO;
import paquete.play.dao.PeliculaDAO;
import paquete.play.plataforma.Genero;
import java.util.List;

public class AppFX extends Application {

    //Método para cambiar vista de forma dinámica al usar botones
    private void cambiarVista(VBox contenedor, Node... nodos) {
        contenedor.getChildren().setAll(nodos);
    }

    @Override
    public void start(Stage stage) {

        //1. UI (Labels, botones)
        //Encabezado con título
        Label tituloCinema = new Label("Cinema Play");
        tituloCinema.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        //Botones
        Button btnMostrar = new Button("Mostrar contenido");
        Button btnAgregar = new Button("Agregar contenido");
        Button btnEditar = new Button("Editar contenido");
        Button btnBuscar = new Button("Buscar Contenido");
        Button btnEliminar = new Button("Eliminar contenido");

        Button btnGuardar = new Button("Guardar");
        Button btnActualizar = new Button("Actualizar");

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

        //Combobox
        ComboBox<String> comboTipo = new ComboBox<>();
        comboTipo.getItems().addAll("Pelicula", "Documental");
        comboTipo.setValue("Pelicula");

        //3. Layouts (VBox, HBox)
        //Área donde se muestran los resultados
        TextArea area = new TextArea();
        area.setPrefHeight(200);

        //refactor: cambio para mostrar contenido con una tabla
        //Para mostrar tabla
        TableView<Contenido> tabla = new TableView<>();

        //Creación de columnas
        TableColumn<Contenido, String> colTitulo = new TableColumn<>("Titulo");
        TableColumn<Contenido, String> colGenero = new TableColumn<>("Genero");
        TableColumn<Contenido, Integer> colDuracion = new TableColumn<>("Duracion");
        TableColumn<Contenido, Double> colCalificacion = new TableColumn<>("Calificacion");

        //Contectar columnas con los datos
        colTitulo.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getTitulo())
        );

        colGenero.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getGenero().toString())
        );

        colDuracion.setCellValueFactory(data ->
                new javafx.beans.property.SimpleIntegerProperty(data.getValue().getDuracion()).asObject()
        );

        colCalificacion.setCellValueFactory(data ->
                new javafx.beans.property.SimpleDoubleProperty(data.getValue().getCalificacion()).asObject()
        );

        //Para agregar columnas a la tabla
        tabla.getColumns().addAll(colTitulo, colGenero, colDuracion, colCalificacion);

        HBox botones = new HBox(10, btnMostrar, btnAgregar, btnEditar, btnBuscar, btnEliminar);
        botones.setAlignment(Pos.CENTER);

        //Formulario para agregar contenido
        //Muestra los elementos al usar el boton "Agregar contenido"
        VBox formularioAgregar = new VBox(10,
                comboTipo,
                txtTitulo,
                txtDuracion,
                txtGenero,
                txtCalificacion,
                txtDirector,
                txtNarrador,
                btnGuardar,
                btnActualizar
        );

        //4. Root y Eventos (setOnAction) en Botones
        VBox contenidoDinamico = new VBox();
        contenidoDinamico.setAlignment(Pos.CENTER);

        //Mostrar elementos en ventana principal
        VBox root = new VBox(30, tituloCinema, botones, contenidoDinamico);
        root.setAlignment(Pos.TOP_CENTER);
        root.setStyle("-fx-padding: 20;-fx-background-color: white;");

        //MOSTRAR CONTENIDO: Acción del botón mostrar contenido
        btnMostrar.setOnAction(e -> {
            List<Contenido> lista = new ContenidoDAO().listar();
            tabla.getItems().setAll(lista);
            cambiarVista(contenidoDinamico, tabla);
        });

        //AGREGAR CONTENIDO: Acción del btn agregar contenido
        btnAgregar.setOnAction(e -> {
            cambiarVista(contenidoDinamico, formularioAgregar);
        });

        //AGREGAR CONTENIDO: Acción del btn guardar
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

                area.setText("Contenido guardado correctamente");

            } catch (Exception ex) {
                area.setText("Error: " + ex.getMessage());
            }
        });

        //EDITAR CONTENIDO:
        btnEditar.setOnAction(e -> {

            Contenido c = tabla.getSelectionModel().getSelectedItem();

            if (c != null) {
                txtTitulo.setText(c.getTitulo());
                txtDuracion.setText(String.valueOf(c.getDuracion()));
                txtGenero.setText(c.getGenero().toString());
                txtCalificacion.setText(String.valueOf(c.getCalificacion()));

                cambiarVista(contenidoDinamico, formularioAgregar, area);
            } else {
                area.setText("Selecciona un contenido primero");
                cambiarVista(contenidoDinamico, tabla, area);
            }
        });

        //EDITAR CONTENIDO:
        btnActualizar.setOnAction(e -> {

            Contenido seleccionado = tabla.getSelectionModel().getSelectedItem();

            if (seleccionado != null) {
                try {
                    seleccionado.setTitulo(txtTitulo.getText());
                    seleccionado.setDuracion(Integer.parseInt(txtDuracion.getText()));
                    seleccionado.setGenero(Genero.valueOf(txtGenero.getText().toUpperCase()));
                    seleccionado.calificar(Double.parseDouble(txtCalificacion.getText()));

                    new ContenidoDAO().actualizar(seleccionado);

                    area.setText("Contenido actualizado");
                } catch (Exception ex) {
                    area.setText("Error: " + ex.getMessage());
                }
            } else {
                area.setText("No hay contenido seleccionado");
            }
        });

        //BUSCAR CONTENIDO:
        btnBuscar.setOnAction(e -> {
            area.clear();
            cambiarVista(contenidoDinamico, area);
        });

        //5. Scene Ventana principal
        //                             v: anchura   v1: altura
        Scene scene = new Scene(root, 800, 500);

        stage.setTitle("Cinema Play");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}