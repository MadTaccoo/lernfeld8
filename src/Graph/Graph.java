package Graph;

import java.security.KeyException;
import java.util.*;

public class Graph {
    private static Map<String, Vertex> graph;

   public Graph (Edge[] edges) {
       this.graph = new HashMap<>(edges.length);
       for (Edge e : edges) {
           if (!graph.containsKey(e.v1)) {
               graph.put(e.v1, new Vertex(e.v1));
           }

           if (!graph.containsKey(e.v2)) {
               graph.put(e.v2, new Vertex(e.v2));
           }

           graph.get(e.v1).neighbours.put(graph.get(e.v2), e.distance);
       }
   }

   public void dijkstra(String startName) throws Exception{
       if (!graph.containsKey(startName)) {
           throw new KeyException("Graph does not contain start vertex " + startName);
       }
       final Vertex source = graph.get(startName);
       NavigableSet<Vertex> q = new TreeSet<>();

       for (Vertex v: graph.values()) {
           if (v == source) {
               v.previous = source;
           } else {
               v.previous = null;
           }

           if (v == source) {
               v.distance = 0;
           } else {
               v.distance = Integer.MAX_VALUE;
           }
           q.add(v);
       }
       dijkstra(q);
   }

   private void dijkstra(final NavigableSet<Vertex> q) {
       Vertex u, v;
       while (!q.isEmpty()) {
           u = q.pollFirst();
           if(u.distance == Integer.MAX_VALUE) {
               break;
           }

           for (Map.Entry<Vertex, Integer> a : u.neighbours.entrySet()) {
               v = a.getKey();

               final int shortestDistance = u.distance + a.getValue();
               if(shortestDistance < v.distance) {
                   q.remove(v);
                   v.distance = shortestDistance;
                   v.previous = u;
                   q.add(v);
               }
           }
       }
   }

   public void printPath(String endName) throws Exception{
       if(!graph.containsKey(endName)) {
           throw new KeyException("Graph does not contain end vertex" + endName);
       }
       graph.get(endName).printPath();
       System.out.println();
   }

   public ArrayList<Integer> valuesOfALl(){
       ArrayList<Integer> ret = new ArrayList<>();
       for (Vertex v : graph.values()) {
           ret.add(v.distance);
       }
       return ret;
   }

   public void printAllPaths() {
       for (Vertex v : graph.values()) {
           v.printPath();
           System.out.println();
       }
   }
}
