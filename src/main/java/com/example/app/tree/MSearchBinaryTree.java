package com.example.app.tree;

import java.util.ArrayList;
import java.util.List;

public class MSearchBinaryTree<E extends Comparable<E>> {

    private MNode<E> root;

    public void add(E value) {
        if (root == null) {
            root = new MNode<>();
            root.value = value;
            return;
        }
        addNode(value, root);
    }

    private void addNode(E value, MNode<E> currentNode) {
        if (value.compareTo(currentNode.value) < 0) {
            if (currentNode.left == null) {
                currentNode.left = new MNode<>();
                currentNode.left.value = value;
            } else {
                addNode(value, currentNode.left);
            }
        }

        if (value.compareTo(currentNode.value) > 0) {
            if (currentNode.right == null) {
                currentNode.right = new MNode<>();
                currentNode.right.value = value;
            } else {
                addNode(value, currentNode.right);
            }
        }
    }

    public int calculateHeight() {
        System.out.println("Calculating the height of the tree:");
        return calculateHeight(root);
    }

    private int calculateHeight(MNode<E> node) {

        if (node == null) {
            return -1;
        }

        int left = calculateHeight(node.left);
        int right = calculateHeight(node.right);

        return Math.max(left, right) + 1;
    }

    public void traverseInOrder() {
        if (root != null) {
            System.out.println("In-order traverse:");

            traverseInOrder(root);
        } else {
            System.out.println("Tree is empty");
        }

    }

    private void traverseInOrder(MNode<E> node) {
        if (node.left != null) {
            traverseInOrder(node.left);
        }
        System.out.println(node.value);

        if (node.right != null) {
            traverseInOrder(node.right);
        }
    }

    public void traversePreOder() {
        if (root != null) {
            System.out.println("Pre-order traverse:");

            traversePreOder(root);
        } else {
            System.out.println("Tree is empty");
        }
    }

    private void traversePreOder(MNode<E> node) {

        System.out.println(node.value);

        if (node.left != null) {
            traversePreOder(node.left);
        }
        if (node.right != null) {
            traversePreOder(node.right);
        }
    }

    public void traversePostOrder() {
        if (root != null) {
            System.out.println("Post-order traverse:");

            traversePostOrder(root);
        } else {
            System.out.println("Tree is empty");
        }
    }

    private void traversePostOrder(MNode<E> node) {
        if (node.left != null) {
            traversePostOrder(node.left);
        }
        if (node.right != null) {
            traversePostOrder(node.right);
        }

        System.out.println(node.value);
    }

    private static class MNode<E> {

        private E value;
        private MNode<E> left;
        private MNode<E> right;

    }

    public List<E> findAncestors(E value) {
        if (root == null) {
            return List.of();
        }

        final ArrayList<E> ancestors = new ArrayList<>();
        final boolean result = findAncestors(value, root, ancestors);
        if (result) {
            return ancestors;
        } else {
            return List.of();
        }
    }

    private boolean findAncestors(E value, MNode<E> currentNode, List<E> ancestors) {
        if (currentNode == null) {
            return false;
        }

        if (value.compareTo(currentNode.value) == 0) {
            return true;
        } else if (value.compareTo(currentNode.value) < 0) {
            ancestors.add(currentNode.value);
            return findAncestors(value, currentNode.left, ancestors);

        } else {
            ancestors.add(currentNode.value);
            return findAncestors(value, currentNode.right, ancestors);

        }
    }

    public List<E> findAncestors2(E value) {
        if (root == null) {
            return List.of();
        }

        final ArrayList<E> ancestors = new ArrayList<>();

        final boolean ancestors2 = findAncestors2(value, root, ancestors);
        if (ancestors2)
            return ancestors;
        else {
            return List.of();
        }
    }

    private boolean findAncestors2(E value, MNode<E> currentNode, List<E> ancestors) {

        if (currentNode == null) {
            return false;
        }

        if (value.compareTo(currentNode.value) == 0) {
            return true;
        } else if (value.compareTo(currentNode.value) < 0) {
            final boolean result = findAncestors(value, currentNode.left, ancestors);
            if (result) {
                ancestors.add(currentNode.value);
            }

            return result;

        } else {
            final boolean result = findAncestors(value, currentNode.right, ancestors);
            if (result) {
                ancestors.add(currentNode.value);
            }

            return result;
        }

    }

    public boolean find(E value) {
        return find(value, root);
    }

    private boolean find(E value, MNode<E> currentNode) {

        if (currentNode == null) {
            return false;
        }

        if (value.compareTo(currentNode.value) == 0) {
            return true;
        }

        if (value.compareTo(currentNode.value) < 0) {
            return find(value, currentNode.left);
        }

        if (value.compareTo(currentNode.value) > 0) {
            return find(value, currentNode.right);
        }

        return false;
    }

    public E findLowestCommonAncestor(E first, E second) {
        if (root == null) {
            return null;
        }

        E min;
        E max;
        if (first.compareTo(second) > 0) {
            max = first;
            min = second;
        } else {
            max = second;
            min = first;
        }

        return findLowestCommonAncestor(min, max, root);
    }

    private E findLowestCommonAncestor(E min, E max, MNode<E> currentNode) {

        if (currentNode.value.compareTo(min) >= 0 && currentNode.value.compareTo(max) <= 0) {
            return currentNode.value;
        }

        if (currentNode.value.compareTo(min) < 0) {
            return findLowestCommonAncestor(min, max, currentNode.right);
        }

        if (currentNode.value.compareTo(max) > 0) {
            return findLowestCommonAncestor(min, max, currentNode.left);
        }

        return null;
    }

    public static void main(String[] args) {
        MSearchBinaryTree<Integer> tree = new MSearchBinaryTree<>();

        tree.add(7);
        tree.add(5);
        tree.add(10);
        tree.add(8);
        tree.add(9);
        tree.add(15);


        tree.traverseInOrder();
        tree.traversePreOder();
        tree.traversePostOrder();

        System.out.println(tree.calculateHeight());
        System.out.println(tree.find(7));
        System.out.println(tree.find(5));
        System.out.println(tree.find(10));
        System.out.println(tree.find(8));
        System.out.println(tree.find(9));
        System.out.println(tree.find(11));
        System.out.println(tree.findAncestors(9));
        System.out.println(tree.findAncestors(7));
        System.out.println(tree.findAncestors(10));
        System.out.println(tree.findAncestors(5));
        System.out.println(tree.findAncestors(0));

        System.out.println(tree.findAncestors2(9));
        System.out.println(tree.findAncestors2(7));
        System.out.println(tree.findAncestors2(10));
        System.out.println(tree.findAncestors2(5));
        System.out.println(tree.findAncestors2(0));

        System.out.println("----");
        System.out.println(tree.findLowestCommonAncestor(5, 9));
    }
}
