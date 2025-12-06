package com.dsa.adts;

public interface QueueADT {
    void enqueue(int element);
    int dequeue();
    int peek();
    boolean isEmpty();
    int size();
}
