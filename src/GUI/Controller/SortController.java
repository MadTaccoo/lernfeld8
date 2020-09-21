package GUI.Controller;

import GUI.MainGUI;
import Sorting_Algorithms.BubbleSort;
import Sorting_Algorithms.InsertionSort;
import Sorting_Algorithms.QuickSort;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class SortController {
    @FXML
    TextField sorted,unsorted;
    public static int whichSort = 0;

    public void handleButtons(ActionEvent e) throws IOException {
        Button b = null;
        if(e.getSource() instanceof Button) {
            b = (Button)e.getSource();
        }
        assert b != null;

        String ident = b.getId();
        switch (ident){
            case "sortB":
                sortList();
                break;
            case "scrambleB":

                break;
        }
    }

    public void sortList(){
        String[] ls = unsorted.getText().split(",");
        if(ls.length < 2){
            sorted.setText("Error!");
            return;
        }
        double[] lsdouble = new double[ls.length];
        for (int i = 0; i < ls.length; i++) {
            lsdouble[i] = Double.parseDouble(ls[i]);
        }
        switch (whichSort){
            case 1:
                InsertionSort.insertionSort(lsdouble);
                break;
            case 2:
                QuickSort.quickSort(lsdouble,0, lsdouble.length-1);
                break;
        }
        StringBuilder result = new StringBuilder();
        for (double d:lsdouble) {
            result.append(d).append(", ");
        }
        sorted.setText(result.substring(0,result.length()-2));
    }
}
