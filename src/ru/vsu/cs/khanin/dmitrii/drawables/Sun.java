package ru.vsu.cs.khanin.dmitrii.drawables;

import ru.vsu.cs.khanin.dmitrii.Drawable;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class Sun implements Drawable {
    private int x, y, r;
    private double rotation;

    public Sun (int x, int y, int r) {
        this(x, y, r, 0);
    }

    public Sun(int x, int y, int r, double rotation) {
        setX(x);
        setY(y);
        setR(r);
        setRotation(rotation);
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

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public double getRotation() {
        return rotation % Math.PI;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillOval(x - r, y - r, r * 2, r * 2);

        Graphics2D g2d = (Graphics2D) g;

        for (int i = 0; i < 8; i++) {
            Rectangle2D ray = new Rectangle2D.Double(x + r + r / 2, y - 5, r, 10);

            AffineTransform transform = new AffineTransform();
            transform.rotate(Math.PI * i / 4 + rotation, x, y);

            Shape rotatedRay = transform.createTransformedShape(ray);
            g2d.fill(rotatedRay);
        }
    }
}
