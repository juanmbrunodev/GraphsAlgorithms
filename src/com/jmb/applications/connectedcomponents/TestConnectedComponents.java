package com.jmb.applications.connectedcomponents;

import com.jmb.UGraph;
import com.jmb.api.Graph;
import com.jmb.connectedcomponents.CC;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Test client for the Connected Components API (CC) to provide answer in constant time (O(1) regarding
 * effective connection of two given vertices in a Graph.
 *
 * For the logic of this algorithm please refer to {@link com.jmb.connectedcomponents.CC}
 */
public class TestConnectedComponents {

    private static Graph graph;
    private static CC cc;

    public static void main(String[] args) throws FileNotFoundException {

        //Read Graph into appropriate structure
        File file = new File("/Users/JuanMBruno/Documents/Projects/GraphsAlgorithms/src/com/jmb/applications/connectedcomponents/GraphInput.txt");
        graph = new UGraph(file);

        //Instantiate the CC API, thus triggering pre-processing of Graph with DFS
        cc = new CC(graph);

        //Evaluate correctness of queries around connected components (see file graph.png for visual
        //interpretation)

        //Assert size of components expected
        assert cc.count() == 3;

        //Assert connections in all three connected components
        assert cc.connected(0, 6);
        assert cc.connected(7, 8);
        assert cc.connected(9, 12);

        //Assert unconnected components
        assert cc.connected(0, 12) == false;

        //Assert sizes of each component as expected
        assert cc.size(0) == 7;
        assert cc.size(7) == 2;
        assert cc.size(9) == 4;

        //assert ids of vertices for three components
        assert cc.id(0) == 0;
        assert cc.id(7) == 1;
        assert cc.id(12) == 2;

        System.out.println("ALL TESTS PASSED");
    }

}
