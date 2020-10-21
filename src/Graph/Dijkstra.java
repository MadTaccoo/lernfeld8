package Graph;

public class Dijkstra {
    public static void dijsktra_algorithm(int[][] graph, int source) {
        int n = graph.length;

        boolean[] explored = new boolean[n];
        int[] distance = new int[n];

        for (int i = 0; i < n; i++) {
            explored[i] = false;
            distance[i] = Integer.MAX_VALUE;
        }

        distance[source] = 0;
        for (int j = 0; j < n ; j++) {

            int u = findShortestPath(distance, explored);
            explored[u] = true;

            for (int v = 0; v < n; v++) {
                if(!explored[v] && graph[u][v] != 0 && (distance[u] + graph[u][v] < distance[v])) {
                    distance[v] = distance[u] + graph[u][v];
                }
            }
        }

        printShortestPath(distance, source);
    }

    private static int findShortestPath(int[] distance, boolean[] explored) {
        int n = distance.length;
        int minDistance = Integer.MAX_VALUE;
        int minDistVertex = -1;

        for (int i = 0; i < n; i++) {
            if(!explored[i] && distance[i] < minDistance) {
                minDistance = distance[i];
                minDistVertex = i;
            }
        }

        return minDistVertex;
    }

    private static void printShortestPath(int[] distance, int source) {
        for (int i = 0; i < distance.length; i++) {
            System.out.printf("Distance from %d to %d is %d%n", source, i, distance[i]);
        }
    }


    public static void main(String[] args) {
        int graph[][] = new int[][] { {0, 2, 3},
                                      {1, 0, 0},
                                      {0, 4, 5}};
        dijsktra_algorithm(graph, 1);
    }
}
