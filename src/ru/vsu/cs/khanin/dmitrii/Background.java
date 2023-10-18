package ru.vsu.cs.khanin.dmitrii;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class Background {
    private int width;
    private int height;
    private double roadAngle;
    private int raySpeed;

    public Background(int width, int height, double roadAngle, int raySpeed) {
        setWidth(width);
        setHeight(height);
        setRoadAngle(roadAngle);
        setRaySpeed(raySpeed);
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

    public int getRaySpeed() {
        return raySpeed;
    }

    public void setRaySpeed(int raySpeed) {
        this.raySpeed = raySpeed;
    }

    public void paint(int ticksFromStart, Graphics2D g2d) {
        paintGrass(g2d);
        paintSky(ticksFromStart, g2d);
        paintRoad(g2d);
    }

    private void paintGrass(Graphics2D g2d) {
        g2d.setColor(Color.GREEN);
        g2d.fillRect(0, height / 3, width, height);
    }

    private void paintSky(int ticksFromStart, Graphics2D g2d) {
        g2d.setColor(new Color(0, 255, 255));
        g2d.fillRect(0, 0, width, height / 3);

        int sunCenterX = width * 7 / 10;
        int sunCenterY = height / 8;

        g2d.setColor(Color.YELLOW);
        g2d.fillOval(sunCenterX - width / 20, sunCenterY - height / 20, width / 10, height / 10);

        for (int i = 0; i < 8; i++) {
            Rectangle2D ray = new Rectangle2D.Double(
                    sunCenterX + width / 15., sunCenterY,
                    width / 10., height / 100.
            );

            AffineTransform transform = new AffineTransform();
            transform.rotate(
                    Math.PI * i / 4 + Math.toRadians(ticksFromStart * raySpeed),
                    sunCenterX, sunCenterY
            );

            Shape rotatedRay = transform.createTransformedShape(ray);
            g2d.fill(rotatedRay);
        }
    }

    private void paintRoad(Graphics2D g2d) {
        Rectangle2D rect = new Rectangle2D.Double(0, height / 2.5, width * 2, height / 4.);

        AffineTransform transform = new AffineTransform();
        transform.rotate(roadAngle, 0, height / 2.5);

        Shape rotatedRect = transform.createTransformedShape(rect);

        g2d.setColor(Color.DARK_GRAY);
        g2d.fill(rotatedRect);
    }
}
