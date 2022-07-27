package com.example.app;

import java.util.EmptyStackException;

public class MStack<E> {

    private MNode<E> top;

    public void push(E e) {
        MNode<E> node = new MNode<>(e);
        node.next = top;
        top = node;
    }

    public E pop() {
        if (top == null) {
            throw new EmptyStackException();
        }

        E val = top.value;
        top = top.next;
        return val;
    }

    public E peek() {
        if (top == null) {
            throw new EmptyStackException();
        }

        return top.value;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public void print() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return;
        }
        System.out.println("Printing stack from the top");
        MNode<E> currentNode = this.top;
        while (currentNode != null) {
            System.out.println(currentNode.value);

            currentNode = currentNode.next;
        }
    }

    private static class MNode<E> {
        private final E value;
        private MNode<E> next;

        public MNode(E value) {
            this.value = value;
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
        try {
            System.out.println("Pop: " + stack.pop());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        stack.print();
    }
}
