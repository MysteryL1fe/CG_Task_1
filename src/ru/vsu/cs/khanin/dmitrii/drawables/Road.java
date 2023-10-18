package ru.vsu.cs.khanin.dmitrii.drawables;

import ru.vsu.cs.khanin.dmitrii.Drawable;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class Road implements Drawable {
    private int x, y, width, height;
    private double angle;

    public Road(int x, int y, int width, int height) {
        this(x, y, width, height, 0);
    }

    public Road(int x, int y, int width, int height, double angle) {
        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);
        setAngle(angle);
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

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle % Math.PI;
    }

    @Override
    public void paint(Graphics g) {
        Rectangle2D rect = new Rectangle2D.Double(x, y, width, height);

        AffineTransform transform = new AffineTransform();
        transform.rotate(angle, x, y);

        Shape rotatedRect = transform.createTransformedShape(rect);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.DARK_GRAY);
        g2d.fill(rotatedRect);
    }
}
