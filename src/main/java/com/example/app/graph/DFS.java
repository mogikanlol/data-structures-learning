package com.example.app.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DFS {

    public static List<Integer> dfs_stack(int start, List<List<Integer>> graph) {
        boolean[] visited = new boolean[graph.size()];

        List<Integer> result = new ArrayList<>();

        Stack<Integer> stack = new Stack<>();
        stack.push(start);

        while (!stack.isEmpty()) {

            Integer current = stack.pop();
            System.out.println(current);
            visited[current] = true;

            List<Integer> adjacentNodes = graph.get(current);
            for (Integer node : adjacentNodes) {
                if (visited[node]) {
                    continue;
                }
                stack.push(node);
            }
        }

        return result;
    }

    public static List<Integer> dfs(int start, List<List<Integer>> graph) {
        boolean[] visited = new boolean[graph.size()];

        List<Integer> result = new ArrayList<>();

        dfsHelp(start, graph, visited, result);

        return result;
    }

    private static void dfsHelp(int start, List<List<Integer>> graph, boolean[] visited, List<Integer> result) {
        if (visited[start]) {
            return;
        }

        visited[start] = true;
        result.add(start);

        for (Integer node : graph.get(start)) {
            dfsHelp(node, graph, visited, result);
        }
    }



    public static List<Integer> dfs(int start, int[][] graph) {
        boolean[] visited = new boolean[graph.length];

        List<Integer> result = new ArrayList<>();

        dfsHelp(start, graph, visited, result);

        return result;
    }

    private static void dfsHelp(int node, int[][] graph, boolean[] visited, List<Integer> result) {
        if (visited[node]) {
            return;
        }

        visited[node] = true;
        result.add(node);
        System.out.println(node);

        for (int i = 0; i < graph.length; i++) {
            if (graph[node][i] == 1 && !visited[i]) {
                dfsHelp(i, graph, visited, result);
            }
        }

    }
}
