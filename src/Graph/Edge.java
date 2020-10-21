package Graph;

public class Edge {
    public final String v1, v2;
    public final int distance;

    public Edge(String v1, String v2, int distance) {
        this.v1 = v1;
        this.v2 = v2;
        this.distance = distance;
    }
}
