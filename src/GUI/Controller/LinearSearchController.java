package GUI.Controller;

import GUI.MainGUI;
import Interfaces.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.util.Random;

public class LinearSearchController implements Controller {
    //array with random integer values
    public static int[] ls;
    //index which displays the given value at this index
    private static int showIndex = 0;
    //list which contains all Labels
    private static Label[] lArr;
    //FXML imports Buttons,Labels,TextFields
    @FXML
    Button prevB,nextB;
    @FXML
    Label txt_n_m1,txt_n,txt_n_p1;
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
            case "prevB":
                showIndex = (showIndex>0)?showIndex-1:showIndex;
                printData();
                break;
            case "nextB":
                showIndex = (showIndex+1< ls.length)?showIndex+1:showIndex;
                printData();
                break;
            case "menu":
                MainGUI.goToMenu();
                break;
        }
    }

    /**
     * function to print the values around n (n-1) (n+1)
     */
    public void printData(){
        resetAll();
        String sm1 = (showIndex>=1) ? ls[showIndex-1] + "" : "";
        txt_n_m1.setText(sm1);
        txt_n.setText(""+ls[showIndex]);
        txt_n_p1.setText((showIndex+1<ls.length) ? ls[showIndex+1]+"":"");
        if(txt_n.getText().equals(nSelection.getText())){
            txt_n.setStyle("-fx-background-color: green");
        }else{
            txt_n.setStyle("-fx-background-color: yellow");
        }
    }

    /**
     * function which is used to initilize the random values and sort the array
     */
    public void load(){
        Random r = new Random();
        ls = new int[]{r.nextInt(100),r.nextInt(100),r.nextInt(100),r.nextInt(100),r.nextInt(100),r.nextInt(100),r.nextInt(100),r.nextInt(100),r.nextInt(100),r.nextInt(100)};
        lArr = new Label[]{txt_n_m1,txt_n,txt_n_p1};
        printData();
    }

    /**
     * resets all Label backgrounds
     */
    public void resetAll(){
        for (Label txtF : lArr) {
            txtF.setStyle("-fx-background-color: #374152");
        }
    }

}