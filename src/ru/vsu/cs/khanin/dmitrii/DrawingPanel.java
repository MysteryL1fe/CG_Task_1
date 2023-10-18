package ru.vsu.cs.khanin.dmitrii;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DrawingPanel extends JFrame {
    private final ArrayList<Drawable> drawableList = new ArrayList<>();
    private int width, height;

    public DrawingPanel(int width, int height) {
        setTitle("Drawing panel");
        setWidth(width);
        setHeight(height);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    @Override
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
        setSize(width, height);
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
