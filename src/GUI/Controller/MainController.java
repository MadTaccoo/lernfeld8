package GUI.Controller;

import GUI.MainGUI;
import Search.LinearSearch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class MainController {
    /**
     *
     * @param e ActionEvent which is used to identify the button
     * @throws IOException
     */
    @FXML
    public void handleButtons(ActionEvent e) throws IOException {
        Button b = null;
        //in case the event source is a Button
        if(e.getSource() instanceof Button) {
            b = (Button)e.getSource();
        }
        assert b != null;
        //where we use the FXML id of the buttons to identify them and give them a function
        String ident = b.getId();
        switch (ident){
            //Opens window for given Sorting algorithm,Numerical Algorithm or Search Algorithm
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
                MainGUI.setWindow("FXML/SortWindow.fxml","Selection Sort",6);
                break;
            case "linearSearchB":
                MainGUI.setWindow("FXML/LinearSearchWindow.fxml","Linear Search");
                LinearSearchController lsc = (LinearSearchController)MainGUI.f.getController();
                lsc.load();
                break;
            case "binarySearchB":
                MainGUI.setWindow("FXML/BinarySearchWindow.fxml","Binary Search");
                BinarySearchController bsc = (BinarySearchController)MainGUI.f.getController();
                bsc.load();
                break;
            case "newtonverfahrenB":
                MainGUI.setWindow("FXML/NewtonGraph.fxml","NewtonGraph");
                break;
            case "GaussSeidelB":
                //TODO Daniel
                break;
        }
    }
}
