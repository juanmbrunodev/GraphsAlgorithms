package com.jmb.applications.dfspaths;

import com.jmb.UGraph;
import com.jmb.api.Graph;
import com.jmb.bfs.BreadthFirstSearchWithPaths;
import com.jmb.dfs.DepthFirstPaths;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;

/**
 * Class creates a simple undirected Graph, and uses it with DFS to find if there is a path between to
 * nodes.
 *
 * Basically this class acts as a client of both the Graph ADT and the BFS algorithm that uses the
 * latter.
 *
 * @author JuanMBruno
 */
public class IsTherePath {

    private static Graph graph;
    private static BreadthFirstSearchWithPaths dfs;

    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("/Users/JuanMBruno/Documents/Courses/Coursera/Princenton - Algorithms 2/Undirected Graphs/Graphs/src/com/jmb/applications/dfspaths/IsTherePath.txt");

        //Initialize the graph with the file in txt representing its edges and connections (see graph.png)
        graph = new UGraph(file);
        int initialVertex = 0; //source for the search path
        //initialize the class holding the BFS algorithm, pass the graph and initial vertex
        DepthFirstPaths dfs = new DepthFirstPaths(graph, initialVertex);

        //Now print the path from a vertex to S, if any
        int vertex = 3;

        System.out.println("Is there a Path from: " + vertex + " to vertex: " + initialVertex + " ? - "
                + dfs.hasPathTo(vertex));

        System.out.println(" Path: ");

        if(Objects.nonNull(dfs.pathTo(vertex))) {
            for (int x : dfs.pathTo(vertex)) {
                if (x == initialVertex) System.out.print(x);
                else        System.out.print(x + " - ");
            }
            System.out.println();
        }

    }
}
