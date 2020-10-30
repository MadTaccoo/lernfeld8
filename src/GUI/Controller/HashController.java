package GUI.Controller;

import Backtracking.Sudoku;
import GUI.MainGUI;
import Hashing_algorithm.Hashing;
import Interfaces.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.io.IOException;

public class HashController implements Controller {
    @FXML
    TextField inputTF,hashTF;
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
        boolean isSort = false;
        //where we use the FXML id of the buttons to identify them and give them a function
        String ident = b.getId();
        switch (ident){
            case "hashB":
                if(inputTF.getText().length() > 16||inputTF.getText().length() < 8 ||!containsOneUpperOneLower(inputTF.getText())){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Wrong input");
                    alert.setHeaderText("Wrong input (╯°□°）╯︵ ┻━┻\nThe text hast to be longer than 7 and shorter than 17\nalso it must have one upper and one lower case char");
                    alert.showAndWait().ifPresent(rs -> { });
                    return;
                }
                hashTF.setText(Hashing.simpleHash(inputTF.getText()));
                break;
            case "menuB":
                MainGUI.goToMenu();
                break;
        }
    }
    public boolean containsOneUpperOneLower(String input){
        boolean upper = false , lower = false;
        for (char c:input.toCharArray()) {
            if(c>=97 && c<=123)
                lower = true;
            if(c>=66 && c<=91)
                upper = true;
        }
        return upper&&lower;
    }

    @Override
    public void load() {

    }


}
