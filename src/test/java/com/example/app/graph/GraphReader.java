package com.example.app.graph;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class GraphReader {

    public static int[][] fromFile(String filename) {
        int[][] graph = null;
        try(Scanner scanner = new Scanner(new FileInputStream(filename))) {
            int v = scanner.nextInt();
            graph = new int[v][v];
            for (int i = 0; i < v; i++) {
                for (int j = 0; j < v; j++) {
                    graph[i][j] = scanner.nextInt();
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return graph;
    }

    public static int[][] matrixFromResourceFile(String filename) {
        int[][] graph = null;
        try(Scanner scanner = new Scanner(GraphReader.class.getClassLoader().getResourceAsStream(filename))) {
            int v = scanner.nextInt();
            graph = new int[v][v];
            for (int i = 0; i < v; i++) {
                for (int j = 0; j < v; j++) {
                    graph[i][j] = scanner.nextInt();
                }
            }
        }
        return graph;
    }

    public static List<List<Integer>> listFromResourceFile(String filename) {
        List<List<Integer>> graph = null;
        try(Scanner scanner = new Scanner(GraphReader.class.getClassLoader().getResourceAsStream(filename))) {
            int v = scanner.nextInt();
            graph = new ArrayList<>(v);

            scanner.nextLine(); // go to the next line

            for (int i = 0; i < v; i++) {
                graph.add(i, new ArrayList<>());

                String[] line = scanner.nextLine().split(":");
                int vertex = Integer.parseInt(line[0]);

                if (vertex != i) {
                    continue;
                }

                if (line.length == 1) {
                    continue;
                }

                Arrays.stream(line[1].trim().split(" "))
                        .map(Integer::valueOf)
                        .forEach(graph.get(i)::add);
            }
        }
        return graph;
    }


    private static int[][] readFromFile(String fileName) {
        int[][] graph = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            int v = Integer.parseInt(reader.readLine());
            graph = new int[v][v];

            String line;
            int row = 0;
            while ((line = reader.readLine()) != null) {
                String[] s = line.split(" ");
                for (int i = 0; i < v; i++) {
                    graph[row][i] = Integer.parseInt(s[i]);
                }
                row++;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return graph;
    }
}
