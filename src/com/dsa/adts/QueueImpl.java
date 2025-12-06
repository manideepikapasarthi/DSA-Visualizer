package com.dsa.adts;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class QueueImpl implements QueueADT {
    private LinkedList<Integer> queue = new LinkedList<>();

    @Override
    public void enqueue(int element) {
        queue.addLast(element);
    }

    @Override
    public int dequeue() {
        if (queue.isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return queue.removeFirst();
    }

    @Override
    public int peek() {
        if (queue.isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return queue.getFirst();
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public int size() {
        return queue.size();
    }

    public int[] getArray() {
        return queue.stream().mapToInt(i -> i).toArray();
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
