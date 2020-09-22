package GUI.Controller;

import GUI.MainGUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class MainController {
    @FXML
    public void handleButtons(ActionEvent e) throws IOException {
        Button b = null;
        if(e.getSource() instanceof Button) {
            b = (Button)e.getSource();
        }
        assert b != null;

        String ident = b.getId();
        switch (ident){
            case "insertionSortB":
                MainGUI.setWindow("FXML/SortWindow.fxml","Insertion Sort",1);
                break;
            case "quickSortB":
                MainGUI.setWindow("FXML/SortWindow.fxml","Quicksort",2);
                break;
            case "bubbleSortB":
                MainGUI.setWindow("FXML/SortWindow.fxml","Bubble Sort",3);
                break;
            case "mergeSortB":
                MainGUI.setWindow("FXML/SortWindow.fxml","Merge Sort",4);
                break;
            case "bogoSortB":
                MainGUI.setWindow("FXML/SortWindow.fxml","Bogo Sort",5);
                break;
            case "selectionSortB": // augment 6
                MainGUI.setWindow("FXML/SortWindow.fxml","Merge Sort",6);
                break;
            case "linearSearchB":
                MainGUI.setWindow("FXML/LinearSearchWindow.fxml","Linear Search");
                break;
            case "binarySearchB":
                //TODO
                break;
            case "newtonverfahrenB":
                //TODO Daniel
                break;
            case "GaussSeidelB":
                //TODO Daniel
                break;

        }
    }
}
