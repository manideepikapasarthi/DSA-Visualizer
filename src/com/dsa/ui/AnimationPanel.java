package com.dsa.ui;

import com.dsa.core.ArrayVisualizer;

import javax.swing.*;
import java.awt.*;

public class AnimationPanel extends JPanel {
    private ArrayVisualizer visualizer = new ArrayVisualizer();

    public AnimationPanel() {
        setLayout(new BorderLayout());
        add(visualizer, BorderLayout.CENTER);
    }

    public ArrayVisualizer getVisualizer() {
        return visualizer;
    }
}
