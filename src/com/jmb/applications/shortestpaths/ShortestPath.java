package com.jmb.applications.shortestpaths;

import com.jmb.UGraph;
import com.jmb.api.Graph;
import com.jmb.bfs.BreadthFirstSearchWithPaths;

import java.io.*;

/**
 * Class creates a simple undirected Graph, and uses it with BFS to find the shortest path between to
 * nodes, if any.
 *
 * Basically this class acts as a client of both the Graph ADT and the BFS algorithm that uses the
 * latter.
 *
 * @author JuanMBruno
 */
public class ShortestPath {

    private static Graph graph;
    private static BreadthFirstSearchWithPaths bfs;

    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("/Users/JuanMBruno/Documents/Courses/Coursera/Princenton - Algorithms 2/Undirected Graphs/Graphs/src/com/jmb/applications/shortestpaths/ShortestPath.txt");

        //Initialize the graph with the file in txt representing its edges and connections (see graph.png)
        graph = new UGraph(file);
        int initialVertex = 0; //source for the search path
        //initialize the class holding the BFS algorithm, pass the graph and initial vertex
        bfs = new BreadthFirstSearchWithPaths(graph, initialVertex);

        /** Run the Breadth First Search Algorithm, populates within the BFS class the info needed */
        bfs.bfs(graph, initialVertex);

        //Now print the distance from the previous vertex to a desired other vertex
        int endVertex = 4;

        System.out.println("Distance from source node: " + initialVertex + " to destination node: "
                + endVertex + " is equal to: " + bfs.getDistTo(endVertex));

        //they are indirectly connected so there shouldn't be an edge between them
        System.out.println("Parent from source node: " + initialVertex + " to destination node: "
                + endVertex + " ? : " + bfs.getEdgeTo(endVertex));
    }
}
