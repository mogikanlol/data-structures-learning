package com.example.app;

public class MLinkedList<E extends Comparable<E>> {

    private MNode<E> head;

    private MNode<E> tail;

    private int size;

    public void add(E e) {

        MNode<E> node = new MNode<>();
        node.value = e;

        if (tail == null && head == null) {
            head = node;
            tail = node;

            size++;
        } else {

            MNode<E> oldTail = this.tail;
            oldTail.next = node;
            node.prev = oldTail;

            tail = node;

            size++;
        }
    }

    public void remove(E e) {
        MNode<E> currentNode = this.head;

        // O(1)
        if (head != null && head.value.compareTo(e) == 0) {
            final MNode<E> oldHead = this.head;

            if (oldHead.next != null) {
                this.head = oldHead.next;
                this.head.prev = null;
            } else {
                this.head = null;
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
        MNode<E> currentNode = this.head;

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
