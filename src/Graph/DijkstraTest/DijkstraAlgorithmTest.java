package Graph.DijkstraTest;

import Graph.*;
import javafx.scene.control.Alert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

public class DijkstraAlgorithmTest {
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

    @Test
    public void testD() throws Exception {
        for (Edge edge : graph) edge.distance = new Random().nextInt(1000);
        /*in case no start or endpoint has been set*/
        String startV = "a", endV="b";
        /*initializes Graph object*/
        Graph gph = Dijkstra_Algorithm.Dijkstra(graph);
        /*starts the algorithm and makes gph ready to read data from*/
        gph.dijkstra(startV);
        /*returns the value of each vertex(Point)*/
        ArrayList<Integer> ls = gph.valuesOfALl();
        System.out.println(ls.toArray().toString()); //abcdef
    }
}
