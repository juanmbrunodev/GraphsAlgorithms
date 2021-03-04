package com.jmb.util;

import com.jmb.api.Graph;

public class GraphUtil {

  //Method calculates the number of vertices adjacent to a vertex V. (other vertices connected to it)
  public static int degree(Graph G, int v) {
    int degree = 0;
    for (int w : G.adj(v)) {
      degree++;
    }
    return degree;
  }

  //Computes the max degree of all the graph vertices' degrees
  public static int maxDegree(Graph G) {
    int max = 0;
    for (int v = 0; v < G.V(); v++) {
      if ((degree(G, v)) > max) {
        max = degree(G, v);
      }


    }
    return max;
  }

  //Compute average degree
  public static double averageDegree(Graph G) {
    return 2.0 * G.E() / G.V();
  }

  //Count self loops
  public static int numberOfSelfLoops(Graph G) {
    int count = 0;
    for(int v = 0; v < G.V(); v++) {
      for(int w : G.adj(v)) {
        if(v == w) count++;
      }
    }
    return count / 2; //each edge counted twice
  }


}

