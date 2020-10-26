package GUI.Controller;

import GUI.MainGUI;
import javafx.scene.image.Image;

public class IconHandler {
    public static void handleIcon(String nameOfPage) {
        switch (nameOfPage) {
            case "menu":
                MainGUI.setIcon("/menu.png");
                break;
            case "dijkstra":
                MainGUI.setIcon("/dijkstra.png");
                break;
            case "sort":
                MainGUI.setIcon("/sort.png");
                break;
            case "search":
                MainGUI.setIcon("/search.png");
                break;
            case "algorithm":
                MainGUI.setIcon("/algorithm.png");
                break;
            default:
                System.out.println("Fk");
        }
    }
}
