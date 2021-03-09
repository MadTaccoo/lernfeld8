package Graph.DijkstraTest;

import Database.MySqlCon;
import Graph.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;


public class DijkstraAlgorithmTest {

    public int[] getData(ArrayList<String> ls) {
        String[] split = ls.get(1).split(" ");
        int[] ints = new int[split.length - 1];
        for (int j = 1; j < split.length; j++) {
            ints[j - 1] = Integer.parseInt(split[j]);
        }
        return ints;
    }

    @ParameterizedTest
    @DisplayName("Test DijkstraAlgo")
    @ValueSource(ints = {1,2,3})
    public void testD(int i) throws Exception {
        Edge[] graph = {
                new Edge("a", "b", Integer.MIN_VALUE),
                new Edge("a", "c", Integer.MIN_VALUE),
                new Edge("c", "e", Integer.MIN_VALUE),
                new Edge("c", "d", Integer.MIN_VALUE),
                new Edge("b", "e", Integer.MIN_VALUE),
                new Edge("b", "d", Integer.MIN_VALUE),
                new Edge("d", "e", Integer.MIN_VALUE),
                new Edge("d", "f", Integer.MIN_VALUE),
                new Edge("e", "f", Integer.MIN_VALUE)
        };

        ArrayList<String> ls = MySqlCon.query(
                "SELECT * " +
                        "FROM tbl_dijkstraRawData " +
                        "WHERE testID = " + i + ";");

        ArrayList<String> res = MySqlCon.query(
                "SELECT Solution_ID,data_3,data_4,data_5,data_6,data_7,data_8 " +
                        "FROM tbl_dijkstraSolutions " +
                        "WHERE Solution_ID = " + i + ";");
        int[] data = getData(ls);
        int[] resData = getData(res);
        for (int j = 0; j < data.length; j++) {
            graph[j].distance = data[j];
        }

        /*in case no start or endpoint has been set*/
        String startV = "a";

        /*initializes Graph object*/
        Graph gph = Dijkstra_Algorithm.Dijkstra(graph);

        gph.dijkstra(startV);

        int[] arr = gph.valuesOfALl().stream().mapToInt(x -> x).toArray();
        StringBuilder toAppend = new StringBuilder();
        for (int val : arr) {
            toAppend.append(",").append(val);
        }
        MySqlCon.update("INSERT INTO tbl_dijkstraRealResults(dateT,dataRow, startpoint, column_3, column_4, column_5, column_6, column_7, column_8) " +
                "values(CURRENT_TIME,"+ i +",'"+ startV +"'"+ toAppend+")");
        Assertions.assertArrayEquals(arr, resData);
    }
}
