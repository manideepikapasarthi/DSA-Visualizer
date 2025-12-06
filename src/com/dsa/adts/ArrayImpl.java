package com.dsa.adts;

import java.util.Arrays;

public class ArrayImpl implements ArrayADT {
    private int[] arr;
    private int count;

    public ArrayImpl(int capacity) {
        arr = new int[capacity];
        count = 0;
    }

    @Override
    public void insert(int element, int index) {
        if (index < 0 || index >= arr.length) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        arr[index] = element;
        if (index >= count) {
            count = index + 1;
        }
    }

    @Override
    public int get(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return arr[index];
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(arr, count));
    }

    public int[] getArray() {
        return Arrays.copyOf(arr, count);
    }
}
