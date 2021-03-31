package GUI.Controller;

import Euclidean_Algorithm.EuclideanAlgorithm;
import GUI.MainGUI;
import Interfaces.Controller;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class EuclideanController implements Controller {
    @FXML
    TextField nr1TF,nr2TF,resTF;

    @Override
    public void load() {
        TextField[] tfLs = {nr1TF,nr2TF,resTF};
        for (TextField tf:tfLs){
            tf.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (!newValue.matches("\\d*")) {
                        tf.setText(newValue.replaceAll("[^\\d]", ""));
                    }
                }
            });
        }
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
            case "solveB":
                int res = EuclideanAlgorithm.euclidRec(Integer.parseInt(nr1TF.getText()),Integer.parseInt(nr2TF.getText()));
                resTF.setText(""+res);
                break;
            case "menuB":
                MainGUI.goToMenu();
                break;
        }
    }
}
