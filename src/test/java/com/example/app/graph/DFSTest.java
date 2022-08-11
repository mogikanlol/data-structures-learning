package com.example.app.graph;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

public class DFSTest {

    @Test
    void testUndirectedGraphDFS() {
        List<List<Integer>> graph = GraphReader.listFromResourceFile("data/graph/undirected-graph-list.txt");

        List<Integer> result = DFS.dfs(0, graph);

        assertThat(result, contains(0, 1, 3, 2, 4, 5, 6, 7));
    }

    @ParameterizedTest
    @MethodSource("testDirectedGraphDFSMethodSource")
    void testDirectedGraphDFS(int start, Integer[] expectedResult) {
        List<List<Integer>> graph = GraphReader.listFromResourceFile("data/graph/directed-graph-list.txt");

        List<Integer> result = DFS.dfs(start, graph);

        assertThat(result, contains(expectedResult));
    }

    static Stream<Arguments> testDirectedGraphDFSMethodSource() {
        return Stream.of(
                Arguments.of(
                        3,
                        new Integer[]{3, 0, 1, 2, 4}
                ),
                Arguments.of(
                        0,
                        new Integer[]{0, 1, 2, 4}
                )
        );
    }



    @Test
    void testUndirectedGraphDFS_matrix() {
        int[][] graph = GraphReader.matrixFromResourceFile("data/graph/undirected-graph-matrix.txt");

        List<Integer> result = DFS.dfs(0, graph);

        assertThat(result, contains(0, 1, 3, 2, 4, 5, 6, 7));
    }

    @ParameterizedTest
    @MethodSource("testDirectedGraphDFSMethodSource_matrix")
    void testDirectedGraphDFS_matrix(int start, Integer[] expectedResult) {
        int[][] graph = GraphReader.matrixFromResourceFile("data/graph/directed-graph-matrix.txt");

        List<Integer> result = DFS.dfs(start, graph);

        assertThat(result, contains(expectedResult));
    }

    static Stream<Arguments> testDirectedGraphDFSMethodSource_matrix() {
        return Stream.of(
                Arguments.of(
                        3,
                        new Integer[]{3, 0, 1, 2, 4}
                ),
                Arguments.of(
                        0,
                        new Integer[]{0, 1, 2, 4}
                )
        );
    }

}
