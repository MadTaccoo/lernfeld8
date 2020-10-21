package Graph;

import java.util.HashMap;
import java.util.Map;

public class Vertex implements Comparable<Vertex> {
    public final String name;
    public int distance = Integer.MAX_VALUE;
    public Vertex previous = null;
    public Map<Vertex, Integer> neighbours = new HashMap<>();

    public Vertex(String name) {
        this.name = name;
    }


    @Override
    public int compareTo(Vertex other) {
        if (distance == other.distance) {
            return name.compareTo(other.name);
        }
        return Integer.compare(distance, other.distance);
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "name='" + name + '\'' +
                ", distance=" + distance +
                '}';
    }

    public void printPath() {
        if (this == this.previous) {
            System.out.printf("%s", this.name);
        } else if (this.previous == null) {
            System.out.printf("%s(not explored)", this.name);
        } else {
            this.previous.printPath();
            System.out.printf(" -> %s:%d", this.name, this.distance);
        }
    }
}
