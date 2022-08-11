package com.example.app.graph.dijkstra;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class Dijkstra_2 {
    public static int[] execute(int[][] graph, int start) {

        boolean[] visited = new boolean[graph.length];
        int[] distance = new int[graph.length];
        Queue<Node> nextToVisit = new PriorityQueue<>();

        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;

        nextToVisit.offer(Node.of(start, distance[start]));

        while (!nextToVisit.isEmpty()) {
            Node currentNode = nextToVisit.poll();
            visited[currentNode.x] = true;

            for (int i = 0; i < graph[currentNode.x].length; i++) {
                if (graph[currentNode.x][i] > 0 && !visited[i]) {
                    int weight = graph[currentNode.x][i];
                    int newDistance = distance[currentNode.x] + weight;
                    if (newDistance < distance[i]) {
                        distance[i] = newDistance;
                    }
                    nextToVisit.add(Node.of(i, distance[i]));
                }
            }
        }

        return distance;
    }

    public static int execute(int[][] graph, int start, int dest) {

        boolean[] visited = new boolean[graph.length];
        int[] distance = new int[graph.length];
        Queue<Node> nextToVisit = new PriorityQueue<>();

        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;

        nextToVisit.offer(Node.of(start, distance[start]));

        while (!nextToVisit.isEmpty()) {
            if (visited[dest]) {
                break;
            }
            Node currentNode = nextToVisit.poll();
            visited[currentNode.x] = true;

            for (int i = 0; i < graph[currentNode.x].length; i++) {
                if (graph[currentNode.x][i] > 0 && !visited[i]) {
                    int weight = graph[currentNode.x][i];
                    int newDistance = distance[currentNode.x] + weight;
                    if (newDistance < distance[i]) {
                        distance[i] = newDistance;
                    }
                    nextToVisit.add(Node.of(i, distance[i]));
                }
            }
        }

        return distance[dest];
    }

    private static class Node implements Comparable<Node>{
        public final int x;
        public final int distance;

        private Node(int x, int distance) {
            this.x = x;
            this.distance = distance;
        }

        public static Node of(int x, int distance) {
            return new Node(x, distance);
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.distance, o.distance);
        }
    }

    private record NodeX(int x, int distance) implements Comparable<NodeX> {
        @Override
        public int compareTo(NodeX o) {
            return Integer.compare(distance, o.distance);
        }
    }

    public static void main(String[] args) {
        NodeX nodeX = new NodeX(1, 1);
    }
}
