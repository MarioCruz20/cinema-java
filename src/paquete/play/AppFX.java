package paquete.play;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import paquete.play.dao.ContenidoDAO;

public class AppFX extends Application {

    @Override
    public void start(Stage stage) {

        Button btnMostrar = new Button("Mostrar contenido");
        TextArea area = new TextArea();

        btnMostrar.setOnAction(e -> {
            ContenidoDAO dao = new ContenidoDAO();
            area.setText(dao.listar());
        });

        VBox root = new VBox(10, btnMostrar, area);

        Scene scene = new Scene(root, 500, 400);

        stage.setTitle("Cinema Play");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
