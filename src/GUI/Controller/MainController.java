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
            case "selectionSortB":
                //TODO Wutthichai
                MainGUI.setWindow("FXML/SortWindow.fxml","Selection Sort",3);
                break;
            case "quickSortB":
                MainGUI.setWindow("FXML/SortWindow.fxml","Quicksort",2);
                break;
            case "mergeSortB":
                //TODO Wutthichai
                MainGUI.setWindow("FXML/SortWindow.fxml","Merge Sort",4);
                break;
            case "linearSearchB":
                //TODO
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
            case "bogoSort":
                //TODO Wutthichai
                break;
        }
    }
}
