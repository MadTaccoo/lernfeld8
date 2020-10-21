package Interfaces;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public interface Controller {
    void load();
    @FXML
    void handleButtons(ActionEvent e) throws Exception;
}
