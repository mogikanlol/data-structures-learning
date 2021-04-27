package com.example.app;

public class MQueue<E> {

    private MNode<E> head;
    private MNode<E> tail;

    public void enqueue(E e) {
        MNode<E> node = new MNode<>();
        node.value = e;

        if (head == null) {
            head = node;
            tail = node;
            head.next = tail;
        } else {
            MNode<E> oldTail = this.tail;
            oldTail.next = node;
            node.prev = oldTail;

            tail = node;
        }
    }

    public E dequeue() {
        MNode<E> oldTail = this.tail;

        if (oldTail != null) {

            this.tail = oldTail.prev;

            if (this.tail == null) {
                this.head = null;
                this.tail = null;
            } else {
                this.tail.next = null;
            }

            return oldTail.value;
        } else {
            return null;
        }
    }

    public void print() {
        System.out.println("Printing queue from the start");
        MNode<E> currentNode = this.head;
        while (currentNode != null) {
            System.out.println(currentNode.value);

            currentNode = currentNode.next;
        }
    }

    private static class MNode<E> {
        private E value;

        private MNode<E> next;
        private MNode<E> prev;
    }

    public static void main(String[] args) {
        MQueue<String> queue = new MQueue<>();

        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        queue.enqueue("D");

        queue.print();

        System.out.println("Dequeue = " + queue.dequeue());
        queue.print();
        System.out.println("Dequeue = " + queue.dequeue());
        queue.print();
        System.out.println("Dequeue = " + queue.dequeue());
        queue.print();
        System.out.println("Dequeue = " + queue.dequeue());
        queue.print();
        System.out.println("Dequeue = " + queue.dequeue());
        queue.print();
    }
}
