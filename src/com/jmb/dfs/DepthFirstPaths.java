package com.jmb.dfs;

import com.jmb.api.Graph;

import java.util.Stack;

/**
 *  This class represents a data type for finding
 *  paths from a source vertex <em>s</em> to every other vertex
 *  in an undirected graph.
 *  <p>
 *  This implementation uses depth-first search.
 *
 * @author JuanMBruno
 */
public class DepthFirstPaths {

    //array containing the 'visited' or marked vertices - needed to avoid loops in recursion
    private boolean[] marked;
    //Keeps links between vertices, if the values of i = path[i], then that's the root of the path.
    int[] path;
    // source vertex
    private final int s;

    //Constructor receives both the Graph ADT populated (undirected for this algorithm) and the source node
    //to find paths from.
    public DepthFirstPaths(Graph G, int s) {
        this.s  = s;
        marked = new boolean[G.V()]; //size is as the worst case scenario - S connected to every node in G
        path = new int[G.V()];
        dfs(G, s);
    }

    /**
     * Actual Depth First Search algorithm implementation -recursive-
     *
     * @param G The Graph to run the algorithm against
     * @param s The source node from which we traverse and plot a path into path[]
     */
    private void dfs(Graph G, int s) {
        marked[s] = true;
        for(int v : G.adj(s)) {
            //if hasn't been visited
            if(!marked[v]) {
                //Add to build the path information
                path[v] = s; //a path 'way' is constructed from previous node (s) as the current path val (v)
                dfs(G, v);
            }
        }
    }

    //Method returns a path from S to the desired node (as end node) in the shape of an Iterable<Integer> object
    // Returns null if no path is found - if path[n] = n -
    public Iterable<Integer> pathTo(int v) {
        //if no path exists, return null
        if(!hasPathTo(v))
            return null;

        Stack<Integer> pathStack = new Stack<>();
        for (int x = v; x != s; x = path[x])
            pathStack.push(x);

        pathStack.push(s);
        return pathStack;
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

}
