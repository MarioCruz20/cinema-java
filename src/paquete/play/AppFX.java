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
        Button btnBuscarID = new Button("Buscar Contenido");
        Button btnEliminar = new Button("Eliminar contenido");

        //Botones secundarios
        Button btnGuardar = new Button("Guardar"); //de Agregar contenido
        Button btnActualizar = new Button("Actualizar"); //de Actualizar contenido
        Button btnd = new Button("Buscar contenidoID"); //de actualizar contenido V2

        //2. Inputs
        //TextFields para ingreso de datos

        //AGREGAR CONTENIDO:
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

        //ACTUALIZAR CONTENIDO:
        TextField txtID = new TextField();
        txtID.setPromptText("ID del contenido");

        //3. Layouts (VBox, HBox)
        //Área donde se muestran los resultados
        TextArea area = new TextArea();
        area.setPrefHeight(200);

        //##: cambio para mostrar contenido con una tabla
        TableView<Contenido> tabla = new TableView<>();

        //Creación de columnas
        TableColumn<Contenido, String> colTitulo = new TableColumn<>("Titulo");
        TableColumn<Contenido, String> colGenero = new TableColumn<>("Genero");
        TableColumn<Contenido, Integer> colDuracion = new TableColumn<>("Duracion");
        TableColumn<Contenido, Double> colCalificacion = new TableColumn<>("Calificacion");
        //TableColumn<Contenido, String> colTipo = new TableColumn<>("Tipo");
        TableColumn<Contenido, Void> colEditar = new TableColumn<>("Editar");
        TableColumn<Contenido, Void> colEliminar = new TableColumn<>("Eliminar");

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
        tabla.getColumns().addAll(colTitulo, colGenero, colDuracion, colCalificacion, colEditar, colEliminar);

        HBox botones = new HBox(10, btnMostrar, btnAgregar, btnBuscarID);


        //FORMULARIOS:

        //Formulario para agregar contenido
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

        //Formulario para buscar contenido por ID
        VBox formularioBuscar = new VBox(10,
                txtID,
                btnBuscarID,
                area
        );

        // CAMBIO: contenidoDinamico se declara ANTES de usarlo en eventos
        VBox contenidoDinamico = new VBox();
        contenidoDinamico.setAlignment(Pos.CENTER);

        //CAMBIO: root también antes de eventos
        VBox root = new VBox(30, tituloCinema, botones, contenidoDinamico);
        root.setAlignment(Pos.TOP_CENTER);
        root.setStyle("-fx-padding: 20;-fx-background-color: white;");

        // BOTÓN EDITAR EN CADA FILA
        colEditar.setCellFactory(col -> new TableCell<>() {
            private final Button btn = new Button("Editar");

            {
                btn.setOnAction(e -> {
                    Contenido c = getTableView().getItems().get(getIndex());

                    // Llenar formulario
                    txtTitulo.setText(c.getTitulo());
                    txtDuracion.setText(String.valueOf(c.getDuracion()));
                    txtGenero.setText(c.getGenero().toString());
                    txtCalificacion.setText(String.valueOf(c.getCalificacion()));

                    // Mostrar formulario
                    cambiarVista(contenidoDinamico, formularioAgregar, area);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : btn);
            }
        });

        //BOTÓN ELIMINAR EN CADA FILA
        colEliminar.setCellFactory(col -> new TableCell<>() {
            private final Button btn = new Button("Eliminar");

            {
                btn.setOnAction(e -> {
                    Contenido c = getTableView().getItems().get(getIndex());

                    new ContenidoDAO().eliminar(c.getContenidoID());

                    //refrescar tabla después de eliminar
                    tabla.getItems().setAll(new ContenidoDAO().listar());

                    area.setText("Contenido eliminado");
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : btn);
            }
        });

        //4. Eventos (setOnAction)

        //MOSTRAR CONTENIDO
        btnMostrar.setOnAction(e -> {
            List<Contenido> lista = new ContenidoDAO().listar();
            tabla.getItems().setAll(lista);
            cambiarVista(contenidoDinamico, tabla);
        });

        //AGREGAR CONTENIDO
        btnAgregar.setOnAction(e -> {
            cambiarVista(contenidoDinamico, formularioAgregar);
        });

        //GUARDAR
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

        //EDITAR CONTENIDO
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

        //ACTUALIZAR
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

        //BUSCAR POR ID
        btnBuscarID.setOnAction(e -> {
            int id = Integer.parseInt(txtID.getText());

            Contenido c = new ContenidoDAO().buscarPorID(id);

            if (c != null) {
                txtTitulo.setText(c.getTitulo());
                txtDuracion.setText(String.valueOf(c.getDuracion()));
                txtGenero.setText(c.getGenero().toString());
                txtCalificacion.setText(String.valueOf(c.getCalificacion()));

                cambiarVista(contenidoDinamico, formularioBuscar);
            } else {
                area.setText("No encontrado");
            }
        });

        //5. Scene Ventana principal
        Scene scene = new Scene(root, 800, 500);

        stage.setTitle("Cinema Play");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}