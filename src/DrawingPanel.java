import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.Random;

public class DrawingPanel extends JFrame implements ActionListener {
    private final int WIDTH = 800;
    private final int HEIGHT = 800;
    private final int RAY_SPEED = 10;
    private final double ROAD_ANGLE = Math.toRadians(16);
    private final int CAR_WIDTH = 300;
    private final int CAR_HEIGHT = 100;
    private final int CAR_SPEED = 40;
    private final int TIMER_DELAY = 1000;
    private final Timer timer = new Timer(TIMER_DELAY, this);
    private int ticksFromStart = 0;

    public DrawingPanel() {
        setTitle("Drawing panel");
        setSize(WIDTH, HEIGHT);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        paintGrass(g2d);
        paintSky(g2d);
        paintRoad(g2d);

        new Car(
                (int) ((WIDTH - CAR_SPEED * ticksFromStart % WIDTH) * Math.cos(ROAD_ANGLE)),
                (int) (HEIGHT / 2 + (WIDTH - CAR_SPEED * ticksFromStart % WIDTH) * Math.sin(ROAD_ANGLE)),
                CAR_WIDTH, CAR_HEIGHT, ROAD_ANGLE, WIDTH, HEIGHT, g2d
        );

        paintTree(g2d);
    }

    private void paintGrass(Graphics2D g2d) {
        g2d.setColor(Color.GREEN);
        g2d.fillRect(0, HEIGHT / 3, WIDTH, HEIGHT);
    }

    private void paintSky(Graphics2D g2d) {
        g2d.setColor(new Color(0, 255, 255));
        g2d.fillRect(0, 0, WIDTH, HEIGHT / 3);

        int sunCenterX = WIDTH * 7 / 10;
        int sunCenterY = HEIGHT / 8;

        g2d.setColor(Color.YELLOW);
        g2d.fillOval(sunCenterX - WIDTH / 20, sunCenterY - HEIGHT / 20, WIDTH / 10, HEIGHT / 10);

        for (int i = 0; i < 8; i++) {
            Rectangle2D ray = new Rectangle2D.Double(
                    sunCenterX + WIDTH / 15., sunCenterY,
                    WIDTH / 10., HEIGHT / 100.
            );

            AffineTransform transform = new AffineTransform();
            transform.rotate(
                    Math.PI * i / 4 + Math.toRadians(ticksFromStart * RAY_SPEED),
                    sunCenterX, sunCenterY
            );

            Shape rotatedRay = transform.createTransformedShape(ray);
            g2d.fill(rotatedRay);
        }
    }

    private void paintRoad(Graphics2D g2d) {
        Rectangle2D rect = new Rectangle2D.Double(0, HEIGHT / 2.5, WIDTH * 2, HEIGHT / 4.);

        AffineTransform transform = new AffineTransform();
        transform.rotate(ROAD_ANGLE, 0, HEIGHT / 2.5);

        Shape rotatedRect = transform.createTransformedShape(rect);

        g2d.setColor(Color.DARK_GRAY);
        g2d.fill(rotatedRect);
    }

    private void paintTree(Graphics2D g2d) {
        int treeCenterX = -1;
        int treeCenterY = -1;
        if (ROAD_ANGLE > Math.toRadians(15) && ROAD_ANGLE < Math.toRadians(45)) {
            treeCenterX = (int) (WIDTH * 0.8);
            treeCenterY = HEIGHT / 3;
        } else if (ROAD_ANGLE < Math.toRadians(10)) {
            treeCenterX = (int) (WIDTH * 0.2);
            treeCenterY = (int) (HEIGHT * 0.7);
        }

        if (treeCenterX > 0) {
            g2d.setColor(new Color(213, 96, 0));
            g2d.fillRect(
                    treeCenterX, treeCenterY, (int) (WIDTH * 0.05), (int) (HEIGHT * 0.25)
            );

            g2d.setColor(new Color(25, 86, 0));
            g2d.fillOval(
                    (int) (treeCenterX - WIDTH * 0.025),
                    (int) (treeCenterY - HEIGHT * 0.025),
                    (int) (WIDTH * 0.1), (int) (HEIGHT * 0.1)
            );

            Random random = new Random();
            for (int i = 0; i < 10; i++) {
                g2d.fillOval(
                        (int) (treeCenterX - random.nextInt() % (WIDTH * 0.05)),
                        (int) (treeCenterY - random.nextInt() % (HEIGHT * 0.05)),
                        (int) (WIDTH * 0.1), (int) (HEIGHT * 0.1)
                );
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == timer) {
            repaint();
            ++ticksFromStart;
        }
    }
}
