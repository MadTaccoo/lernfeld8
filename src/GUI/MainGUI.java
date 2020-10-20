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

    /**
     * @param primaryStage is used to display the given FXML file
     * Is needed to initialize the first window shown to the user
     */

    @Override
    public void start(Stage primaryStage) throws Exception{
        f = new FXMLLoader(MainGUI.class.getResource(("FXML/MainMenuWindow.fxml")));
        Parent root = f.load();
        primaryStage.setTitle("Menu");
        windowTitle = primaryStage.getTitle();
        primaryStage.sizeToScene();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.setResizable(false);
        stage = primaryStage;
    }

    /**
     *
     * @param path to given FXML file
     * @param title window title
     * @param augment in this case the augment is used to identify the wanted sorting algorithm
     * @throws IOException in case the given path does not exist
     */
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
        stage.setResizable(false);
        if(path.equals("FXML/SortWindow.fxml"))
            SortController.whichSort = augment;
        stage.show();
    }

    /**
     *
     * @param path to given FXML file
     * @param title window title
     * @throws IOException in case the given path does not exist
     */
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
        stage.setResizable(path.contains("Graph"));

        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * allows the user to go back to the main menu
     * @throws IOException in case the given path does not exist
     */
    public static void goToMenu() throws IOException {
        double width = stage.getWidth();
        double height = stage.getHeight();
        f = new FXMLLoader(MainGUI.class.getResource(("FXML/MainMenuWindow.fxml")));
        Parent root = f.load();
        stage.setTitle("Menu");
        windowTitle = stage.getTitle();
        stage.setWidth(width);
        stage.setHeight(height);
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
