package com.dsa.core;

import java.awt.*;

public class ArrayVisualizer extends Visualizer {

    public enum Mode {
        ARRAY,          // horizontal boxes
        STACK,          // vertical column
        QUEUE,          // vertical column
        CIRCULAR_QUEUE  // vertical column
    }

    private int highlightA = -1;
    private int highlightB = -1;
    private String status = "";
    private Mode mode = Mode.ARRAY;

    public ArrayVisualizer() {
        super();
        setBackground(Color.WHITE);
    }

    public void highlight(int a, int b) {
        this.highlightA = a;
        this.highlightB = b;
        repaint();
    }

    public void setStatus(String s) {
        this.status = s;
        repaint();
    }

    public void clearHighlights() {
        this.highlightA = -1;
        this.highlightB = -1;
        repaint();
    }

    public void setMode(Mode mode) {
        this.mode = mode == null ? Mode.ARRAY : mode;
        repaint();
    }

    public Mode getMode() {
        return mode;
    }

    @Override
    public void resetVisualState() {
        clearHighlights();
        setStatus("");
        mode = Mode.ARRAY;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (data == null || data.length == 0) {
            g.setColor(Color.DARK_GRAY);
            g.drawString("No data. Load array first.", 20, 20);
            return;
        }

        switch (mode) {
            case STACK:
            case QUEUE:
            case CIRCULAR_QUEUE:
                drawVertical(g);
                break;
            case ARRAY:
            default:
                drawHorizontal(g);
                break;
        }

        g.setColor(Color.BLACK);
        g.drawString(status, 10, 15);
    }

    private void drawHorizontal(Graphics g) {
        int w = getWidth();
        int h = getHeight();
        int n = data.length;

        int boxWidth = Math.max(40, w / Math.max(n, 1) - 10);
        int boxHeight = 40;
        int gap = 10;
        int startX = 10;
        int y = h / 2 - boxHeight / 2;

        for (int i = 0; i < n; i++) {
            int x = startX + i * (boxWidth + gap);

            if (i == highlightA || i == highlightB) {
                g.setColor(Color.ORANGE);
            } else {
                g.setColor(Color.WHITE);
            }
            g.fillRect(x, y, boxWidth, boxHeight);

            g.setColor(Color.BLACK);
            g.drawRect(x, y, boxWidth, boxHeight);

            String text = String.valueOf(data[i]);
            int textWidth = g.getFontMetrics().stringWidth(text);
            int textX = x + (boxWidth - textWidth) / 2;
            int textY = y + boxHeight / 2 + g.getFontMetrics().getAscent() / 4;
            g.drawString(text, textX, textY);
        }
    }

    private void drawVertical(Graphics g) {
        int w = getWidth();
        int h = getHeight();
        int n = data.length;

        int boxWidth = 80;
        int boxHeight = 40;
        int gap = 10;

        int totalHeight = n * boxHeight + (n - 1) * gap;
        int startY = Math.max(40, h / 2 - totalHeight / 2);
        int x = w / 2 - boxWidth / 2;

        for (int i = 0; i < n; i++) {
            int y = startY + i * (boxHeight + gap);

            if (i == highlightA || i == highlightB) {
                g.setColor(Color.ORANGE);
            } else {
                g.setColor(Color.WHITE);
            }
            g.fillRect(x, y, boxWidth, boxHeight);

            g.setColor(Color.BLACK);
            g.drawRect(x, y, boxWidth, boxHeight);

            String text = String.valueOf(data[i]);
            int textWidth = g.getFontMetrics().stringWidth(text);
            int textX = x + (boxWidth - textWidth) / 2;
            int textY = y + boxHeight / 2 + g.getFontMetrics().getAscent() / 4;
            g.drawString(text, textX, textY);
        }

        int topY = startY;
        int bottomY = startY + (n - 1) * (boxHeight + gap);

        g.setColor(Color.BLACK);
        if (mode == Mode.STACK) {
            g.drawString("Top", x + boxWidth + 10, bottomY + boxHeight / 2);
        } else if (mode == Mode.QUEUE) {
            g.drawString("Front", x - 40, topY + boxHeight / 2);
            g.drawString("Rear", x - 35, bottomY + boxHeight / 2);
        } else if (mode == Mode.CIRCULAR_QUEUE) {
            g.drawString("Front", x - 40, topY + boxHeight / 2);
            g.drawString("Rear", x - 35, bottomY + boxHeight / 2);
        }
    }
}
