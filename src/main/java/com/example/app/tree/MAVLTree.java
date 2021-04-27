package com.example.app.tree;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class MAVLTree {

    private MNode root;

    public void add(int value) {
        root = add(value, root);
    }

    private MNode add(int value, MNode currentNode) {
        if (currentNode == null) {
            MNode node = new MNode();
            node.value = value;
            updateHeight(node);

            return node;
        }

        if (value < currentNode.value) {
            currentNode.left = add(value, currentNode.left);
        }

        if (value > currentNode.value) {
            currentNode.right = add(value, currentNode.right);
        }

        return rebalance(currentNode);
    }

    private MNode rebalance(MNode node) {
        updateHeight(node);

        // calculate balance
        int balance = height(node.left) - height(node.right);
        if (balance > 1) {
            // right rotation: node.left != null && node.left.left != null
            if (height(node.left.left) > height(node.left.right)) {
                // right rotation
                node = rotateRight(node);
            } else {
                // left-right rotation
                node.left = rotateLeft(node.left);
                node = rotateRight(node);
            }
        }

        if (balance < -1) {
            // left rotation: node.right != null && node.right.right != null
            if (height(node.right.right) > height(node.right.left)) {
                // left rotation
                node = rotateLeft(node);
            } else {
                // right-left rotation
                node.right = rotateRight(node.right);
                node = rotateLeft(node);
            }

        }

        return node;
    }

    private MNode rotateLeft(MNode node) {

        //         10
        //        / \
        //      6    15
        //          /  \
        //         13  20
        //             \
        //             24

        //  after left rotation of 10
        //  it becomes
        //
        //         15
        //        / \
        //     10    20
        //    /  \    \
        //   6   13   24

        // the right node becomes root, so we return it at the end of the method
        MNode right = node.right;

        // right.left is less than right and more than current node so it becomes node.right
        node.right = right.left;

        // afterwards update right.left
        // the current node becomes left child
        right.left = node;

        updateHeight(node);
        updateHeight(right);

        return right;
    }

    private MNode rotateRight(MNode node) {

        //         10
        //        / \
        //       6   15
        //      /\
        //     4  8
        //    /
        //   2

        //  after right rotation of 10
        //  it becomes
        //
        //          6
        //        /  \
        //       4   10
        //      /   /  \
        //     2   8   15

        // the left node becomes root, so we return it at the end of the method
        MNode left = node.left;

        // left.right is more than left and less than node so it becomes node.left
        node.left = left.right;

        // afterwards update left.right
        // the current node becomes right child
        left.right = node;


        updateHeight(node);
        updateHeight(left);

        return left;
    }

    private int height(MNode node) {
        if (node == null) {
            return -1;
        } else {
            return node.height;
        }
    }

    private void updateHeight(MNode node) {
        if (node != null) {
            node.height = Math.max(height(node.left), height(node.right)) + 1;
        }
    }

    public void traverseInOrder() {
        if (root != null) {
            System.out.println("In-order traverse:");

            traverseInOrder(root);
        } else {
            System.out.println("Tree is empty");
        }

    }

    private void traverseInOrder(MNode node) {
        if (node.left != null) {
            traverseInOrder(node.left);
        }
        System.out.println(node.value);

        if (node.right != null) {
            traverseInOrder(node.right);
        }
    }

    public void deleteViaSuccessor(int value) {
        root = deleteViaSuccessor(value, root);
    }

    private MNode deleteViaSuccessor(int value, MNode currentNode) {

        if (currentNode == null) {
            return currentNode;
        }

        if (value == currentNode.value) {
            // find most left of the right subtree

            if (currentNode.right == null || currentNode.left == null) {
                if (currentNode.right == null) {
                    currentNode = currentNode.left;
                } else {
                    currentNode = currentNode.right;
                }

                return currentNode;
            } else {

                MNode mostLeft = mostLeft(currentNode.right);

                mostLeft.right = deleteViaSuccessor(mostLeft.value, currentNode.right);
                mostLeft.left = currentNode.left;

                return mostLeft;
            }
        }

        if (value < currentNode.value) {
            currentNode.left = deleteViaSuccessor(value, currentNode.left);
        }

        if (value > currentNode.value) {
            currentNode.right = deleteViaSuccessor(value, currentNode.right);
        }

        return rebalance(currentNode);
    }

    private MNode mostLeft(MNode node) {
        while (node.left != null) {
            node = node.left;
        }

        return node;
    }

    private static class MNode {

        private MNode left;
        private MNode right;
        private int value;
        private int height = 0;
    }

    public static void main2(String[] args) {

        MAVLTree checkDeletion = new MAVLTree();
        checkDeletion.add(10);
        checkDeletion.add(6);
        checkDeletion.add(15);
        checkDeletion.add(4);
        checkDeletion.add(12);
        checkDeletion.add(18);
        checkDeletion.add(16);
        checkDeletion.add(20);

        checkDeletion.deleteViaSuccessor(15);
        System.out.println(checkDeletion);

        check_right_rotation();
        check_left_rotation();

        check_left_right_rotation();
        check_right_left_rotation();

        MAVLTree tree = new MAVLTree();

        for (int i = 9; i > 0; i--) {
            tree.add(i);
            tree.traverseInOrder();
        }

//        tree.add(7);
//        tree.add(5);
//        tree.add(3);

        tree.traverseInOrder();
        System.out.println(tree);

        MAVLTree tree1 = new MAVLTree();

        for (int i = 1; i < 10; i++) {
            tree1.add(i);
        }

        System.out.println(tree1);

    }

    private static void check_right_rotation() {
        MAVLTree tree = new MAVLTree();

        tree.add(7);
        tree.add(5);
        tree.add(3);

        System.out.println(tree);
    }

    private static void check_left_rotation() {
        MAVLTree tree = new MAVLTree();

        tree.add(7);
        tree.add(10);
        tree.add(15);

        System.out.println(tree);
    }

    private static void check_left_right_rotation() {
        MAVLTree tree = new MAVLTree();

        tree.add(7);
        tree.add(3);
        tree.add(5);

        System.out.println(tree);
    }

    private static void check_right_left_rotation() {
        MAVLTree tree = new MAVLTree();

        tree.add(7);
        tree.add(15);
        tree.add(10);

        System.out.println(tree);
    }


    public int calculateHeight() {
        System.out.println("Calculating the height of the tree:");
        return calculateHeight(root);
    }

    private int calculateHeight(MNode node) {

        if (node == null) {
            return -1;
        }

        int left = calculateHeight(node.left);
        int right = calculateHeight(node.right);

        return Math.max(left, right) + 1;
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }


    private static void createAndShowGUI() {
        System.out.println("Created GUI on EDI? " + SwingUtilities.isEventDispatchThread());
        JFrame f = new JFrame("Swing Paint Demo");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        final MAVLTree tree = new MAVLTree();

        JPanel buttonPanel = new JPanel();
        MyPanel drawPanel = new MyPanel(tree);
        JPanel container = new JPanel();

        container.setLayout(new BorderLayout());

        JTextField removeField = new JTextField(5);
        JTextField addField = new JTextField(5);

        JButton removeButton = new JButton("Remove");
        removeButton.addActionListener(e -> {
            final int value = Integer.parseInt(removeField.getText());
            tree.deleteViaSuccessor(value);
            removeField.setText("");

            drawPanel.repaint();
        });

        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> {
            final int value = Integer.parseInt(addField.getText());
            tree.add(value);
            addField.setText("");

            addField.requestFocus();

            drawPanel.repaint();
        });

        buttonPanel.add(removeField);
        buttonPanel.add(removeButton);

        buttonPanel.add(addField);
        buttonPanel.add(addButton);

        f.add(container);
        container.add(drawPanel, BorderLayout.PAGE_START);
        container.add(buttonPanel, BorderLayout.PAGE_END);

        f.pack();
        f.setVisible(true);
    }


    private static class MyPanel extends JPanel {

        private final MAVLTree tree;


        public MyPanel(MAVLTree tree) {

            this.tree = tree;

            for (int i = 9; i > 0; i--) {
                tree.add(i);
            }

            setBorder(BorderFactory.createLineBorder(Color.BLACK));

        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(1280, 720);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);


            if (tree.root != null) {
                int height = tree.calculateHeight();
                paintNodes(1280 / 2, 10, tree.root, (Graphics2D)g, height + 2);
            }

        }

        private void paintNodes(int x, int y, MNode node, Graphics2D g, int scale) {
            final String s = "" + node.value;
            int stringWidth = g.getFontMetrics().stringWidth(s);
            int stringHeight = g.getFontMetrics().getHeight();

            int offset = 20;

            g.setStroke(new BasicStroke(3));
            g.drawOval(x, y, 30, 30);
            g.drawString(s, x + stringWidth, y + stringHeight);

            scale--;

            if (node.left != null) {
                paintNodes(x - (offset * scale), y + (offset * scale), node.left, g, scale);
            }

            if (node.right != null) {
                paintNodes(x + (offset * scale), y + (offset * scale), node.right, g, scale);
            }
        }

    }


}
