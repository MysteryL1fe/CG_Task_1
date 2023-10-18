package ru.vsu.cs.khanin.dmitrii;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DrawingPanel extends JFrame {
    private final ArrayList<Drawable> drawableList = new ArrayList<>();
    private final int WIDTH = 800;
    private final int HEIGHT = 800;
    private final int RAY_SPEED = 10;
    private final double ROAD_ANGLE = Math.toRadians(30);
    private final int CAR_WIDTH = 300;
    private final int CAR_HEIGHT = 100;
    private final int CAR_SPEED = 70;

    public DrawingPanel() {
        setTitle("Drawing panel");
        setSize(WIDTH, HEIGHT);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        /*background = new Background(
                WIDTH, HEIGHT, ROAD_ANGLE, RAY_SPEED
        );

        car = new Car(
                (int) ((WIDTH - CAR_SPEED * ticksFromStart % WIDTH) * Math.cos(ROAD_ANGLE)),
                (int) (HEIGHT / 2 + (WIDTH - CAR_SPEED * ticksFromStart % WIDTH) * Math.sin(ROAD_ANGLE)),
                CAR_WIDTH, CAR_HEIGHT, ROAD_ANGLE, WIDTH, HEIGHT
        );

        foreground = new Foreground(
                WIDTH, HEIGHT, ROAD_ANGLE
        );*/
    }

    @Override
    public void paint(Graphics g) {
        for (Drawable drawable : drawableList) {
            drawable.paint(g);
        }
    }

    public void addDrawable(Drawable drawable) {
        if (drawable == null) return;
        drawableList.add(drawable);
    }

    public void removeDrawable(Drawable drawable) {
        drawableList.remove(drawable);
    }

    public void removeDrawable(int index) {
        drawableList.remove(index);
    }

    public ArrayList<Drawable> getDrawableList() {
        return (ArrayList<Drawable>) drawableList.clone();
    }
}
