package Graph;

import java.security.KeyException;
import java.util.*;

public class Graph {
    private static Map<String, Vertex> graph;

    /**
     * Creation of the graph from a set of edges
     * @param edges of type Edge[]
     */

    public Graph (Edge[] edges) {
       this.graph = new HashMap<>(edges.length);

       for (Edge e : edges) {
           /* searching for all vertices */
           if (!graph.containsKey(e.v1)) {
               graph.put(e.v1, new Vertex(e.v1));
           }

           if (!graph.containsKey(e.v2)) {
               graph.put(e.v2, new Vertex(e.v2));
           }

           /* setting all the neighbouring vertices */
           graph.get(e.v1).neighbours.put(graph.get(e.v2), e.distance);
       }
   }

    /**
     * Execution of the dijkstra algorithm from a
     * entered starting vertex
     * @param startName (String) starting vertex
     * @throws Exception when starting vertex is not given
     */
   public void dijkstra(String startName) throws Exception{
       if (!graph.containsKey(startName)) {
           throw new KeyException("Graph does not contain start vertex " + startName);
       }
       final Vertex source = graph.get(startName);
       NavigableSet<Vertex> q = new TreeSet<>();

       /* setting up the vertices */
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

    /**
     * Implementation of dijkstra's algorithm
     * @param q (NavigableSet<Vertex>) in order to navigate
     * among the set elements
     */
   private void dijkstra(final NavigableSet<Vertex> q) {
       Vertex u, v;
       while (!q.isEmpty()) {
           u = q.pollFirst(); /* vertex with the shortest distance */
           if(u.distance == Integer.MAX_VALUE) {
               /* if the distance is 'infinity'(described as Integer.MAX_VALUE)
               * ignore u, since it is unreachable at this point*/
               break;
           }

           /* looking at the distances to each neighbour */
           for (Map.Entry<Vertex, Integer> a : u.neighbours.entrySet()) {
               v = a.getKey(); /* current neighbour */

               /* if a shorter distance to the neighbour exists,
               *  then update the distance */
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

    /**
     * prints the path from starting vertex
     * to the specified vertex
     * @param endName (String) specified vertex
     * @throws Exception when endName has not been specified
     */
   public void printPath(String endName) throws Exception{
       if(!graph.containsKey(endName)) {
           throw new KeyException("Graph does not contain end vertex" + endName);
       }
       graph.get(endName).printPath();
       System.out.println();
   }

    /**
     *
     * @return  list of all the final distances
     */
   public ArrayList<Integer> valuesOfALl(){
       ArrayList<Integer> ret = new ArrayList<>();
       for (Vertex v : graph.values()) {
           ret.add(v.distance);
       }
       return ret;
   }

   /* prints shortest path from starting vertex to all
   *  the other vertices */
   public void printAllPaths() {
       for (Vertex v : graph.values()) {
           v.printPath();
           System.out.println();
       }
   }
}
