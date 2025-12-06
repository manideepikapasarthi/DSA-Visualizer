package com.dsa.ui;

import com.dsa.algorithms.*;
import com.dsa.algorithms.exceptions.InvalidSizeException;
import com.dsa.adts.*;
import com.dsa.core.ArrayVisualizer.Mode;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {

    private enum ViewMode { ARRAY, STACK, QUEUE, CIRCULAR_QUEUE }

    private ViewMode currentView = ViewMode.ARRAY;

    private JComboBox<String> viewSelector =
            new JComboBox<>(new String[] { "Array", "Stack", "Queue", "Circular Queue" });

    // Keep explicit labels so we can hide them too
    private JLabel sizeLabel = new JLabel("Array Size:");
    private JLabel elementsLabel = new JLabel("Elements:");

    private JTextField sizeField = new JTextField(3);
    private JTextField elementsField = new JTextField(30);
    private JButton loadBtn = new JButton("Load");
    private JButton bubbleBtn = new JButton("Bubble Sort");
    private JButton selectionBtn = new JButton("Selection Sort");
    private JButton pushBtn = new JButton("Push Stack");
    private JButton popBtn = new JButton("Pop Stack");
    private JButton enqueueBtn = new JButton("Enqueue Queue");
    private JButton dequeueBtn = new JButton("Dequeue Queue");
    private JButton enqueueCQBtn = new JButton("Enqueue CQueue");
    private JButton dequeueCQBtn = new JButton("Dequeue CQueue");
    private JButton stopBtn = new JButton("Stop");
    private JSlider speedSlider = new JSlider(1, 1000, 700);

    private AnimationPanel animationPanel;
    private Algorithm currentAlgorithm = null;

    private StackADT stack = new StackImpl();
    private QueueADT queue = new QueueImpl();
    private CircularQueueADT circularQueue = new CircularQueueImpl(8);

    private int computeDelay() {
        int val = speedSlider.getValue();    // 1..1000
        int minDelay = 10;
        int maxDelay = 1000;
        return maxDelay + minDelay - val;    // higher slider -> smaller delay
    }

    public ControlPanel(AnimationPanel panel) {
        this.animationPanel = panel;
        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(new JLabel("View:"));
        add(viewSelector);

        add(sizeLabel);
        add(sizeField);
        add(elementsLabel);
        add(elementsField);
        add(loadBtn);
        add(bubbleBtn);
        add(selectionBtn);

        add(new JLabel(" | Stack: "));
        add(pushBtn);
        add(popBtn);

        add(new JLabel(" | Queue: "));
        add(enqueueBtn);
        add(dequeueBtn);

        add(new JLabel(" | CQueue: "));
        add(enqueueCQBtn);
        add(dequeueCQBtn);

        add(stopBtn);
        add(new JLabel("Speed:"));
        add(speedSlider);

        loadBtn.addActionListener(e -> onLoad());
        bubbleBtn.addActionListener(e -> runBubble());
        selectionBtn.addActionListener(e -> runSelection());
        stopBtn.addActionListener(e -> stopCurrent());
        pushBtn.addActionListener(e -> pushStack());
        popBtn.addActionListener(e -> popStack());
        enqueueBtn.addActionListener(e -> enqueueQueue());
        dequeueBtn.addActionListener(e -> dequeueQueue());
        enqueueCQBtn.addActionListener(e -> enqueueCQueue());
        dequeueCQBtn.addActionListener(e -> dequeueCQueue());
        viewSelector.addActionListener(e -> switchView());

        switchView();
    }

    private void switchView() {
        String sel = (String) viewSelector.getSelectedItem();
        if ("Stack".equals(sel)) currentView = ViewMode.STACK;
        else if ("Queue".equals(sel)) currentView = ViewMode.QUEUE;
        else if ("Circular Queue".equals(sel)) currentView = ViewMode.CIRCULAR_QUEUE;
        else currentView = ViewMode.ARRAY;

        boolean arrayVisible = (currentView == ViewMode.ARRAY);
        sizeLabel.setVisible(arrayVisible);
        sizeField.setVisible(arrayVisible);
        elementsLabel.setVisible(arrayVisible);
        elementsField.setVisible(arrayVisible);
        loadBtn.setVisible(arrayVisible);
        bubbleBtn.setVisible(arrayVisible);
        selectionBtn.setVisible(arrayVisible);

        boolean stackVisible = (currentView == ViewMode.STACK);
        pushBtn.setVisible(stackVisible);
        popBtn.setVisible(stackVisible);

        boolean queueVisible = (currentView == ViewMode.QUEUE);
        enqueueBtn.setVisible(queueVisible);
        dequeueBtn.setVisible(queueVisible);

        boolean cqueueVisible = (currentView == ViewMode.CIRCULAR_QUEUE);
        enqueueCQBtn.setVisible(cqueueVisible);
        dequeueCQBtn.setVisible(cqueueVisible);

        revalidate();   // refresh FlowLayout positions after visibility changes [web:160][web:166]
        repaint();
    }

    private int[] parseInput() throws InvalidSizeException {
        String sSize = sizeField.getText().trim();
        String sElems = elementsField.getText().trim();

        if (sSize.isEmpty()) {
            throw new InvalidSizeException("Enter size");
        }
        int size = Integer.parseInt(sSize);

        if (sElems.isEmpty()) {
            throw new InvalidSizeException("Enter elements");
        }
        String[] parts = sElems.split("\\s+");
        if (parts.length != size) {
            throw new InvalidSizeException("Size mismatch");
        }

        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = Integer.parseInt(parts[i]);
        }
        return arr;
    }

    private void onLoad() {
        try {
            if (currentView != ViewMode.ARRAY) {
                JOptionPane.showMessageDialog(this, "Switch to Array view to load array.");
                return;
            }
            int[] arr = parseInput();
            animationPanel.getVisualizer().setMode(Mode.ARRAY);
            animationPanel.getVisualizer().setData(arr);
            animationPanel.getVisualizer().setStatus("Array");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    private void stopCurrent() {
        if (currentAlgorithm != null && currentAlgorithm.isRunning()) {
            currentAlgorithm.stop();
        }
        animationPanel.getVisualizer().clearHighlights();
        animationPanel.getVisualizer().setStatus("Stopped");
    }

    private void runBubble() {
        if (currentView != ViewMode.ARRAY) {
            JOptionPane.showMessageDialog(this, "Bubble Sort works only in Array view.");
            return;
        }
        stopCurrent();
        int[] arr = animationPanel.getVisualizer().getData();
        if (arr.length == 0) {
            JOptionPane.showMessageDialog(this, "Load array first");
            return;
        }
        animationPanel.getVisualizer().setMode(Mode.ARRAY);
        BubbleSortAlgorithm alg =
                new BubbleSortAlgorithm(arr, animationPanel.getVisualizer());
        alg.setDelay(computeDelay());
        currentAlgorithm = alg;
        animationPanel.getVisualizer().setStatus("Bubble Sort");
        alg.start();
    }

    private void runSelection() {
        if (currentView != ViewMode.ARRAY) {
            JOptionPane.showMessageDialog(this, "Selection Sort works only in Array view.");
            return;
        }
        stopCurrent();
        int[] arr = animationPanel.getVisualizer().getData();
        if (arr.length == 0) {
            JOptionPane.showMessageDialog(this, "Load array first");
            return;
        }
        animationPanel.getVisualizer().setMode(Mode.ARRAY);
        SelectionSortAlgorithm alg =
                new SelectionSortAlgorithm(arr, animationPanel.getVisualizer());
        alg.setDelay(computeDelay());
        currentAlgorithm = alg;
        animationPanel.getVisualizer().setStatus("Selection Sort");
        alg.start();
    }

    private void pushStack() {
        if (currentView != ViewMode.STACK) {
            JOptionPane.showMessageDialog(this, "Switch to Stack view to use stack.");
            return;
        }
        String s = JOptionPane.showInputDialog(this, "Enter value to push:");
        if (s == null) return;
        try {
            int v = Integer.parseInt(s.trim());
            stack.push(v);
            updateStackVisualizer();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void popStack() {
        if (currentView != ViewMode.STACK) {
            JOptionPane.showMessageDialog(this, "Switch to Stack view to use stack.");
            return;
        }
        try {
            int v = stack.pop();
            updateStackVisualizer();
            JOptionPane.showMessageDialog(this, "Popped: " + v);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void enqueueQueue() {
        if (currentView != ViewMode.QUEUE) {
            JOptionPane.showMessageDialog(this, "Switch to Queue view to use queue.");
            return;
        }
        String s = JOptionPane.showInputDialog(this, "Enter value to enqueue:");
        if (s == null) return;
        try {
            int v = Integer.parseInt(s.trim());
            queue.enqueue(v);
            updateQueueVisualizer();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void dequeueQueue() {
        if (currentView != ViewMode.QUEUE) {
            JOptionPane.showMessageDialog(this, "Switch to Queue view to use queue.");
            return;
        }
        try {
            int v = queue.dequeue();
            updateQueueVisualizer();
            JOptionPane.showMessageDialog(this, "Dequeued: " + v);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void enqueueCQueue() {
        if (currentView != ViewMode.CIRCULAR_QUEUE) {
            JOptionPane.showMessageDialog(this, "Switch to Circular Queue view to use it.");
            return;
        }
        String s = JOptionPane.showInputDialog(this, "Enter value to enqueue (CQueue):");
        if (s == null) return;
        try {
            int v = Integer.parseInt(s.trim());
            circularQueue.enqueue(v);
            updateCQueueVisualizer();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void dequeueCQueue() {
        if (currentView != ViewMode.CIRCULAR_QUEUE) {
            JOptionPane.showMessageDialog(this, "Switch to Circular Queue view to use it.");
            return;
        }
        try {
            int v = circularQueue.dequeue();
            updateCQueueVisualizer();
            JOptionPane.showMessageDialog(this, "Dequeued (CQueue): " + v);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void updateStackVisualizer() {
        int[] arr = stack.size() == 0 ? new int[0] : ((StackImpl) stack).getArray();
        animationPanel.getVisualizer().setMode(Mode.STACK);
        animationPanel.getVisualizer().setData(arr);
        animationPanel.getVisualizer().setStatus("Stack");
    }

    private void updateQueueVisualizer() {
        int[] arr = queue.size() == 0 ? new int[0] : ((QueueImpl) queue).getArray();
        animationPanel.getVisualizer().setMode(Mode.QUEUE);
        animationPanel.getVisualizer().setData(arr);
        animationPanel.getVisualizer().setStatus("Queue");
    }

    private void updateCQueueVisualizer() {
        int[] arr = circularQueue.size() == 0 ? new int[0] : circularQueue.toArrayInOrder();
        animationPanel.getVisualizer().setMode(Mode.CIRCULAR_QUEUE);
        animationPanel.getVisualizer().setData(arr);
        animationPanel.getVisualizer().setStatus("Circular Queue");
    }
}
