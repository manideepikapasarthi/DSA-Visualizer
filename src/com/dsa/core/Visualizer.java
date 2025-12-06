package com.dsa.core;

import javax.swing.*;

public abstract class Visualizer extends JPanel {
    protected int[] data;

    public Visualizer() {
        this.data = new int[0];
    }

    public void setData(int[] data) {
        this.data = (data == null) ? new int[0] : data.clone();
        repaint();
    }

    public int[] getData() {
        return data;
    }

    public abstract void resetVisualState();
}
