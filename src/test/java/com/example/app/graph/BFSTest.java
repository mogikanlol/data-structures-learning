package com.example.app.graph;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

public class BFSTest {

    @Test
    void testUndirectedGraph() {
        List<List<Integer>> graph = GraphReader.listFromResourceFile("data/graph/undirected-graph-list.txt");

        List<Integer> results = BFS.bfs(0, graph);

        assertThat(results, contains(0, 1, 2, 5, 6, 7, 3, 4));
    }

    @ParameterizedTest
    @MethodSource("testDirectedGraphMethodSource")
    void testDirectedGraph(int start, Integer[] expectedResult) {
        List<List<Integer>> graph = GraphReader.listFromResourceFile("data/graph/directed-graph-list.txt");

        List<Integer> results = BFS.bfs(start, graph);

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



    @Test
    void testUndirectedGraph_matrix() {
        int[][] graph = GraphReader.matrixFromResourceFile("data/graph/undirected-graph-matrix.txt");

        List<Integer> results = BFS.bfs(0, graph);

        assertThat(results, contains(0, 1, 2, 5, 6, 7, 3, 4));
    }

    @ParameterizedTest
    @MethodSource("testDirectedGraphMethodSource_matrix")
    void testDirectedGraph_matrix(int start, Integer[] expectedResult) {
        int[][] graph = GraphReader.matrixFromResourceFile("data/graph/directed-graph-matrix.txt");

        List<Integer> results = BFS.bfs(start, graph);

        assertThat(results, contains(expectedResult));
    }

    static Stream<Arguments> testDirectedGraphMethodSource_matrix() {
        return Stream.of(
                Arguments.of(
                        3,
                        new Integer[]{3, 0, 1, 4, 2}
                ),
                Arguments.of(
                        0,
                        new Integer[]{0, 1, 4, 2}
                )
        );
    }

}
