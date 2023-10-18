import ru.vsu.cs.khanin.dmitrii.DrawingPanel;
import ru.vsu.cs.khanin.dmitrii.drawables.*;
import ru.vsu.cs.khanin.dmitrii.drawables.Rectangle;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        DrawingPanel panel = new DrawingPanel(800, 800);

        Rectangle rectangle = new Rectangle(0, 0, 800, 250, Color.BLUE);
        panel.addDrawable(rectangle);

        Sun sun = new Sun(500, 150, 50);
        panel.addDrawable(sun);

        rectangle = new Rectangle(0, 250, 800, 550, Color.GREEN);
        panel.addDrawable(rectangle);

        Road road = new Road(0, 300, 1000, 200, Math.toRadians(30));
        panel.addDrawable(road);

        Tree tree = new Tree(560, 300, 40, 200);
        panel.addDrawable(tree);

        tree = new Tree(700, 150, 40, 200);
        panel.addDrawable(tree);

        tree = new Tree(320, 200, 40, 200);
        panel.addDrawable(tree);

        Car car = new Car(400, 600, 300, 100, Math.toRadians(30));
        panel.addDrawable(car);

        panel.repaint();
    }
}