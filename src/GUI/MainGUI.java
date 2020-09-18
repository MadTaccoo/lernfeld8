package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainGUI extends Application {
    static Stage main;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
        primaryStage.setTitle("");
        primaryStage.sizeToScene();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        main = primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
