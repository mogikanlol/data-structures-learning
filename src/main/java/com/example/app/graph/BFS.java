package com.example.app.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS {

    public static List<Integer> bfs(int start, List<List<Integer>> graph) {
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



    public static List<Integer> bfs(int start, int[][] graph) {
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

}
