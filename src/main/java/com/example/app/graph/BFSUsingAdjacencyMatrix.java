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
    https://youtu.be/x6N5FK6ArRk
    https://www.geeksforgeeks.org/implementation-of-bfs-using-adjacency-matrix/

    https://www.log2base2.com/data-structures/graph/adjacency-matrix-representation-of-graph.html
    https://www.javatpoint.com/graph-theory-graph-representations
    https://www.baeldung.com/java-graphs
    https://www.geeksforgeeks.org/graph-and-its-representations/

    V - amount of vertex

    Advantages:
        * Easy to implement
        * Adding/Removing an edge takes O(1) time
        * Queries like whether there is an edge between one vertex to another
         are efficient and take O(1) time

    Disadvantages:
        * Consumes O(V^2) space. Even if the graph is sparse (only a small amount of nodes are connected),
         it consumes the same space
        * Adding/Removing a vertex is O(V^2) time

 */
public class BFSUsingAdjacencyMatrix {

    public static void main(String[] args) {

        int[][] undirectedGraph = initializeUndirectedGraph();

        // should print: 0 1 2 5 6 7 3 4
        System.out.println("BFS Undirected Graph");
        bfs(0, undirectedGraph);

        int[][] directedGraph = initializeDirectedGraph();

        // should print: 3 0 1 4 2
        System.out.println("BFS Directed Graph");
        bfs(3, directedGraph);

        System.out.println("-----------");

        // should print: 0 1 4 2
        System.out.println("BFS Directed Graph");
        bfs(0, directedGraph);
    }

    private static List<Integer> bfs(int start, int[][] graph) {
        List<Integer> results = new ArrayList<>();
        int v = graph.length;
        boolean[] visited = new boolean[v];

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);

        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            results.add(poll);
            System.out.println(poll);
            for (int i = 0; i < v; i++) {
                if (i == poll) {
                    continue;
                }
                if (visited[i]) {
                    continue;
                }

                int node = graph[poll][i];

                if (node == 1) {
                    queue.offer(i);
                }
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

    @Test
    void testUndirectedGraph() {
        int[][] graph = initializeUndirectedGraph();

        List<Integer> results = bfs(0, graph);

        assertThat(results, contains(0, 1, 2, 5, 6, 7, 3, 4));
    }

    @ParameterizedTest
    @MethodSource("testDirectedGraphMethodSource")
    void testDirectedGraph(int start, Integer[] expectedResult) {
        int[][] graph = initializeDirectedGraph();

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
