package com.dsa.adts;

import java.util.ArrayList;
import java.util.EmptyStackException;

public class StackImpl implements StackADT {
    private ArrayList<Integer> stack = new ArrayList<>();

    @Override
    public void push(int element) {
        stack.add(element);
    }

    @Override
    public int pop() {
        if (stack.isEmpty()) {
            throw new EmptyStackException();
        }
        return stack.remove(stack.size() - 1);
    }

    @Override
    public int peek() {
        if (stack.isEmpty()) {
            throw new EmptyStackException();
        }
        return stack.get(stack.size() - 1);
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public int size() {
        return stack.size();
    }

    public int[] getArray() {
        return stack.stream().mapToInt(i -> i).toArray();
    }

    @Override
    public String toString() {
        return stack.toString();
    }
}
