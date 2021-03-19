package GUI.Controller;

import GUI.MainGUI;
import Interfaces.Controller;
import Search.BinarySearch;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class BinarySearchController implements Controller {
    //list in which the random generated numbers are stored
    double[] arr = new double[11];
    //list that contains the designated labels to display numbers stored in arr
    Label[] lArr = new Label[11];
    //currIndex is used to count the currently highlighted value and the targetIndex is the calculated index of n
    int currIndex = 0,targetIndex = 0;
    //stores the steps returned from the algorithm
    ArrayList<Integer> listOfSteps = new ArrayList<>();
    //FXML imports for created Labels,Buttons,TextField
    @FXML
    Label l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11;
    @FXML
    Button start,next;
    @FXML
    TextField nSelection;

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
        }
        assert b != null;
        //where we use the FXML id of the buttons to identify them and give them a function
        String ident = b.getId();
        switch (ident){
            case "menu":
                MainGUI.goToMenu();
                break;
            case "next":
                resetAll();
                lArr[listOfSteps.get(currIndex)].setStyle("-fx-background-color: yellow");
                if(targetIndex == listOfSteps.get(currIndex)){
                    lArr[listOfSteps.get(currIndex)].setStyle("-fx-background-color: Green");
                    next.setDisable(true);
                }
                currIndex++;
                break;
            case "start":
                if(nSelection.getText().equals(""))
                    return;
                targetIndex = BinarySearch.binarySearchR(arr,Double.parseDouble(nSelection.getText()));
                listOfSteps = BinarySearch.ls;
                listOfSteps.add(targetIndex);
                next.setDisable(false);
                start.setDisable(true);
                break;

        }
    }

    /**
     * resets the background of all labels which are used to display the random values
     */
    public void resetAll(){
        for (Label label : lArr) {
            label.setStyle("-fx-background-color: #374152");
        }
    }

    /**
     * function which is used to initilize the random values and sort the array
     */
    public void load(){
        IconHandler.handleIcon("search");
        Random r = new Random();
        arr = new double[] {r.nextInt(1000),r.nextInt(1000),r.nextInt(1000),r.nextInt(1000),r.nextInt(1000),r.nextInt(1000),r.nextInt(1000),r.nextInt(1000),r.nextInt(1000),r.nextInt(1000),r.nextInt(1000)};
        Arrays.sort(arr);
        lArr = new Label[] {l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11};
        for (int i = 0; i < arr.length; i++) {
            lArr[i].setText(arr[i]+"");
        }

        //Disallows the user to enter any chars that are not numeric
        nSelection.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    nSelection.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }
}
