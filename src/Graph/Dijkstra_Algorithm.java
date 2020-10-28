package Graph;
import Graph.*;

/**
 * Implementation of Dijkstra's algorithm for finding
 * the shortest path between vertices in a graph
 *
 * @author Wutthichai Laphutama
 */
public class Dijkstra_Algorithm {
    /*
    private static String start = "a", end = "e";

    private static final Edge[] graph = {
            new Edge("a", "b", 7),
            new Edge("a", "c", 9),
            new Edge("a", "f", 14),
            new Edge("b", "c", 10),
            new Edge("b", "d", 15),
            new Edge("c", "d", 11),
            new Edge("c", "f", 2),
            new Edge("d", "e", 6),
            new Edge("e", "f", 9),
    };

    private static final Edge[] graph = {
            new Edge("a", "b", 100),
            new Edge("a", "d", 50),
            new Edge("b", "e", 250),
            new Edge("b", "c", 100),
            new Edge("c", "e", 50),
            new Edge("d", "e", 250),};
    */
    public static Graph Dijkstra(Edge[] graph){
        return new Graph(graph);
    }


}
