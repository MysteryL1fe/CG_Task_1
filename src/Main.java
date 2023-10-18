import ru.vsu.cs.khanin.dmitrii.DrawingPanel;
import ru.vsu.cs.khanin.dmitrii.drawables.Car;
import ru.vsu.cs.khanin.dmitrii.drawables.Rectangle;
import ru.vsu.cs.khanin.dmitrii.drawables.Tree;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        DrawingPanel panel = new DrawingPanel();

        Rectangle rectangle = new Rectangle(0, 0, 800, 250, Color.BLUE);
        panel.addDrawable(rectangle);

        rectangle = new Rectangle(0, 250, 800, 550, Color.GREEN);
        panel.addDrawable(rectangle);

        Car car = new Car(400, 400, 300, 100, Math.toRadians(30));
        panel.addDrawable(car);

        Tree tree = new Tree(560, 250, 40, 200);
        panel.addDrawable(tree);

        tree = new Tree(320, 200, 40, 200);
        panel.addDrawable(tree);

        panel.repaint();
    }
}