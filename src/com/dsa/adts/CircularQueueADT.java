package com.dsa.adts;

public interface CircularQueueADT {
    void enqueue(int element);
    int dequeue();
    int peek();
    boolean isEmpty();
    boolean isFull();
    int size();
    int[] toArrayInOrder(); // for visualization
}
