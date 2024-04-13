package com.example.app.tree;

import java.util.HashMap;
import java.util.Map;

public class Trie {

    private static class Node {
        Character c;
        Map<Character, Node> children = new HashMap<>();
        boolean isWord;
    }

    Node root = new Node();

    public void insert(String word) {
        Node current = root;
        for (char c: word.toCharArray()) {
            Node node = current.children.get(c);

            if (node == null) {
                node = new Node();
                node.c = c;
                current.children.put(c, node);
            }

            current = node;
        }

        current.isWord = true;
    }

    public boolean search(String word) {
        Node current = root;
        for (char c : word.toCharArray()) {
            Node node = current.children.get(c);
            if (node == null) {
                return false;
            }
            current = node;
        }

        return current.isWord;
    }

    public boolean startWith(String prefix) {
        Node current = root;
        for (char c : prefix.toCharArray()) {
            Node node = current.children.get(c);
            if (node == null) {
                return false;
            }
            current = node;
        }

        return true;
    }

}
