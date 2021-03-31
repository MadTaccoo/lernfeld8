package Graph;

public class Edge {
    public String v1, v2; /* Start and end vertices */
    public int distance; /* Distance between vertices */

    public Edge(String v1, String v2, int distance) {
        /* Constructor */
        this.v1 = v1;
        this.v2 = v2;
        this.distance = distance;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
