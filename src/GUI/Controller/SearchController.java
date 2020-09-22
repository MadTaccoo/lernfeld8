package GUI.Controller;

import GUI.MainGUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class SearchController {
    @FXML
    public void handleButtons(ActionEvent e) throws IOException {
        Button b = null;
        if(e.getSource() instanceof Button) {
            b = (Button)e.getSource();
        }
        assert b != null;

        String ident = b.getId();
        switch (ident){

        }
    }
}
