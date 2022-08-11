package com.example.app.graph.dijkstra;

import com.example.app.graph.GraphReader;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;

public class Dijkstra_1_Test {

    @Test
    void test_1() {

        int[][] graph = GraphReader.matrixFromResourceFile("data/graph/dijkstra-1/1.txt");
        int[] expected = new int[] {0, 4, 3, 9};

        int start = 0;

        int[] result = Dijkstra_1.execute(graph, start);

        assertThat(result, Is.is(expected));
    }

    @Test
    void test_2() {

        int[][] graph = GraphReader.matrixFromResourceFile("data/graph/dijkstra-1/2.txt");
        int[] expected = new int[] {0, 7, 9, 20, 20, 11};

        int start = 0;

        int[] result = Dijkstra_1.execute(graph, start);

        assertThat(result, Is.is(expected));
    }

    @Test
    void test_3() {

        int[][] graph = GraphReader.matrixFromResourceFile("data/graph/dijkstra-1/3.txt");
        int[] expected = new int[] {0, 4, 3, 9, Integer.MAX_VALUE, Integer.MAX_VALUE};

        int start = 0;

        int[] result = Dijkstra_1.execute(graph, start);

        assertThat(result, Is.is(expected));
    }

    @ParameterizedTest
    @MethodSource("test_4_methodSource")
    void test_4(int dest, int expectedDistance) {

        int[][] graph = GraphReader.matrixFromResourceFile("data/graph/dijkstra-1/1.txt");

        int start = 0;

        int result = Dijkstra_1.execute(graph, start, dest);

        assertThat(result, Is.is(expectedDistance));
    }

    static Stream<Arguments> test_4_methodSource() {
        return Stream.of(
            Arguments.of(1, 4),
            Arguments.of(2, 3),
            Arguments.of(3, 9)
        );
    }
}
