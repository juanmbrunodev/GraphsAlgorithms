package com.jmb;

import com.jmb.api.Graph;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Undirected Graph implementation for the Graph interface.
 *
 * @author JuanMBruno
 */
public class UGraph implements Graph {

  private int V;
  private int E;
  private List<Integer>[] adj;


  public UGraph(int V) {
    this.V = V;
    adj = new List[V];
    for(int v = 0; v < V; v++) {
      adj[v] = new LinkedList<>();
    }
  }

  /**
   * Method reads a graph from an input stream, whether this is a text or an input from keyboard/console.
   * @param in
   */
  public UGraph(InputStream in) {
    if (in == null) throw new IllegalArgumentException("argument is null");
    try {
      this.V = in.read();
      if (V < 0) throw new IllegalArgumentException("number of vertices in a Graph must be non-negative");
      adj = (List<Integer>[]) new List[V];
      for (int v = 0; v < V; v++) {
        adj[v] = new LinkedList<>();
      }
      int E = in.read();
      if (E < 0) throw new IllegalArgumentException("number of edges in a Graph must be non-negative");
      for (int i = 0; i < E; i++) {
        int v = in.read();
        int w = in.read();
        validateVertex(v);
        validateVertex(w);
        addEdge(v, w);
      }
    }
    catch (NoSuchElementException | IOException e) {
      throw new IllegalArgumentException("invalid input format in Graph constructor", e);
    }
  }

  /**
   * Method reads a graph from an input stream, whether this is a text or an input from keyboard/console.
   * @param file Abstraction representing the file's input stream.
   */
  public UGraph(File file) throws FileNotFoundException {
    Scanner scanner = new Scanner(file);
    if (scanner.hasNext() == false) throw new IllegalArgumentException("argument is null");
    try {
      this.V = scanner.nextInt();
      if (V < 0) throw new IllegalArgumentException("number of Vertices in a Graph must be non-negative");
      adj = (List<Integer>[]) new List[V];
      for (int v = 0; v < V; v++) {
        adj[v] = new LinkedList<>();
      }
      int E = scanner.nextInt();
      if (E < 0) throw new IllegalArgumentException("number of Edges in a Graph must be non-negative");
      for (int i = 0; i < E; i++) {
        int v = scanner.nextInt();
        int w = scanner.nextInt();
        validateVertex(v);
        validateVertex(w);
        addEdge(v, w);
      }

    } finally {
      //do nothing
    }

  }

  //Being undirected both connections have to be made for the nodes passed
  @Override
  public void addEdge(int v, int w) {
    adj[v].add(w);
    adj[w].add(v);
    E++;
  }

  @Override
  public Iterable<Integer> adj(int v) {
    return adj[v];
  }

  @Override
  public int V() {
    return V;
  }

  @Override
  public int E() {
    return E;
  }

  // throw an IllegalArgumentException unless {@code 0 <= v < V}
  private void validateVertex(int v) {
    if (v < 0 || v >= V)
      throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
  }
}
