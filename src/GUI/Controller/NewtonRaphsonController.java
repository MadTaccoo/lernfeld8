package GUI.Controller;

import GUI.MainGUI;
import Interfaces.Controller;
import NumericalMathematics.Newton.Function;
import Parser.FunctionParser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.GridPane;
import java.io.IOException;
import java.util.ArrayList;

public class NewtonRaphsonController implements Controller {
    @FXML
    LineChart graph;
    @FXML
    NumberAxis xAxis, yAxis;
    @FXML
    TextField funcEntry;
    @FXML
    GridPane mainGrid;
    @FXML
    Label rootsL;
    //defines the X range of the plot
    private double offsetSteps = 1;
    private double[][] interval;
    private int searchRootInterval = 10;
    private final float initalPrec = 0.1F;

    private ArrayList<Double> roots = new ArrayList<>();
    private Function functionToDraw;

    /**
     * this function allows the user to draw a passed function
     * and select a range in which the function will be drawn
     * also the user is able to select the precision of the steps
     *
     * @param f    function which you want to draw
     * @param x    start of table for values
     * @param x1   end of table for values
     * @param prec precision of steps
     */
    public void draw(Function f, double x, double x1, double prec) {
        if (x >= x1)
            throw new IllegalArgumentException("x must be smaller than x1");
        graph.getData().clear();
        Series series = new Series();
        series.setName("Test");
        System.out.println(f.toString());
        ArrayList<ArrayList<Double>> ls = f.table(x, x1, prec);

        for (int i = 0; i < ls.get(0).size(); i++) {
            series.getData().add(new XYChart.Data(ls.get(0).get(i), ls.get(1).get(i)));
        }
        graph.getData().add(series);
    }

    /**
     * this function allows the user to draw a passed function
     * the range of the table is fitted to the lineChart to display the function
     * also the user is able to select the precision of the steps
     *
     * @param f    function which you want to draw
     * @param prec precision of steps
     */
    public void draw(Function f, double prec) {
        //clears old graph data
        graph.getData().clear();
        Series series = new Series();
        //create an ArrayList containing more 2 Array lists which then contain Coordinates
        ArrayList<ArrayList<Double>> ls = f.table(xAxis.getLowerBound(), xAxis.getUpperBound(), prec);
        //Creates for each Coordinate a XYChart Data which then can be used to display a graph
        for (int i = 0; i < ls.get(0).size(); i++)
            series.getData().add(new XYChart.Data(ls.get(0).get(i), ls.get(1).get(i)));
        graph.getData().add(series);

        //calls the algorithm to get intervals of roots and than approximates the root with newton-method
        //finally returns array with roots for given function
        functionToDraw.setIntervalAndRoots((int) xAxis.getLowerBound(), (int) xAxis.getUpperBound(), (float) prec);
        StringBuilder rootStr = new StringBuilder();
        for (int i = 0; i < functionToDraw.roots.size(); i++) {
            //round each root to 3 values behind the decimal dot
            rootStr.append(round(functionToDraw.roots.get(i), 3)).append(", ");
        }
        rootStr = new StringBuilder(rootStr.substring(0, rootStr.length() - 2));
        rootsL.setText("");
        //displays the roots as formatted text
        rootsL.setText(rootStr.toString());
    }

    /**
     * function to round to a specified place
     * @param value value to be rounded
     * @param places space behind .
     * @return rounded value
     */
    public double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    /**
     * this function handles all click events of Buttons
     * it uses the fxml id of the button to identify it and than execute the code for the button
     *
     * @param e ActionEvent used to get the source of the event
     * @throws IOException
     */
    @FXML
    public void handleButtons(ActionEvent e) throws IOException {
        Button b = null;
        //in case the event source is a Button
        if (e.getSource() instanceof Button) {
            b = (Button) e.getSource();
        }
        assert b != null;
        //where we use the FXML id of the buttons to identify them and give them a function
        String ident = b.getId();
        switch (ident) {
            case "xPlusB":
                //adjusts bounds of lineChart and redraws the function
                xAxis.setUpperBound(xAxis.getUpperBound() - offsetSteps);
                xAxis.setLowerBound(xAxis.getLowerBound() + offsetSteps);

                yAxis.setUpperBound(yAxis.getUpperBound() - offsetSteps);
                yAxis.setLowerBound(yAxis.getLowerBound() + offsetSteps);
                draw(functionToDraw, initalPrec);
                break;
            case "xMinusB":
                //adjusts bounds of lineChart and redraws the function
                xAxis.setUpperBound(xAxis.getUpperBound() + offsetSteps);
                xAxis.setLowerBound(xAxis.getLowerBound() - offsetSteps);

                yAxis.setUpperBound(yAxis.getUpperBound() + offsetSteps);
                yAxis.setLowerBound(yAxis.getLowerBound() - offsetSteps);
                draw(functionToDraw, initalPrec);
                break;
            case "drawB":
                draw(functionToDraw, initalPrec);
                break;
            case "rightB":
                //adjusts bounds of lineChart and redraws the function
                xAxis.setUpperBound(xAxis.getUpperBound() + offsetSteps);
                xAxis.setLowerBound(xAxis.getLowerBound() + offsetSteps);
                draw(functionToDraw, initalPrec);
                break;
            case "leftB":
                //adjusts bounds of lineChart and redraws the function
                xAxis.setUpperBound(xAxis.getUpperBound() - offsetSteps);
                xAxis.setLowerBound(xAxis.getLowerBound() - offsetSteps);
                draw(functionToDraw, initalPrec);
                break;
            case "upB":
                //adjusts bounds of lineChart and redraws the function
                yAxis.setUpperBound(yAxis.getUpperBound() + offsetSteps);
                yAxis.setLowerBound(yAxis.getLowerBound() + offsetSteps);
                draw(functionToDraw, initalPrec);
                break;
            case "downB":
                //adjusts bounds of lineChart and redraws the function
                yAxis.setUpperBound(yAxis.getUpperBound() - offsetSteps);
                yAxis.setLowerBound(yAxis.getLowerBound() - offsetSteps);
                draw(functionToDraw, initalPrec);
                break;
            case "readFunc":
                //makes use of the Function Parser to generate a function
                rootsL.setText("");
                functionToDraw = FunctionParser.parse(funcEntry.getText());
                draw(functionToDraw, initalPrec);
                break;

            case "menuB":
                MainGUI.goToMenu();
                break;
        }
    }

    /**
     * mouse scroll event to allow the user to scroll out of the lineChart to see more of the function
     * @param node in this special case the node is the lineChart but in theory it could be any control item
     */
    public void addMouseScrolling(Node node) {
        node.setOnScroll((ScrollEvent event) -> {
            double deltaY = event.getDeltaY();

            if (deltaY > 0 && xAxis.getUpperBound() > 1) {
                xAxis.setUpperBound(xAxis.getUpperBound() - 1);
                xAxis.setLowerBound(xAxis.getLowerBound() + 1);
                yAxis.setUpperBound(yAxis.getUpperBound() - 1);
                yAxis.setLowerBound(yAxis.getLowerBound() + 1);
            } else if (deltaY < 0 && xAxis.getUpperBound() >= 1) {
                xAxis.setUpperBound(xAxis.getUpperBound() + 1);
                xAxis.setLowerBound(xAxis.getLowerBound() - 1);
                yAxis.setUpperBound(yAxis.getUpperBound() + 1);
                yAxis.setLowerBound(yAxis.getLowerBound() - 1);
            }

            draw(functionToDraw, initalPrec);
        });
    }

    /**
     * load function is called when the controller is first initiated
     * it loads a function to show that it is operational
     * also enables the scroll event
     */
    public void load() {
        IconHandler.handleIcon("algorithm");
        functionToDraw = new Function();
        //just an initial Function can be changed later
        functionToDraw.insertInto(3, 1);
        functionToDraw.insertInto(2, 1);
        functionToDraw.insertInto(0, -1.0 / 10.0);
        draw(functionToDraw, initalPrec);
        addMouseScrolling(graph);
    }
}
