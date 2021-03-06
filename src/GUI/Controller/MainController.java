package GUI.Controller;

import Euclidean_Algorithm.EuclideanAlgorithm;
import GUI.MainGUI;
import Interfaces.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

import java.io.IOException;

public class MainController implements Controller {
    @FXML
    CheckBox cBdebug;
    @Override
    public void load() {
        //in this case not needed
        cBdebug.setSelected(MainGUI.debug);
    }

    /**
     * this function handles all click events of Buttons
     * it uses the fxml id of the button to identify it and than execute the code for the button
     * @param e ActionEvent used to get the source of the event
     * @throws IOException
     */
    @FXML
    public void handleButtons(ActionEvent e) throws IOException {
        Button b = null;
        //in case the event source is a Button
        if(e.getSource() instanceof Button) {
            b = (Button)e.getSource();
        }else if (e.getSource() instanceof  CheckBox){
            CheckBox debugBox = (CheckBox) e.getSource();
            MainGUI.debug = debugBox.isSelected();
            return;
        }

        assert b != null;
        boolean isSort = false;
        //where we use the FXML id of the buttons to identify them and give them a function
        String ident = b.getId();
        switch (ident){
            //Opens window for given Sorting algorithm,Numerical Algorithm or Search Algorithm
            case "insertionSortB":
                MainGUI.setWindow("FXML/SortWindow.fxml","Insertion Sort",1);
                isSort = true;
                break;
            case "quickSortB":
                MainGUI.setWindow("FXML/SortWindow.fxml","Quicksort",2);
                isSort = true;
                break;
            case "bubbleSortB":
                MainGUI.setWindow("FXML/SortWindow.fxml","Bubble Sort",3);
                isSort = true;
                break;
            case "mergeSortB":
                MainGUI.setWindow("FXML/SortWindow.fxml","Merge Sort",4);
                isSort = true;
                break;
            case "bogoSortB":
                MainGUI.setWindow("FXML/SortWindow.fxml","Bogo Sort",5);
                isSort = true;
                break;
            case "selectionSortB": // augment 6
                MainGUI.setWindow("FXML/SortWindow.fxml","Selection Sort",6);
                isSort = true;
                break;
            case "heapSortB":
                MainGUI.setWindow("FXML/SortWindow.fxml","Heap Sort",7);
                isSort = true;
                break;
            case "linearSearchB":
                MainGUI.setWindow("FXML/LinearSearchWindow.fxml","Linear Search");
                LinearSearchController lsc = MainGUI.f.getController();
                lsc.load();
                break;
            case "binarySearchB":
                MainGUI.setWindow("FXML/BinarySearchWindow.fxml","Binary Search");
                BinarySearchController bsc = MainGUI.f.getController();
                bsc.load();
                break;
            case "newtonRaphsonB":
                MainGUI.setWindow("FXML/NewtonRaphsonWindow.fxml","Newton Raphson");
                NewtonRaphsonController ngc = MainGUI.f.getController();
                ngc.load();
                break;
            case "GaussSeidelB":
                MainGUI.setWindow("FXML/GaussSeidelWindow.fxml","Gauss Seidel");
                GaussSeidelController gsc = MainGUI.f.getController();
                gsc.load();
                break;
            case "dijkstraB":
                MainGUI.setWindow("FXML/DijkstraWindow.fxml","Dijkstra");
                DijkstraController djc = MainGUI.f.getController();
                djc.load();
                break;
            case "sudokuB":
                MainGUI.setWindow("FXML/SudokuWindow.fxml","Sudoku");
                SudokuController sc = MainGUI.f.getController();
                sc.load();
                break;
            case "euclideanB":
                MainGUI.setWindow("FXML/EuclideanWindow.fxml","Euclidean Algorithm",600,400);
                EuclideanController ea = MainGUI.f.getController();
                ea.load();
                break;
            case "hashB":
                MainGUI.setWindow("FXML/HashWindow.fxml","Simple Hash",400,400);
                break;
        }
        if(isSort){
            SortController sc = MainGUI.f.getController();
            sc.load();
        }
    }
}
