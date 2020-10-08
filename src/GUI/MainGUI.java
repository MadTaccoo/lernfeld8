package GUI;

import GUI.Controller.SortController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainGUI extends Application {
    static Stage stage;
    static Stage lastStage;
    public static String windowTitle;
    public static FXMLLoader f;
    @Override
    public void start(Stage primaryStage) throws Exception{
        f = new FXMLLoader(MainGUI.class.getResource(("FXML/mainMenu.fxml")));
        Parent root = f.load();
        primaryStage.setTitle("Menu");
        windowTitle = primaryStage.getTitle();
        primaryStage.sizeToScene();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        stage = primaryStage;
    }

    public static void setWindow(String path,String title,int augment) throws IOException {
        lastStage = stage;
        double width = stage.getWidth();
        double height = stage.getHeight();
        f = new FXMLLoader(MainGUI.class.getResource((path)));
        Parent root = f.load();
        stage.setTitle(title.equals("") ? stage.getTitle() : title);
        windowTitle = stage.getTitle();
        stage.setWidth(width);
        stage.setHeight(height);
        stage.setScene(new Scene(root));
        if(path.equals("FXML/SortWindow.fxml"))
            SortController.whichSort = augment;
        stage.show();
    }
    public static void setWindow(String path,String title) throws IOException {
        lastStage = stage;
        double width = stage.getWidth();
        double height = stage.getHeight();
        f = new FXMLLoader(MainGUI.class.getResource((path)));
        Parent root = f.load();
        stage.setTitle(title.equals("") ? stage.getTitle() : title);
        windowTitle = stage.getTitle();

        stage.setWidth(width);
        stage.setHeight(height);
        stage.setScene(new Scene(root));
        stage.show();
    }
    public static void goToMenu() throws IOException {
        double width = stage.getWidth();
        double height = stage.getHeight();
        f = new FXMLLoader(MainGUI.class.getResource(("FXML/mainMenu.fxml")));
        Parent root = f.load();
        stage.setTitle("Menu");
        windowTitle = stage.getTitle();
        stage.setWidth(width);
        stage.setHeight(height);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
