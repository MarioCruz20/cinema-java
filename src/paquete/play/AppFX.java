package paquete.play;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import paquete.play.dao.ContenidoDAO;
public class AppFX extends Application {

    @Override
    public void start(Stage stage) {

        //Encabezado con título
        Label titulo = new Label("Cinema Play");
        titulo.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        //Botones
        Button btnMostrar = new Button("Mostrar contenido");
        Button btnAgregar = new Button("Agregar contenido");
        Button btnBuscar = new Button("Buscar contenido");
        Button btnEliminar = new Button("Eliminar contenido");

        HBox botones = new HBox(10, btnMostrar, btnAgregar, btnBuscar, btnEliminar);
        botones.setAlignment(Pos.CENTER);

        //Área donde se muestran los resultados
        TextArea area = new TextArea();
        area.setPrefHeight(400);

        //Acción del botón mostrar contenido
        btnMostrar.setOnAction(e -> {
            ContenidoDAO dao = new ContenidoDAO();
            area.setText(dao.listar());
        });

        //Acción del btn agregar contenido

        //Mostrar elementos en ventana principal
        VBox root = new VBox(15, titulo, botones, area);
        root.setAlignment(Pos.TOP_CENTER);
        root.setStyle("-fx-padding: 20;-fx-background-color: white;");

        //Ventana principal           v: anchura   v1: altura
        Scene scene = new Scene(root, 800, 400);

        stage.setTitle("Cinema Play");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
