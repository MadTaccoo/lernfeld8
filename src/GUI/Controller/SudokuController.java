package GUI.Controller;

import Backtracking.Sudoku;
import GUI.MainGUI;
import Interfaces.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

public class SudokuController implements Controller {
    static int[][] arrayGameField;
    static TextField[][] arrayGameFieldLabel;
    @FXML
    AnchorPane sudokuPane;


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
            case "solveB":
                Sudoku.grid = arrayGameField;
                Sudoku.solve();
                arrayGameField = Sudoku.grid;
                for (int i = 0; i < arrayGameField.length; i++) {
                    for (int j = 0; j < arrayGameField.length ; j++) {
                        arrayGameFieldLabel[i][j].setText(""+arrayGameField[i][j]);
                    }
                }
                break;
            case "menuB":
                MainGUI.goToMenu();
                break;
        }
    }
    @Override
    public void load() {
        createField();
        fillStart();
    }

    public void createField(){
        arrayGameField = new int[9][9];
        arrayGameFieldLabel = new TextField[9][9];
        Label askHowManyCubes = new Label("");
        askHowManyCubes.setLayoutX(50);
        askHowManyCubes.setLayoutY(25);
        int labelX = 0;
        int labelY = 0;
        int width = 50;
        int height = 50;
        for (int i = 0; i < arrayGameField.length; i++) {
            if (i % 3 == 0 && i !=0){
                labelY += 10;
            }
            for (int m = 0; m < arrayGameField.length; m++) {
                if (m % 3 == 0 && m != 0){
                    labelX += 10;
                }
                arrayGameFieldLabel[m][i] = new TextField();
                arrayGameFieldLabel[m][i].setLayoutX(labelX);
                arrayGameFieldLabel[m][i].setLayoutY(labelY);
                arrayGameFieldLabel[m][i].setPrefWidth(width);
                arrayGameFieldLabel[m][i].setPrefHeight(height);
                arrayGameFieldLabel[m][i].setStyle("-fx-background-color: #ae705b; -fx-text-fill: #ffffff;-fx-text-alignment: Center;-fx-font-size: 15");
                arrayGameFieldLabel[m][i].setText("0");
                arrayGameFieldLabel[m][i].setAlignment(Pos.CENTER);
                sudokuPane.getChildren().add(arrayGameFieldLabel[m][i]);

                labelX += 60;
            }
            labelX = 0;
            labelY += 60;
        }
    }

    public void fillStart(){
        setVal(0,3,3);
        setVal(1,3,8);
        setVal(2,3,4);
        setVal(8,8,2);
    }

    public void setVal(int x, int y, int n){
        if (possible(x,y,n)){
            arrayGameFieldLabel[x][y].setText(String.valueOf(n));
            arrayGameField[x][y] = n;
        }else if (n == 0){
            arrayGameFieldLabel[x][y].setText(String.valueOf(n));
            arrayGameField[x][y] = n;
        }
    }

    public boolean possible(int x,int y, int n){
        if (arrayGameField[x][y] == n){
            return false;
        }
        for (int i = 0; i < 9; i++) {
            if (arrayGameField[x][i] == n || arrayGameField[i][y] == n){
                return false;
            }
        }
        int x1 = (x/3)*3;
        int y1 = (y/3)*3;
        for (int i = 0; i < 3; i++) {
            for (int b = 0; b < 3; b++) {
                if (arrayGameField[x1+i][y1+b] == n){
                    return false;
                }
            }
        }
        return true;
    }
}
