package GUI.Controller;

import GUI.MainGUI;
import Interfaces.Controller;
import Sorting_Algorithms.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//WUTTI
public class SortController implements Controller {
    @FXML
    ListView
            sorted,
            unsorted;
    @FXML
    TextField
            howManyRandom;
    @FXML
    Label
            statsL;
    public static int whichSort = 0;

    @Override
    public void load() {
        IconHandler.handleIcon("sort");
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
        if (e.getSource() instanceof Button) {
            b = (Button) e.getSource();
        }
        assert b != null;

        String ident = b.getId();
        switch (ident) {
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

    public void addRandomValues() {
        String strhowMany = howManyRandom.getText();
        String regex = "[0-9]+";
        if (!strhowMany.matches(regex))
            System.out.println("Error");
        int howMany = Integer.parseInt(strhowMany);
        Random r = new Random();
        List<String> ls = new ArrayList<>();
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < howMany - 1; i++) {
            ls.add(r.nextFloat()+"");
        }
        ls.add(""+r.nextFloat());
        unsorted.setItems(FXCollections.observableArrayList(ls));
    }

    public void sortList() {
        Platform.runLater(() -> {
            statsL.setText("Stats: " + MainGUI.windowTitle +
                    "Working!");
        });
        long from = 0;
        long to = 0;
        ArrayList<String> lst = new ArrayList<>();
        for (Object s:
             unsorted.getItems()) {
             lst.add(String.valueOf(s));
        }
        String[] ls  = new String[lst.size()];
        ls = lst.toArray(ls);
        if (ls.length < 2) {
            return;
        }
        double[] lsdouble = new double[ls.length];
        for (int i = 0; i < ls.length; i++) {
            lsdouble[i] = Double.parseDouble(ls[i]);
        }
        switch (whichSort) {
            case 1:
                from = System.nanoTime();
                InsertionSort.insertionSort(lsdouble);
                to = System.nanoTime();
                break;
            case 2:
                from = System.nanoTime();
                QuickSort.quickSort(lsdouble);
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
                SelectionSort.SelectionSort(lsdouble);
                to = System.nanoTime();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + whichSort);
        }

        ArrayList<String> result = new ArrayList<>();
        for (double d : lsdouble)
            result.add(d+"\n");

        long nanoToComp = (to - from);
        long miliToComp = nanoToComp / 1000000; //TODO check conversion
        Platform.runLater(() -> {
            sorted.setItems(FXCollections.observableArrayList(result));
            statsL.setText("Stats: " +
                    "\nSorting Algorithm: " + MainGUI.windowTitle + "\n" +
                    "Nano seconds: " + nanoToComp + "\n" +
                    "Milli seconds: " + miliToComp);
        });
    }
}
