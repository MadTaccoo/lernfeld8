package GUI.Controller;

import GUI.MainGUI;
import Graph.Dijkstra_Algorithm;
import Graph.*;
import Interfaces.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Control Class for Dijkstra Visualizer
 */
public class DijkstraController implements Controller {
    /*start and endpoint for Dijkstra Algorithm*/
    private String startV = "",endV="";
    /*Lists which contain Controls for easier management later on*/
    private TextArea[] taLS;
    private ArrayList<TextArea> lsL;

    /*edges for the given graph*/
    private static Edge[] graph = {
            new Edge("a", "b", Integer.MIN_VALUE),
            new Edge("a", "c", Integer.MIN_VALUE),
            new Edge("c", "e", Integer.MIN_VALUE),
            new Edge("c", "d", Integer.MIN_VALUE),
            new Edge("b", "e", Integer.MIN_VALUE),
            new Edge("b", "d", Integer.MIN_VALUE),
            new Edge("d", "e", Integer.MIN_VALUE),
            new Edge("d", "f", Integer.MIN_VALUE),
            new Edge("e", "f", Integer.MIN_VALUE)};

    /*TextArea FXML imports for later use in Program*/
    @FXML
    TextArea abTA,acTA,ceTA,cdTA,beTA,bdTA,deTA,dfTA,efTA,aTA,bTA,cTA,dTA,eTA,fTA;

    /**
     * load function to get the controller ready for the user
     * fills Arrays and ArrayLists which make it easier to access all the controls
    */
    public void load() {
        taLS = new TextArea[9];
        taLS[0] = abTA;
        taLS[1] = acTA;
        taLS[2] = ceTA;
        taLS[3] = cdTA;
        taLS[4] = beTA;
        taLS[5] = bdTA;
        taLS[6] = deTA;
        taLS[7] = dfTA;
        taLS[8] = efTA;
        lsL = new ArrayList<>();
        lsL.add(aTA);
        lsL.add(bTA);
        lsL.add(cTA);
        lsL.add(dTA);
        lsL.add(eTA);
        lsL.add(fTA);
    }

    /**
     * this function handles all click events of Buttons
     * it uses the fxml id of the button to identify it and than execute the code for the button
     * @param e ActionEvent used to get the source of the event
     * @throws IOException
     */
    @FXML
    public void handleButtons(ActionEvent e) throws Exception {
        Button b = null;
        //in case the event source is a Button
        if(e.getSource() instanceof Button) {
            b = (Button)e.getSource();
        }
        assert b != null;
        //where we use the FXML id of the buttons to identify them and give them a function
        String ident = b.getId();
        switch (ident){
            /*enables the user to return to the menu*/
            case "menuB":
                MainGUI.goToMenu();
                break;
            /*resets the result values*/
            case "resetB":
                startV = "";
                endV = "";
                for (TextArea textArea : lsL) {
                    textArea.setText("");
                }
                break;
            /*starts the Dijkstra algorithm*/
            case "startB":
                /*reads all values from xxTA TextAreas which contain edges costs and sets the cost to the edge*/
                for (int i = 0; i < graph.length; i++)
                    graph[i].distance = Integer.parseInt(taLS[i].getText());
                /*in case no start or endpoint has been set*/
                if(startV.equals("")){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error");
                    alert.setHeaderText("You should have selected a Start point!");
                    alert.show();
                    return;
                }
                /*initializes Graph object*/
                Graph gph = Dijkstra_Algorithm.Dijkstra(graph);
                /*starts the algorithm and makes gph ready to read data from*/
                gph.dijkstra(startV);
                /*returns the value of each vertex(Point)*/
                ArrayList<Integer> ls = gph.valuesOfALl();
                for (int i = 0; i < lsL.size(); i++) {
                    if(ls.get(i).equals(Integer.MAX_VALUE))
                        lsL.get(i).setText("-1");
                    else
                        lsL.get(i).setText(""+ls.get(i));
                }
                break;
            case "aB":
                /*sets startV*/
                if(startV.equals(""))
                    startV = "a";
                System.out.println("S:"+startV+" E:"+endV);
                break;
            case "bB":
                /*sets startV*/
                if (startV.equals(""))
                    startV = "b";
                System.out.println("S:"+startV+" E:"+endV);
                break;
            case "cB":
                /*sets startV*/
                if (startV.equals(""))
                    startV = "c";
                System.out.println("S:"+startV+" E:"+endV);
                break;
            case "dB":
                /*sets startV*/
                if (startV.equals(""))
                    startV = "d";
                System.out.println("S:"+startV+" E:"+endV);
                break;
            case "eB":
                /*sets startV*/
                if (startV.equals(""))
                    startV = "e";
                System.out.println("S:"+startV+" E:"+endV);
                break;
            case "fB":
                /*sets startV*/
                if (startV.equals(""))
                    startV = "f";
                System.out.println("S:"+startV+" E:"+endV);
                break;
        }
    }
}
