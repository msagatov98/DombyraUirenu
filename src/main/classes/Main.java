package main.classes;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;


public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{

            File file = new File("src/main/resources/scenes/window.fxml");
            Parent root = FXMLLoader.load(file.toURI().toURL());
            stage.setScene(new Scene(root, 890, 590));
            stage.getIcons().add(new Image(new File("src\\main\\resources\\res\\images\\icons\\mainLogo.png").toURI().toString()));
            stage.setTitle("Dombyra Tuner");
            stage.setResizable(false);
            stage.centerOnScreen();
            stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}