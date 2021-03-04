package com.jmb.dfs;

import com.jmb.api.Graph;

/**
 * Class implements the DFS or DepthFirstSearch algorithm.
 *
 * In doing so, it utilises the {@link com.jmb.api.Graph} Data Structure.
 *
 * This API operates on a Graph at a time based on a vertex passed as the source node for the
 * search.
 *
 * @author JuanMBruno
 */
public class DepthFirstSearch {

    //array containing the 'visited' or marked vertices
    private boolean[] marked;

    //number of vertices connected to 's'
    private int count;

    //Graph reference for DFS algorithm
    private Graph G;

    //Constructor receives a Graph type populated and a initial vertex
    public DepthFirstSearch(Graph G, int s) {
        marked = new boolean[G.V()];
        validateVertex(s);
        this.G = G;
    }

    /**
     * Implementation of the DFS.
     *
     * @param s vertex to run DFS against.
     */
    public void dfs(int s) {
        //Mark the root vertex // curr recursion vertex as visited when the funcion is called for an 's'
        marked[s] = true;

        //Loop through the connected vertices (adj list), recursively going through all connections for the
        //current node in the stack, and mark the visited nodes adjacent, provided is not already marked
        //or visiting
        for(int i : G.adj(s)) {
            if(!marked(s)) {
                dfs(s);
            }
        }
    }

    public boolean marked(int v) {
        validateVertex(v);
        return marked[v];
    }

    /**
     * Returns the number of vertices connected to the source vertex {@code s}.
     * @return the number of vertices connected to the source vertex {@code s}
     */
    public int count() {
        return count;
    }

    private void validateVertex(int s) {
        if(s < 0 || s > marked.length)
            throw new IllegalArgumentException("Vertex value is incorrect");
    }
}
