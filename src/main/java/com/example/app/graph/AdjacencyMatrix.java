package com.example.app.graph;

public class AdjacencyMatrix {

    /*

            5    6
             \ /
          7 - 0
             / \
            1   2
           /    \
          3      4

      0 1 2 3 4 5 6 7
    0 0 1 1 0 0 1 1 1
    1 1 0 0 1 0 0 0 0
    2 1 0 0 0 1 0 0 0
    3 0 1 0 0 0 0 0 0
    4 0 0 1 0 0 0 0 0
    5 1 0 0 0 0 0 0 0
    6 1 0 0 0 0 0 0 0
    7 1 0 0 0 0 0 0 0

    */
    private static int[][] initializeUndirectedGraph() {
        int v = 8;
        int[][] graph = new int[v][v];

        graph[0][1] = 1;
        graph[1][0] = 1;

        graph[0][2] = 1;
        graph[2][0] = 1;

        graph[1][3] = 1;
        graph[3][1] = 1;

        graph[2][4] = 1;
        graph[4][2] = 1;

        graph[0][5] = 1;
        graph[5][0] = 1;

        graph[0][6] = 1;
        graph[6][0] = 1;

        graph[0][7] = 1;
        graph[7][0] = 1;

        return graph;
    }

    /*
             -> ==  -*

            4      3
             *    /
              \  *
               0
             /
            *
           1
            \
             *
              2

      0 1 2 3 4
    0 1 0 0 0 1
    1 0 0 1 0 0
    2 0 0 0 0 0
    3 1 0 0 0 0
    4 0 0 0 0 0

    */
    private static int[][] initializeDirectedGraph() {
        int v = 5;
        int[][] graph = new int[v][v];

        graph[0][1] = 1;
        graph[0][4] = 1;
        graph[1][2] = 1;
        graph[3][0] = 1;

        return graph;
    }

}
