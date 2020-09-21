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
                MainGUI.setWindow("FXML/SortWindow.fxml","",1);
                break;
            case "selectionSortB":
                //TODO Wutthichai
                break;
            case "quickSortB":
                //TODO Jan
                break;
            case "mergeSortB":
                //TODO Wutthichai
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
        }
    }
}
