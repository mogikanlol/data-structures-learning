package com.example.app.graph.dijkstra;

import java.util.Arrays;

public class Dijkstra_1 {

    public static int[] execute(int[][] graph, int start) {

        boolean[] visited = new boolean[graph.length];
        int[] distance = new int[graph.length];

        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;

        int currentNode = start;

        while (currentNode != -1) {
            visited[currentNode] = true;

            for (int i = 0; i < graph[currentNode].length; i++) {
                if (graph[currentNode][i] > 0 && !visited[i]) {
                    int weight = graph[currentNode][i];
                    int newDistance = distance[currentNode] + weight;
                    if (newDistance < distance[i]) {
                        distance[i] = newDistance;
                    }
                }
            }

            currentNode = getNextNode(distance, visited);
        }

        return distance;
    }

    public static int execute(int[][] graph, int start, int dest) {

        boolean[] visited = new boolean[graph.length];
        int[] distance = new int[graph.length];

        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;

        int currentNode = start;

        while (currentNode != -1) {
            if (visited[dest]) {
                break;
            }
            visited[currentNode] = true;

            for (int i = 0; i < graph[currentNode].length; i++) {
                if (graph[currentNode][i] > 0 && !visited[i]) {
                    int weight = graph[currentNode][i];
                    int newDistance = distance[currentNode] + weight;
                    if (newDistance < distance[i]) {
                        distance[i] = newDistance;
                    }
                }
            }

            currentNode = getNextNode(distance, visited);
        }

        return distance[dest];
    }

    private static int getNextNode(int[] distance, boolean[] visited) {
        int resultDistance = Integer.MAX_VALUE;
        int result = -1;

        for (int i = 0; i < distance.length; i++) {
            if (distance[i] < resultDistance && !visited[i]) {
                resultDistance = distance[i];
                result = i;
            }
        }

        return result;
    }


    /*
    private static int getNextNode(int currentNode, int[][] graph, boolean[] visited, int[] distance) {
        int resultDistance = Integer.MAX_VALUE;
        int result = -1;

        for (int i = 0; i < graph[currentNode].length; i++) {
            if (graph[currentNode][i] > 0 && !visited[i]) {
                if (distance[i] < resultDistance) {
                    resultDistance = distance[i];
                    result = i;
                }
            }
        }

        return result;
    }
     */

}
