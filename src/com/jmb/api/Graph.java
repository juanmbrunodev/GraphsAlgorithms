package com.jmb.api;

public interface Graph {

  void addEdge(int v, int w);
  //Return the adjacent vertices from the one passed
  Iterable<Integer> adj(int v);
  //Return number of vertices
  int V();
  //Return number of edges
  int E();

}
