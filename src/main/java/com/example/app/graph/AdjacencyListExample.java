package com.example.app.graph;

import java.util.ArrayList;
import java.util.List;

public class AdjacencyListExample {

    /*

            5    6
             \ /
          7 - 0
             / \
            1   2
           /    \
          3      4

        0 [1, 2, 5, 6, 7]
        1 [0, 3]
        2 [0, 4]
        3 [1]
        4 [2]
        5 [0]
        6 [0]
        7 [0]
     */
    private static List<List<Integer>> initializeUndirectedGraph() {
        int v = 8;

        List<List<Integer>> list = new ArrayList<>(v);

        for (int i = 0; i < v; i++) {
            list.add(new ArrayList<>());
        }

        list.get(0).add(1);
        list.get(0).add(2);
        list.get(0).add(5);
        list.get(0).add(6);
        list.get(0).add(7);

        list.get(1).add(0);
        list.get(1).add(3);

        list.get(2).add(0);
        list.get(2).add(4);

        list.get(3).add(1);

        list.get(4).add(2);

        list.get(5).add(0);

        list.get(6).add(0);

        list.get(7).add(0);

        return list;
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

        0 [1, 4]
        1 [2]
        2 []
        3 [0]
        4 []
     */
    private static List<List<Integer>> initializeDirectedGraph() {
        int v = 5;

        List<List<Integer>> list = new ArrayList<>(v);

        for (int i = 0; i < v; i++) {
            list.add(new ArrayList<>());
        }

        list.get(0).add(1);
        list.get(0).add(4);

        list.get(1).add(2);

        list.get(3).add(0);

        return list;
    }

}
