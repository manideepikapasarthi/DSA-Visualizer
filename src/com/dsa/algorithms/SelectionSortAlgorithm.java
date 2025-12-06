package com.dsa.algorithms;

import com.dsa.core.ArrayVisualizer;

public class SelectionSortAlgorithm implements Algorithm, Runnable {
    private int[] arr;
    private volatile boolean running = false;
    private Thread worker;
    private ArrayVisualizer panel;
    private int delay = 300;

    public SelectionSortAlgorithm(int[] arr, ArrayVisualizer panel) {
        this.arr = arr;
        this.panel = panel;
    }

    public void setDelay(int ms) {
        this.delay = ms;
    }

    @Override
    public void start() {
        if (running) return;
        running = true;
        worker = new Thread(this);
        worker.start();
    }

    @Override
    public void stop() {
        running = false;
        if (worker != null) worker.interrupt();
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    @Override
    public void run() {
        try {
            int n = arr.length;
            for (int i = 0; i < n - 1 && running; i++) {
                int minIdx = i;
                for (int j = i + 1; j < n && running; j++) {
                    panel.setStatus("Comparing " + j + " & " + minIdx);
                    panel.highlight(j, minIdx);
                    Thread.sleep(delay);
                    if (arr[j] < arr[minIdx]) {
                        minIdx = j;
                        panel.highlight(i, minIdx);
                        Thread.sleep(delay);
                    }
                }
                if (minIdx != i && running) {
                    int tmp = arr[i];
                    arr[i] = arr[minIdx];
                    arr[minIdx] = tmp;
                    panel.setData(arr);
                    panel.setStatus("Swapped " + i + " & " + minIdx);
                    panel.highlight(i, minIdx);
                    Thread.sleep(delay);
                }
            }
            panel.setStatus("Sorted");
            panel.clearHighlights();
            running = false;
        } catch (InterruptedException e) {
            running = false;
        }
    }
}
