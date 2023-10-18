package ru.vsu.cs.khanin.dmitrii;

import java.awt.*;
import java.util.Random;

public class Foreground {
    private int width;
    private int height;
    private double roadAngle;

    public Foreground(int width, int height, double roadAngle) {
        setWidth(width);
        setHeight(height);
        setRoadAngle(roadAngle);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getRoadAngle() {
        return roadAngle;
    }

    public void setRoadAngle(double roadAngle) {
        this.roadAngle = roadAngle;
    }

    public void paint(Graphics2D g2d) {
        paintTree(g2d);
    }

    private void paintTree(Graphics2D g2d) {
        int[] treeCenterX = null;
        int[] treeCenterY = null;
        if (roadAngle > Math.toRadians(15) && roadAngle < Math.toRadians(45)) {
            treeCenterX = new int[]{(int) (width * 0.7), (int) (width * 0.4), (int) (width * 0.9)};
            treeCenterY = new int[]{(int) (height / 3), (int) (height / 3.5), (int) (height / 2)};
        } else if (roadAngle < Math.toRadians(10)) {
            treeCenterX = new int[]{(int) (width * 0.2), (int) (width * 0.5), (int) (width * 0.8)};
            treeCenterY = new int[]{(int) (height * 0.7), (int) (height * 0.6), (int) (height * 0.7)};
        }

        if (treeCenterX != null && treeCenterY != null) {
            for (int i = 0; i < treeCenterX.length && i < treeCenterY.length; i++) {
                g2d.setColor(new Color(213, 96, 0));
                g2d.fillRect(
                        treeCenterX[i], treeCenterY[i], (int) (width * 0.05), (int) (height * 0.25)
                );

                g2d.setColor(new Color(25, 86, 0));
                g2d.fillOval(
                        (int) (treeCenterX[i] - width * 0.025),
                        (int) (treeCenterY[i] - height * 0.025),
                        (int) (width * 0.1), (int) (height * 0.1)
                );

                Random random = new Random();
                for (int j = 0; j < 50; j++) {
                    g2d.fillOval(
                            (int) (treeCenterX[i] - random.nextInt() % (width * 0.05)),
                            (int) (treeCenterY[i] - random.nextInt() % (height * 0.05)),
                            (int) (width * 0.1), (int) (height * 0.1)
                    );
                }
            }
        }
    }
}
