package com.jmb.bfs;

import com.jmb.api.Graph;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Class represents the BFS algorithm, it includes its logic and utilizes the decoupled Graph
 * Data Type.
 *
 * @author JuanMBruno
 */
public class BreadthFirstSearchWithPaths {

  private static final int INFINITY = Integer.MAX_VALUE;
  private boolean[] marked;  // marked[v] = is there an s-v path - can be seen as "visited" array too.
  private int[] edgeTo;      // edgeTo[v] = previous edge on shortest s-v path
  private int[] distTo;      // distTo[v] = number of edges shortest s-v path

  public BreadthFirstSearchWithPaths(Graph G, int s) {
    marked = new boolean[G.V()];
    distTo = new int[G.V()];
    edgeTo = new int[G.V()];
    validateVertex(s);
  }

  /** Main method for the Graph Algorithm
   breadth-first search from a single source */
  public void bfs(Graph G, int s) {
    Queue<Integer> q = new PriorityQueue<>();

    //Traverse the Graph vertices and set the distance to it, originally to INFINITY, in case is unreachable (unconnected node)
    for (int v = 0; v < G.V(); v++) {
      distTo[v] = INFINITY;
    }

    //mark the source node as traversed (marked or visited) and having a distance of 0 with itself
    distTo[s] = 0;
    marked[s] = true;
    q.add(s);

    //Now start traversing relying on the aiding structures and completing them as it goes

    //While there are more vertices to traverse
    while(!q.isEmpty()) {
      int v = q.remove(); //get vertex
      //visit adjacent nodes and enqueue them as the next ones
      for(int w : G.adj(v)) {
        //but only if they haven't been visited before (mutiple nodes' connections)
        if(!marked[w]) {
          edgeTo[w] = v; //mark there is an edge, or connection, from this node to the parent (also called prev[])
          distTo[w] = distTo[v] + 1; //increase in 1 the distance compared to the previous distance
          marked[w] = true;
          q.add(w);
        }
      }
    }
  }

  //Is there an edge (connection) from w to v, for the current Graph processed?
  public int getEdgeTo(int w) {
    return edgeTo[w];
  }

  //What's the distance (levels) from v to w, for the current Graph processed?
  public int getDistTo(int w) {
    return distTo[w];
  }

  // throw an IllegalArgumentException unless {@code 0 <= v < V}
  private void validateVertex(int v) {
    int V = marked.length;
    if (v < 0 || v >= V)
      throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
  }

}
