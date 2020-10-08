package GUI.Controller;

import NumericalMathematics.Newton.Newton;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;

public class NewtonGraphController {
    @FXML
    LineChart graph;

    public void draw(){
        Series series = new Series();
        series.setName("Test");
        int type = 2;
        Newton.controller(type);
        double[][] ls = Newton.plottingTable(type);
        for (int i = 0; i < ls[0].length; i++) {
            series.getData().add(new XYChart.Data(ls[0][i], ls[1][i]));
        }
        graph.getData().add(series);
    }

}
