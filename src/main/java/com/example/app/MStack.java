package com.example.app;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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

    @Test
    void test() {
        MStack<String> stack = new MStack<>();

        Assertions.assertTrue(stack.isEmpty());

        stack.push("H");
        Assertions.assertEquals("H", stack.peek());
        Assertions.assertFalse(stack.isEmpty());

        stack.push("E");
        Assertions.assertEquals("E", stack.peek());
        Assertions.assertFalse(stack.isEmpty());

        stack.push("L");
        Assertions.assertEquals("L", stack.peek());
        Assertions.assertFalse(stack.isEmpty());

        stack.push("L");
        Assertions.assertEquals("L", stack.peek());
        Assertions.assertFalse(stack.isEmpty());

        stack.push("O");
        Assertions.assertEquals("O", stack.peek());
        Assertions.assertFalse(stack.isEmpty());

        Assertions.assertEquals("O", stack.pop());
        Assertions.assertFalse(stack.isEmpty());
        Assertions.assertEquals("L", stack.pop());
        Assertions.assertFalse(stack.isEmpty());
        Assertions.assertEquals("L", stack.pop());
        Assertions.assertFalse(stack.isEmpty());
        Assertions.assertEquals("E", stack.pop());
        Assertions.assertFalse(stack.isEmpty());
        Assertions.assertEquals("H", stack.pop());

        Assertions.assertTrue(stack.isEmpty());

        Assertions.assertThrows(Exception.class, stack::pop);
        Assertions.assertThrows(Exception.class, stack::peek);
    }

}
