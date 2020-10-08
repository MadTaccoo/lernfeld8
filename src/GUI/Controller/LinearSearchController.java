package GUI.Controller;

import GUI.MainGUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LinearSearchController {
    @FXML
    Button prevB,nextB;
    @FXML
    TextField txt_n_m1,txt_n,txt_n_p1;

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
                break;
            case "nextB":
                break;
        }
    }

    public void load(){

    }
}
