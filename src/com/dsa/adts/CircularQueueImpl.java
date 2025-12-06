package com.dsa.adts;

import java.util.NoSuchElementException;

public class CircularQueueImpl implements CircularQueueADT {
    private int[] arr;
    private int front;
    private int rear;
    private int count;

    public CircularQueueImpl(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException("Capacity must be > 0");
        arr = new int[capacity];
        front = 0;
        rear = -1;
        count = 0;
    }

    @Override
    public void enqueue(int element) {
        if (isFull()) {
            throw new IllegalStateException("Circular queue is full");
        }
        rear = (rear + 1) % arr.length;
        arr[rear] = element;
        count++;
    }

    @Override
    public int dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Circular queue is empty");
        }
        int val = arr[front];
        front = (front + 1) % arr.length;
        count--;
        return val;
    }

    @Override
    public int peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Circular queue is empty");
        }
        return arr[front];
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public boolean isFull() {
        return count == arr.length;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public int[] toArrayInOrder() {
        int[] out = new int[count];
        for (int i = 0; i < count; i++) {
            out[i] = arr[(front + i) % arr.length];
        }
        return out;
    }
}
