package com.example.app;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MLinkedList<E extends Comparable<E>> {

    private MNode<E> first;

    private MNode<E> last;

    private int size;

    public void add(E e) {

        MNode<E> node = new MNode<>(last, e, null);

        if (first == null && last == null) {
            first = node;
            last = node;
        } else {
            last.next = node;
            last = node;
        }
        size++;
    }

    public void remove(E e) {
        MNode<E> currentNode = this.first;

        // O(1)
        if (first != null && first.value.compareTo(e) == 0) {
            final MNode<E> oldHead = this.first;

            if (oldHead.next != null) {
                this.first = oldHead.next;
                this.first.prev = null;
            } else {
                this.first = null;
            }

            size--;

            return;
        }

        // O(n)
        while (currentNode != null) {

            if (currentNode.value.compareTo(e) == 0) {

                final MNode<E> prev = currentNode.prev;
                final MNode<E> next = currentNode.next;
                prev.next = next;

                if (next != null) {
                    next.prev = prev;
                }

                size--;

                return;
            }

            currentNode = currentNode.next;
        }
    }

    public void print() {
        MNode<E> currentNode = this.first;

        if (currentNode != null) {

            while (currentNode != null) {
                System.out.print(currentNode.value + ", ");
                currentNode = currentNode.next;
            }
            System.out.println();
        } else {
            System.out.println("List is empty");
        }
    }

    public int size() {
        return size;
    }

    private static class MNode<E> {

        private E value;
        private MNode<E> next;
        private MNode<E> prev;

        public MNode(){}

        public MNode(MNode<E> prev, E value, MNode<E> next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }


    @Test
    void test () {
        MLinkedList<String> linkedList = new MLinkedList<>();

        linkedList.add("A");
        linkedList.add("B");
        linkedList.add("C");
        linkedList.add("D");

        Assertions.assertEquals(4, linkedList.size());

        linkedList.remove("A");
        Assertions.assertEquals(3, linkedList.size());

        linkedList.remove("B");
        Assertions.assertEquals(2, linkedList.size());

        linkedList.remove("C");
        Assertions.assertEquals(1, linkedList.size());

        linkedList.remove("D");
        Assertions.assertEquals(0, linkedList.size());

        System.out.println("qweqwe");
    }

    public static void main(String[] args) {
        MLinkedList<String> linkedList = new MLinkedList<>();

        linkedList.add("A");
        linkedList.add("B");
        linkedList.add("C");
        linkedList.add("D");

        linkedList.print();

        System.out.println("-----");
        System.out.println("Removing A");
        linkedList.remove("A");
        linkedList.print();
        System.out.println("Current size = " + linkedList.size());
        System.out.println("-----");

        System.out.println("Removing B");
        linkedList.remove("B");
        linkedList.print();
        System.out.println("Current size = " + linkedList.size());
        System.out.println("-----");

        System.out.println("Removing C");
        linkedList.remove("C");
        linkedList.print();
        System.out.println("Current size = " + linkedList.size());
        System.out.println("-----");

        System.out.println("Removing D");
        linkedList.remove("D");
        linkedList.print();
        System.out.println("Current size = " + linkedList.size());
        System.out.println("-----");

        System.out.println("Removing D one more time");
        linkedList.remove("D");
        linkedList.print();
        System.out.println("Current size = " + linkedList.size());
        System.out.println("-----");
    }
}
