package ru.vsu.cs.khanin.dmitrii.drawables;

import ru.vsu.cs.khanin.dmitrii.Drawable;

import java.awt.*;
import java.util.Random;

public class Tree implements Drawable {
    private int x, y, width, height;

    public Tree(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
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

    @Override
    public void paint(Graphics g) {
        g.setColor(new Color(213, 96, 0));
        g.fillRect(x, y, width, height);

        g.setColor(new Color(25, 86, 0));
        Random random = new Random();
        for (int j = 0; j < 50; j++) {
            g.fillOval(
                    x - random.nextInt() % width - width / 2,
                    y - random.nextInt() % width - width / 2,
                    width * 2, width * 2
            );
        }
    }
}
