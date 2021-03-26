package GUI.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.StringWriter;

public class ErrorController {
    @FXML
    private Label errorMessage ;

    public void setErrorText(String text) {
        errorMessage.setText(text);
    }
    @FXML
    private void close() {
        errorMessage.getScene().getWindow().hide();
    }

    public String getErrorMsg() {
        return errorMessage.getText();
    }
}