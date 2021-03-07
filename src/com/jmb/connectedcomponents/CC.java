package com.jmb.connectedcomponents;

import com.jmb.api.Graph;

/**
 *  Compute connected components using depth first search.
 *  Runs in O(E + V) time.
 *
 *  The {@code CC} class represents a data type for
 *  determining the connected components in an undirected graph.
 *  The <em>id</em> operation determines in which connected component
 *  a given vertex lies; the <em>connected</em> operation
 *  determines whether two vertices are in the same connected component;
 *  the <em>count</em> operation determines the number of connected
 *  components; and the <em>size</em> operation determines the number
 *  of vertices in the connect component containing a given vertex.

 *  The <em>component identifier</em> of a connected component is one of the
 *  vertices in the connected component: two vertices have the same component
 *  identifier if and only if they are in the same connected component.

 *  <p>
 *  This implementation uses depth-first search.
 */
public class CC {

    //id[v] = id of connected component containing v
    private int[] id;
    //size of each component on the graph (component = subgraph with connections)
    private int[] size;
    //Used to track in DFS visited nodes
    private boolean marked[];
    //Number of connected components
    private int count;

    /**
     * Constructor does the DFS pre-processing to answer later connected() query in O(1) time.
     * unlike DFS it doesn't require an initial vertex as it maps components and connections by
     * going through all (unvisited) vertices subsequently.
     *
     * @param G Graph to map the components from.
     */
    public CC(Graph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        size = new int[G.V()];
        calculateConnected(G);
    }

    private void calculateConnected(Graph G) {
        //Do a recursion 'pass' for every unmarked component
        for(int v = 0; v < G.V(); v++) {
            if(!marked[v]) {
                dfs(G, v);
                count++; //exiting recursion means a new component has been mapped
            }
        }
    }

    //Depth First Search used as main algorithm
    private void dfs(Graph G, int v) {
        marked[v] = true;
        id[v] = count;
        size[count]++; //increase the size for the component id
        //go through edges and adjacency list and calculate
        for(int w : G.adj(v)) {
            if(!marked[w]) {
                dfs(G, w);
            }
        }
    }

    //Main method of API to check connection of components in constant time (O(1))
    public boolean connected(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        return id[v] == id[w];
    }

    public int id(int v) {
        validateVertex(v);
        return id[v];
    }

    public int count() {
        return count;
    }

    //Return the size of the component id for a vertex
    public int size(int v) {
        validateVertex(v);
        return size[id[v]];
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

}
