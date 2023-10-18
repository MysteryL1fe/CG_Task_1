package ru.vsu.cs.khanin.dmitrii.drawables;

import ru.vsu.cs.khanin.dmitrii.Drawable;

import java.awt.*;

public class Rectangle implements Drawable {
    private int x, y, width, height;
    private Color color;

    public Rectangle(int x, int y, int width, int height) {
        this(x, y, width, height, Color.BLACK);
    }

    public Rectangle(int x, int y, int width, int height, Color color) {
        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);
        setColor(color);
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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }
}
