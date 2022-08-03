package com.example.app.graph;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

/*
    https://youtu.be/3AtEzK4sowk

    https://www.log2base2.com/data-structures/graph/adjacency-list-representation-of-graph.html
    https://www.javatpoint.com/graph-theory-graph-representations
    https://www.baeldung.com/java-graphs
    https://www.geeksforgeeks.org/graph-and-its-representations/

    V - amount of vertex
    E - amount of edges

    Advantages:
        * Saves space O(V + E)
        * Adding an edge takes O(1) time
        * Adding a vertex takes O(1) time

    Disadvantages:
        * Queries like whether there is an edge between one vertex to another
         are not efficient and take O(V) time
        * Time taken to remove an edge takes O(E)
        * Time taken to remove a vertex takes O(V+E)

 */
public class BFSUsingAdjacencyList {

    public static void main(String[] args) {

        List<List<Integer>> undirectedGraph = initializeUndirectedGraph();

        // should print: 0 1 2 5 6 7 3 4
        System.out.println("BFS Undirected Graph");
        bfs(0, undirectedGraph);

        List<List<Integer>> directedGraph = initializeDirectedGraph();

        // should print: 3 0 1 4 2
        System.out.println("BFS Directed Graph");
        bfs(3, directedGraph);

        System.out.println("-----------");

        // should print: 0 1 4 2
        System.out.println("BFS Directed Graph");
        bfs(0, directedGraph);

    }

    private static List<Integer> bfs(int start, List<List<Integer>> graph) {
        List<Integer> results = new ArrayList<>();
        int v = graph.size();
        boolean[] visited = new boolean[v];

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);

        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            results.add(poll);
            System.out.println(poll);

            List<Integer> integers = graph.get(poll);
            for (Integer i : integers) {
                if (visited[i]) {
                    continue;
                }

                queue.offer(i);
            }

            visited[poll] = true;
        }

        return results;
    }
    /*

            5    6
             \ /
          7 - 0
             / \
            1   2
           /    \
          3      4

        0 [1, 2, 5, 6, 7]
        1 [0. 3]
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


    @Test
    void testUndirectedGraph() {
        List<List<Integer>> graph = initializeUndirectedGraph();

        List<Integer> results = bfs(0, graph);

        assertThat(results, contains(0, 1, 2, 5, 6, 7, 3, 4));
    }

    @ParameterizedTest
    @MethodSource("testDirectedGraphMethodSource")
    void testDirectedGraph(int start, Integer[] expectedResult) {
        List<List<Integer>> graph = initializeDirectedGraph();

        List<Integer> results = bfs(start, graph);

        assertThat(results, contains(expectedResult));
    }

    static Stream<Arguments> testDirectedGraphMethodSource() {
        return Stream.of(
                Arguments.of(
                        3,
                        new Integer[] {3, 0, 1, 4, 2}
                ),
                Arguments.of(
                        0,
                        new Integer[] {0, 1, 4, 2}
                )
        );
    }
}
