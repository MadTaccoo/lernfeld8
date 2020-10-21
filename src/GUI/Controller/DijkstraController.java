package GUI.Controller;


import GUI.MainGUI;
import Graph.Dijkstra_Algorithm;
import Graph.*;
import Interfaces.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import java.util.ArrayList;

public class DijkstraController implements Controller {
    private String startV = "",endV="";
    private TextArea[] taLS;
    private ArrayList<Button> bLS;
    private ArrayList<TextArea> lsL;

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

    @FXML
    Button aB,bB,cB,dB,eB,fB;
    @FXML
    TextArea abTA,acTA,ceTA,cdTA,beTA,bdTA,deTA,dfTA,efTA;
    @FXML
    TextArea aTA,bTA,cTA,dTA,eTA,fTA;
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
            case "menuB":
                MainGUI.goToMenu();
                break;

            case "resetB":
                startV = "";
                endV = "";
                for (int i = 0; i < lsL.size(); i++) {
                    lsL.get(i).setText("");
                }
                break;

            case "startB":
                for (int i = 0; i < graph.length; i++) {
                    graph[i].distance = Integer.parseInt(taLS[i].getText());
                }
                if(endV.equals("")||startV.equals(""))
                    return;
                Graph gph = Dijkstra_Algorithm.Dijkstra(graph);
                gph.dijkstra(startV);
                ArrayList<Integer> ls = gph.valuesOfALl();
                for (int i = 0; i < lsL.size(); i++) {
                    if(ls.get(i).equals(Integer.MAX_VALUE))
                        lsL.get(i).setText("-1");
                    else
                        lsL.get(i).setText(""+ls.get(i));
                }
                break;
            case "aB":
                if(startV.equals(""))
                    startV = "a";
                else
                    endV = "a";
                System.out.println("S:"+startV+" E:"+endV);
                break;
            case "bB":
                if (startV.equals(""))
                    startV = "b";
                else
                    endV = "b";
                System.out.println("S:"+startV+" E:"+endV);
                break;
            case "cB":
                if (startV.equals(""))
                    startV = "c";
                else
                    endV = "c";
                System.out.println("S:"+startV+" E:"+endV);
                break;
            case "dB":
                if (startV.equals(""))
                    startV = "d";
                else
                    endV = "d";
                System.out.println("S:"+startV+" E:"+endV);
                break;
            case "eB":
                if (startV.equals(""))
                    startV = "e";
                else
                    endV = "e";
                System.out.println("S:"+startV+" E:"+endV);
                break;

            case "fB":
                if (startV.equals(""))
                    startV = "f";
                else
                    endV = "f";
                System.out.println("S:"+startV+" E:"+endV);
                break;
        }

    }
}
