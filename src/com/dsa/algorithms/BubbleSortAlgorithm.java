package com.dsa.algorithms;

import com.dsa.core.ArrayVisualizer;

public class BubbleSortAlgorithm implements Algorithm, Runnable {
    private int[] arr;
    private volatile boolean running = false;
    private Thread worker;
    private ArrayVisualizer panel;
    private int delay = 300;

    public BubbleSortAlgorithm(int[] arr, ArrayVisualizer panel) {
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
                for (int j = 0; j < n - i - 1 && running; j++) {
                    panel.setStatus("Comparing " + j + " & " + (j + 1));
                    panel.highlight(j, j + 1);
                    Thread.sleep(delay);
                    if (arr[j] > arr[j + 1]) {
                        int t = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = t;
                        panel.setData(arr);
                        panel.setStatus("Swapped " + j + " & " + (j + 1));
                        panel.highlight(j, j + 1);
                        Thread.sleep(delay);
                    }
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
