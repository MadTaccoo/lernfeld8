package GUI;

import GUI.Controller.SortController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainGUI extends Application {
    static Stage main;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("FXML/mainMenu.fxml"));
        primaryStage.setTitle("");
        primaryStage.sizeToScene();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        main = primaryStage;
    }

    public static void setWindow(String path,String title,int augment) throws IOException {
        double width = main.getWidth();
        double heigth = main.getHeight();
        Parent root = FXMLLoader.load(MainGUI.class.getResource((path)));
        main.setTitle(title.equals("") ? main.getTitle() : title);
        main.setWidth(width);
        main.setHeight(heigth);
        main.setScene(new Scene(root));
        if(path.equals("FXML/SortWindow.fxml"))
            SortController.whichSort = augment;
        main.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
