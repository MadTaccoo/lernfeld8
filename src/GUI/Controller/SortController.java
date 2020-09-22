package GUI.Controller;

import GUI.MainGUI;
import Sorting_Algorithms.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Random;

public class SortController {
    @FXML
    TextArea
            sorted,
            unsorted;
    @FXML
    TextField
            howManyRandom;
    @FXML
    Label
            statsL;
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
                Thread t1 = new Thread(this::sortList);
                t1.start();
                break;
            case "scrambleB":
                addRandomValues();
                break;
            case "fkGoBack":
                MainGUI.goToMenu();
                break;
        }
    }

    public void addRandomValues(){
        String strhowMany = howManyRandom.getText();
        String regex = "[0-9]+";
        if(!strhowMany.matches(regex)){
            unsorted.setText("Error!");
        }
        int howMany = Integer.parseInt(strhowMany);
        Random r = new Random();
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < howMany; i++) {
            res.append(r.nextFloat()).append("\n");
        }
        unsorted.setText(res.substring(0,res.length()-1));
    }

    public void sortList(){
        long from = 0;
        long to = 0;
        String[] ls = unsorted.getText().split("\n");
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
                from = System.nanoTime();
                InsertionSort.insertionSort(lsdouble);
                to = System.nanoTime();
                break;
            case 2:
                from = System.nanoTime();
                QuickSort.quickSort(lsdouble,0, lsdouble.length-1);
                to = System.nanoTime();
                break;
            case 3:
                from = System.nanoTime();
                BubbleSort.bubbleSortOptimized(lsdouble);
                to = System.nanoTime();
                break;
            case 4:
                from = System.nanoTime();
                MergeSort.mergeSort(lsdouble);
                to = System.nanoTime();
                break;
            case 5:
                from = System.nanoTime();
                BogoSort.bogoSort(lsdouble);
                to = System.nanoTime();
                break;
            case 6:
                from = System.nanoTime();
                //TODO SELECTION sort
                to = System.nanoTime();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + whichSort);
        }

        StringBuilder result = new StringBuilder();
        for (double d:lsdouble) {
            result.append(d).append("\n");
        }

        sorted.setText(result.substring(0,result.length()-2));
        long nanoToComp = (to-from);
        long miliToComp = nanoToComp/1000000; //TODO check conversion
        Platform.runLater(()->{
            statsL.setText("Stats:\n" +
                    "Nano seconds  " + nanoToComp + "\n" +
                    "Milli seconds " + miliToComp);
        });
    }
}
