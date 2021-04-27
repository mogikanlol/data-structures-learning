package com.example.app;

public class MStack<E> {

    private MNode<E> head;

    public void push(E e) {
        MNode<E> node = new MNode<>();
        node.value = e;
        node.next = null;

        if (head == null) {
            head = node;
        } else {
            MNode<E> oldHead = this.head;
            this.head = node;
            node.next = oldHead;
        }
    }

    public E pop() {
        MNode<E> oldHead = this.head;
        if (oldHead != null) {
            this.head = oldHead.next;
            return oldHead.value;
        } else {
            return null;
        }
    }

    private static class MNode<E> {
        private E value;
        private MNode<E> next;
    }

    public void print() {
        System.out.println("Printing stack from the top");
        MNode<E> currentNode = this.head;
        while (currentNode != null) {
            System.out.println(currentNode.value);

            currentNode = currentNode.next;
        }
    }

    public static void main(String[] args) {
        MStack<String> stack = new MStack<>();

        stack.push("H");
        stack.push("E");
        stack.push("L");
        stack.push("L");
        stack.push("O");

        stack.print();

        System.out.println("Pop: " + stack.pop());
        System.out.println("Pop: " + stack.pop());
        System.out.println("Pop: " + stack.pop());
        System.out.println("Pop: " + stack.pop());
        System.out.println("Pop: " + stack.pop());
        System.out.println("Pop: " + stack.pop());

        stack.print();
    }
}
