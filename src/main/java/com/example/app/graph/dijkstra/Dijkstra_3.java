package com.example.app.graph.dijkstra;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Dijkstra_3 {

    public static void calculateShortestPath(Node source) {
        source.setDistance(0);
        Set<Node> settledNodes = new HashSet<>();
        Queue<Node> unsettledNodes = new PriorityQueue<>(Collections.singleton(source));
        while (!unsettledNodes.isEmpty()) {
            Node currentNode = unsettledNodes.poll();
            currentNode.getAdjacentNodes().entrySet().stream()
                    .filter(entry -> !settledNodes.contains(entry.getKey()))
                    .forEach(entry -> {
                        evaluateDistanceAndPath(entry.getKey(), entry.getValue(), currentNode);
                        unsettledNodes.add(entry.getKey());
                    });
            settledNodes.add(currentNode);
        }
    }

    private static void evaluateDistanceAndPath(Node adjacentNode, Integer edgeWeight, Node sourceNode) {
        Integer newDistance = sourceNode.getDistance() + edgeWeight;
        if (newDistance < adjacentNode.getDistance()) {
            adjacentNode.setDistance(newDistance);
            adjacentNode.setShortestPath(
                    Stream.concat(sourceNode.getShortestPath().stream(), Stream.of(sourceNode)).collect(Collectors.toList())
            );
        }
    }


    public static void main(String[] args) {
        {
            Node nodeA = new Node("A");
            Node nodeB = new Node("B");
            Node nodeC = new Node("C");
            Node nodeD = new Node("D");
            Node nodeE = new Node("E");
            Node nodeF = new Node("F");

            nodeA.addAdjacentNode(nodeB, 2);
            nodeA.addAdjacentNode(nodeC, 4);

            nodeB.addAdjacentNode(nodeC, 3);
            nodeB.addAdjacentNode(nodeD, 1);
            nodeB.addAdjacentNode(nodeE, 5);

            nodeC.addAdjacentNode(nodeD, 2);

            nodeD.addAdjacentNode(nodeE, 1);
            nodeD.addAdjacentNode(nodeF, 4);

            nodeE.addAdjacentNode(nodeF, 2);

            calculateShortestPath(nodeA);

            printPaths(Arrays.asList(nodeA, nodeB, nodeC, nodeD, nodeE, nodeF));
        }

        {
            Node node1 = new Node("1");
            Node node2 = new Node("2");
            Node node3 = new Node("3");
            Node node4 = new Node("4");
            Node node5 = new Node("5");
            Node node6 = new Node("6");

            node1.addAdjacentNode(node2, 7);
            node1.addAdjacentNode(node3, 9);
            node1.addAdjacentNode(node6, 14);

            node2.addAdjacentNode(node1, 7);
            node2.addAdjacentNode(node3, 10);
            node2.addAdjacentNode(node4, 15);

            node3.addAdjacentNode(node1, 9);
            node3.addAdjacentNode(node2, 10);
            node3.addAdjacentNode(node4, 11);
            node3.addAdjacentNode(node6, 2);

            node4.addAdjacentNode(node3, 11);
            node4.addAdjacentNode(node5, 6);

            node5.addAdjacentNode(node4, 6);
            node5.addAdjacentNode(node6, 9);

            node6.addAdjacentNode(node1, 14);
            node6.addAdjacentNode(node3, 2);
            node6.addAdjacentNode(node5, 9);

            calculateShortestPath(node1);

            printPaths(Arrays.asList(node1, node2, node3, node4, node5, node6));
        }
    }

    private static void printPaths(List<Node> nodes) {
        nodes.forEach(node -> {
            String path = node.getShortestPath().stream()
                    .map(Node::getName)
                    .collect(Collectors.joining(" -> "));

            System.out.println(path.isBlank()
                    ? String.format("%s : %s", node.getName(), node.getDistance())
                    : String.format("%s -> %s : %s", path, node.getName(), node.getDistance())
            );
        });
    }
}
