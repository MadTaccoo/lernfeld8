package GUI.Controller;

import GUI.MainGUI;
import Search.BinarySearch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class BinarySearchController {
    double[] arr = new double[11];
    Label[] lArr = new Label[11];
    int currIndex = 0,targetIndex = 0;
    ArrayList<Integer> listOfSteps = new ArrayList<>();

    @FXML
    Label l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11;
    @FXML
    Button start,next;
    @FXML
    TextField nSelection;
    @FXML
    public void handleButtons(ActionEvent e) throws IOException {
        Button b = null;
        if(e.getSource() instanceof Button) {
            b = (Button)e.getSource();
        }
        assert b != null;

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

    public void resetAll(){
        for (Label label : lArr) {
            label.setStyle("-fx-background-color: #374152");
        }
    }

    public void load(){
        Random r = new Random();
        arr = new double[] {r.nextInt(1000),r.nextInt(1000),r.nextInt(1000),r.nextInt(1000),r.nextInt(1000),r.nextInt(1000),r.nextInt(1000),r.nextInt(1000),r.nextInt(1000),r.nextInt(1000),r.nextInt(1000)};
        Arrays.sort(arr);
        lArr = new Label[] {l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11};
        for (int i = 0; i < arr.length; i++) {
            lArr[i].setText(arr[i]+"");
        }
    }
}
