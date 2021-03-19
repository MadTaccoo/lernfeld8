package GUI.Controller;

import GUI.MainGUI;
import Interfaces.Controller;
import NumericalMathematics.Gauss.Gauss_Jordan;
import Parser.SLEParser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

public class GaussSeidelController implements Controller {
    //FXML imports of needed controls
    @FXML
    public TextArea lgsInput,lgsRes,solutionTA,solutionGJTA;
    @FXML
    public TextField precisionTF;
    /**
     * this function handles all click events of Buttons
     * it uses the fxml id of the button to identify it and than execute the code for the button
     * @param e ActionEvent used to get the source of the event
     * @throws IOException needed in case MainGUI.goToMenu(); throes an error
     */
    @FXML
    public void handleButtons(ActionEvent e) throws IOException {
        Button b = null;
        //in case the event source is a Button
        if(e.getSource() instanceof Button)
            b = (Button)e.getSource();
        assert b != null;
        //where we use the FXML id of the buttons to identify them and give them a function
        String ident = b.getId();
        switch (ident){
            case "menuB":
                MainGUI.goToMenu();
                break;
            case "solveB":
                solve();
                break;
        }
    }

    /**
     * helper function which solves the array which is currently displayed in the GUI
     * Solves once with Gauss Seidel and with Gauss Jordan
     * than displays the results in the gui
     */
    public void solve(){
        //parse the System of linear equations
        double[][] lgs = SLEParser.parseLGS(lgsInput.getText());
        double[] res = SLEParser.parseRes(lgsRes.getText());
        //reads how many unknown variables
        int howManyVar = res.length;
        //creates the guess for gauss-seidel algorithm
        double[] guess = new double[howManyVar];
        for (int i = 0; i < howManyVar; i++)
            guess[i] = 0;
        //solve SLE with the GaussSeidel algorithm
        double[] sol = NumericalMathematics.Gauss.GaussSeidel.gaussSeidel(lgs,res,Integer.parseInt(precisionTF.getText()),guess);

        //builds string out of returns from the algorithm
        StringBuilder resStringGS = new StringBuilder();
        for (double v : sol)
            resStringGS.append(v).append("\n");
        solutionTA.setText(resStringGS.toString());

        //copy the array which is to solve
        double[][] lgsGJ = new double[lgs.length][lgs.length+1];
        for (int i = 0; i < lgs.length; i++) {
            for (int j = 0; j < lgs[i].length; j++) {
                lgsGJ[i][j] = lgs[i][j];
            }
        }
        //add the res Vector to the els
        for (int i = 0; i < lgs.length; i++) {
            lgsGJ[i][lgsGJ.length] = res[i];
        }
        //Solves ELS with Gauss Jordan Algorithm to back check the values from Gauss Seidel algorithm
        double[] solGJ = Gauss_Jordan.gaussJ(lgsGJ);
        //builds string out of returns from the algorithm
        StringBuilder resStringGJ = new StringBuilder();
        for (double v : solGJ)
            resStringGJ.append(v).append("\n");
        solutionGJTA.setText(resStringGJ.toString());
    }

    public void load(){
        IconHandler.handleIcon("algorithm");
    }

}
