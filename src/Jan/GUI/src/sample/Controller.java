package Jan.GUI.src.sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.QuadCurve;

import static sample.Main.main;

public class Controller {
    public Button rot;
    @FXML
    Pane back;
    @FXML
    QuadCurve curve;
    @FXML
    Line stick;
    @FXML
    Button gelb,gruen,blau,links,rechts,up,down;

    @FXML
    public void buttons(MouseEvent e){
        Button b = null;
        if(e.getSource() instanceof Button){
            b = (Button)e.getSource();
        }
        assert b != null;
        String buttonContent = b.getText().toLowerCase();
        switch (buttonContent){
            case "rot":
                back.setStyle("-fx-background-color: red");
                break;
            case "gelb":
                back.setStyle("-fx-background-color: yellow");
                break;
            case "blau":
                back.setStyle("-fx-background-color: blue");
                break;
            case "gruen":
                back.setStyle("-fx-background-color: green");
                break;
            case "links":
                stick.setLayoutX(stick.getLayoutX()-2);
                curve.setRotate(curve.getRotate()-2);
                break;
            case "rechts":
                stick.setLayoutX(stick.getLayoutX()+2);
                curve.setRotate(curve.getRotate()+2);
                break;
            case "up":
                stick.setLayoutY(stick.getLayoutY()-2);
                break;
            case "down":
                stick.setLayoutY(stick.getLayoutY()+2);
                break;
        }

    }
}
