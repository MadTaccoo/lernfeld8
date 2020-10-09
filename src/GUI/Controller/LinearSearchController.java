package GUI.Controller;

import GUI.MainGUI;
import Search.LinearSearch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.util.Random;

public class LinearSearchController {
    public static int[] ls;
    private static int showIndex = 0;
    private static Label[] lArr;
    private int targetIndex = 0;

    @FXML
    Button prevB,nextB;
    @FXML
    Label txt_n_m1,txt_n,txt_n_p1;
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

    public void load(){
        Random r = new Random();
        ls = new int[]{r.nextInt(100),r.nextInt(100),r.nextInt(100),r.nextInt(100),r.nextInt(100),r.nextInt(100),r.nextInt(100),r.nextInt(100),r.nextInt(100),r.nextInt(100)};
        lArr = new Label[]{txt_n_m1,txt_n,txt_n_p1};
        printData();
    }

    public void resetAll(){
        for (Label txtF : lArr) {
            txtF.setStyle("-fx-background-color: #374152");
        }
    }

}