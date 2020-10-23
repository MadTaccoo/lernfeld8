package Graph;
import Graph.*;

/**
 * Implementation of Dijkstra's Algorithm in which you try to find
 * the shortest path from an entered starting vertex to all neighbouring
 * vertices
 *
 * @author Wutthichai Laphutama
 */
public class Dijkstra_Algorithm {
    /*
    private static String start = "a", end = "e";


    For testing purposes
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
    };*/
    /*
    private static final Edge[] graph = {
            new Edge("a", "b", 100),
            new Edge("a", "d", 50),
            new Edge("b", "e", 250),
            new Edge("b", "c", 100),
            new Edge("c", "e", 50),
            new Edge("d", "e", 250),};


    public static void main(String[] args) throws Exception {
        Graph g = new Graph(graph);
        g.dijkstra(start);
        g.printAllPaths();
    }*/

    public static Graph Dijkstra(Edge[] graph){
        return new Graph(graph);
    }


}
