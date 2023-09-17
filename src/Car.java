import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Rectangle2D;

public class Car {
    private final int X, Y, CAR_WIDTH, CAR_HEIGHT, WIDTH, HEIGHT;
    private final double ANGLE;

    public Car(int x, int y, int carWidth, int carHeight, double angle, int width, int height, Graphics2D g2d) {
        X = x;
        Y = y;
        CAR_WIDTH = carWidth;
        CAR_HEIGHT = carHeight;
        ANGLE = angle;
        WIDTH = width;
        HEIGHT = height;

        paintFrame(g2d);
        paintWheels(g2d);
        paintGlasses(g2d);
        paintLights(g2d);
        paintText(g2d);
    }

    private void paintFrame(Graphics2D g2d) {
        paintSide(g2d);
        paintBonnet(g2d);
        paintBack(g2d);
        paintArc(g2d);
        paintUpperArc(g2d);
    }

    private void paintSide(Graphics2D g2d) {
        Rectangle2D side = new Rectangle2D.Double(
                X, Y, CAR_WIDTH * Math.cos(ANGLE), CAR_HEIGHT / 2. * Math.cos(ANGLE)
        );

        AffineTransform transform = new AffineTransform();
        transform.rotate(ANGLE, X, Y);

        Shape rotatedSide = transform.createTransformedShape(side);
        g2d.setColor(Color.BLUE);
        g2d.fill(rotatedSide);
    }

    private void paintBack(Graphics2D g2d) {
        double backPivotX = X + CAR_WIDTH * Math.pow(Math.cos(ANGLE), 2);
        double backPivotY = Y + CAR_WIDTH * Math.cos(ANGLE) * Math.sin(ANGLE);

        Rectangle2D back = new Rectangle2D.Double(
                backPivotX, backPivotY,
                CAR_WIDTH / 3. * Math.sin(ANGLE), CAR_HEIGHT / 2. * Math.sin(ANGLE)
        );

        AffineTransform transform = new AffineTransform();
        transform.rotate(-(Math.PI / 2 - ANGLE), backPivotX, backPivotY);

        Shape rotatedBack = transform.createTransformedShape(back);
        g2d.setColor(Color.BLUE);
        g2d.fill(rotatedBack);
    }

    private void paintArc(Graphics2D g2d) {
        double arcWidth = CAR_HEIGHT * Math.cos(ANGLE);
        double arcHeight = CAR_HEIGHT * Math.sin(ANGLE);
        double arcPivotX = X + CAR_WIDTH * Math.pow(Math.cos(ANGLE), 2) - arcWidth / 2;
        double arcPivotY = Y + CAR_WIDTH * Math.cos(ANGLE) * Math.sin(ANGLE) - arcHeight / 2;

        Arc2D arc = new Arc2D.Double(
                arcPivotX, arcPivotY,
                arcWidth, arcHeight, 180, 90, Arc2D.PIE
        );

        AffineTransform transform = new AffineTransform();
        transform.rotate(
                -(Math.PI / 2 - ANGLE), arcPivotX + arcWidth / 2, arcPivotY + arcHeight / 2
        );

        Shape rotatedArc = transform.createTransformedShape(arc);
        g2d.setColor(Color.BLUE);
        g2d.fill(rotatedArc);
    }

    private void paintBonnet(Graphics2D g2d) {
        Rectangle2D rect = new Rectangle2D.Double(
                X, Y, CAR_WIDTH / 3. * Math.sin(ANGLE), CAR_WIDTH * Math.cos(ANGLE)
        );

        AffineTransform transform = new AffineTransform();
        transform.rotate(-(Math.PI / 2 - ANGLE), X, Y);

        Shape rotatedRect = transform.createTransformedShape(rect);
        g2d.setColor(Color.BLUE);
        g2d.fill(rotatedRect);
    }

    private void paintUpperArc(Graphics2D g2d) {
        double arcWidth = CAR_WIDTH * 0.75 * Math.cos(ANGLE);
        double arcHeight = CAR_HEIGHT * Math.cos(ANGLE);
        double arcPivotX = X + CAR_WIDTH / 6. * Math.cos(ANGLE) * Math.cos(ANGLE);
        double arcPivotY = Y - arcHeight / 2 + CAR_WIDTH / 6. * Math.sin(ANGLE) * Math.cos(ANGLE);

        {
            Arc2D arc = new Arc2D.Double(
                    arcPivotX, arcPivotY,
                    arcWidth, arcHeight, 0, 180, Arc2D.PIE
            );

            AffineTransform transform = new AffineTransform();
            transform.rotate(
                    ANGLE, arcPivotX, arcPivotY + arcHeight / 2
            );

            Shape rotatedArc = transform.createTransformedShape(arc);
            g2d.setColor(Color.BLACK);
            g2d.draw(rotatedArc);
        }

        {
            arcPivotX += CAR_WIDTH / 3. * Math.sin(ANGLE) * Math.sin(ANGLE);
            arcPivotY -= CAR_WIDTH / 3. * Math.sin(ANGLE) * Math.cos(ANGLE);

            AffineTransform transform = new AffineTransform();
            transform.rotate(
                    ANGLE, arcPivotX, arcPivotY + arcHeight / 2
            );

            Arc2D arc = new Arc2D.Double(
                    arcPivotX, arcPivotY,
                    arcWidth, arcHeight, 0, 180, Arc2D.PIE
            );

            Shape rotatedArc = transform.createTransformedShape(arc);
            g2d.setColor(Color.BLUE);
            g2d.fill(rotatedArc);

            g2d.setColor(Color.BLACK);
            g2d.draw(rotatedArc);
        }

        g2d.drawLine(
                (int) (X + CAR_WIDTH / 6. * Math.cos(ANGLE) * Math.cos(ANGLE)),
                (int) (Y + CAR_WIDTH / 6. * Math.sin(ANGLE) * Math.cos(ANGLE)),
                (int) arcPivotX, (int) (arcPivotY + arcHeight / 2)
        );

        g2d.drawLine(
                (int) (X + CAR_WIDTH / 6. * Math.cos(ANGLE) * Math.cos(ANGLE) + arcWidth * Math.cos(ANGLE)),
                (int) (Y + CAR_WIDTH / 6. * Math.sin(ANGLE) * Math.cos(ANGLE) + arcWidth * Math.sin(ANGLE)),
                (int) (arcPivotX + arcWidth * Math.cos(ANGLE)),
                (int) (arcPivotY + arcHeight / 2 + arcWidth * Math.sin(ANGLE))
        );
    }

    private void paintWheels(Graphics2D g2d) {
        double wheelsRadius = CAR_HEIGHT / 2. * Math.cos(ANGLE);
        double leftWheelPivotX = X - CAR_HEIGHT / 2. * Math.cos(ANGLE) * Math.sin(ANGLE)
                + CAR_WIDTH / 5. * Math.cos(ANGLE) * Math.cos(ANGLE) - wheelsRadius / 2;
        double leftWheelPivotY = Y + CAR_HEIGHT / 2. * Math.cos(ANGLE) * Math.cos(ANGLE)
                + CAR_WIDTH / 5. * Math.cos(ANGLE) * Math.sin(ANGLE) - wheelsRadius / 2;
        double rightWheelPivotX = X - CAR_HEIGHT / 2. * Math.cos(ANGLE) * Math.sin(ANGLE)
                + CAR_WIDTH * 0.8 * Math.cos(ANGLE) * Math.cos(ANGLE) - wheelsRadius / 2;
        double rightWheelPivotY = Y + CAR_HEIGHT / 2. * Math.cos(ANGLE) * Math.cos(ANGLE)
                + CAR_WIDTH * 0.8 * Math.cos(ANGLE) * Math.sin(ANGLE) - wheelsRadius / 2;

        g2d.setColor(Color.BLACK);
        g2d.fillOval((int) leftWheelPivotX, (int) leftWheelPivotY, (int) wheelsRadius, (int) wheelsRadius);
        g2d.fillOval((int) rightWheelPivotX, (int) rightWheelPivotY, (int) wheelsRadius, (int) wheelsRadius);

        g2d.setColor(Color.WHITE);
        g2d.fillOval(
                (int) (leftWheelPivotX + wheelsRadius * 0.2 * Math.cos(ANGLE)),
                (int) (leftWheelPivotY + wheelsRadius * 0.2 * Math.cos(ANGLE)),
                (int) (wheelsRadius * 0.6), (int) (wheelsRadius * 0.6)
        );
        g2d.fillOval(
                (int) (rightWheelPivotX + wheelsRadius * 0.2 * Math.cos(ANGLE)),
                (int) (rightWheelPivotY + wheelsRadius * 0.2 * Math.cos(ANGLE)),
                (int) (wheelsRadius * 0.6), (int) (wheelsRadius * 0.6)
        );

        g2d.setColor(Color.WHITE);
    }

    private void paintGlasses(Graphics2D g2d) {
        {
            double sideGlassWidth = CAR_WIDTH * 0.75 * Math.cos(ANGLE) * 0.75;
            double sideGlassHeight = CAR_HEIGHT * Math.cos(ANGLE) * 0.75;
            double sideGlassPivotX = X + CAR_WIDTH / 4. * Math.cos(ANGLE) * Math.cos(ANGLE);
            double sideGlassPivotY = Y - sideGlassHeight / 2 + CAR_WIDTH / 5. * Math.sin(ANGLE) * Math.cos(ANGLE);

            Arc2D sideGlass = new Arc2D.Double(
                    sideGlassPivotX, sideGlassPivotY,
                    sideGlassWidth, sideGlassHeight, 0, 180, Arc2D.PIE
            );

            AffineTransform transform = new AffineTransform();
            transform.rotate(
                    ANGLE, sideGlassPivotX, sideGlassPivotY + sideGlassHeight / 2
            );

            Shape rotatedSideGlass = transform.createTransformedShape(sideGlass);
            g2d.setColor(Color.BLACK);
            g2d.fill(rotatedSideGlass);
        }

        {
            double backGlassWidth = CAR_WIDTH * 0.2 * Math.sin(ANGLE);
            double backGlassHeight = CAR_WIDTH * 2 / 9. * Math.sin(ANGLE);
            double backGlassPivotX = X + CAR_WIDTH * 0.7 * Math.cos(ANGLE) * Math.cos(ANGLE)
                    + CAR_WIDTH / 6. * Math.cos(ANGLE);
            double backGlassPivotY = Y + CAR_WIDTH * 0.7 * Math.cos(ANGLE) * Math.sin(ANGLE);

            Rectangle2D backGlass = new Rectangle2D.Double(
                    backGlassPivotX, backGlassPivotY, backGlassWidth, backGlassHeight
            );

            AffineTransform transform = new AffineTransform();
            transform.rotate(
                    -(Math.PI * 5 / 6. - ANGLE), backGlassPivotX, backGlassPivotY
            );

            Shape rotatedBackGlass = transform.createTransformedShape(backGlass);
            g2d.fill(rotatedBackGlass);
        }
    }

    private void paintLights(Graphics2D g2d) {
        double forwardLightWidth = CAR_WIDTH / 10. * Math.sin(ANGLE);
        double forwardLightHeight = CAR_HEIGHT / 4. * Math.sin(ANGLE);
        double leftLightPivotX = X + CAR_WIDTH * Math.pow(Math.cos(ANGLE), 2);
        double leftLightPivotY = Y + CAR_WIDTH * Math.cos(ANGLE) * Math.sin(ANGLE);
        {
            Rectangle2D leftLight = new Rectangle2D.Double(
                    leftLightPivotX, leftLightPivotY, forwardLightWidth, forwardLightHeight
            );

            AffineTransform transform = new AffineTransform();
            transform.rotate(-(Math.PI / 2 - ANGLE), leftLightPivotX, leftLightPivotY);

            Shape rotatedLeftLight = transform.createTransformedShape(leftLight);
            g2d.setColor(Color.RED);
            g2d.fill(rotatedLeftLight);

            double arcWidth = CAR_HEIGHT / 4. * Math.cos(ANGLE);
            double arcHeight = CAR_HEIGHT / 2. * Math.sin(ANGLE);
            double arcPivotX = X + CAR_WIDTH * Math.pow(Math.cos(ANGLE), 2) - arcWidth / 2;
            double arcPivotY = Y + CAR_WIDTH * Math.cos(ANGLE) * Math.sin(ANGLE) - arcHeight / 2;

            Arc2D arc = new Arc2D.Double(
                    arcPivotX, arcPivotY,
                    arcWidth, arcHeight, 180, 90, Arc2D.PIE
            );

            transform = new AffineTransform();
            transform.rotate(
                    -(Math.PI / 2 - ANGLE), arcPivotX + arcWidth / 2, arcPivotY + arcHeight / 2
            );

            Shape rotatedArc = transform.createTransformedShape(arc);
            g2d.setColor(Color.YELLOW);
            g2d.fill(rotatedArc);

            Rectangle2D leftYellowLight = new Rectangle2D.Double(
                    leftLightPivotX, leftLightPivotY, forwardLightWidth / 2, forwardLightHeight
            );

            transform = new AffineTransform();
            transform.rotate(-(Math.PI / 2 - ANGLE), leftLightPivotX, leftLightPivotY);

            Shape rotatedLeftYellowLight = transform.createTransformedShape(leftYellowLight);
            g2d.setColor(Color.YELLOW);
            g2d.fill(rotatedLeftYellowLight);
        }

        {
            double rightLightPivotX = leftLightPivotX + CAR_WIDTH / 3. * Math.sin(ANGLE) * Math.sin(ANGLE)
                    - forwardLightWidth * Math.sin(ANGLE);
            double rightLightPivotY = leftLightPivotY - CAR_WIDTH / 3. * Math.sin(ANGLE) * Math.cos(ANGLE)
                    + forwardLightWidth * Math.cos(ANGLE);

            Rectangle2D rightLight = new Rectangle2D.Double(
                    rightLightPivotX, rightLightPivotY, forwardLightWidth, forwardLightHeight
            );

            AffineTransform transform = new AffineTransform();
            transform.rotate(-(Math.PI / 2 - ANGLE), rightLightPivotX, rightLightPivotY);

            Shape rotatedRightLight = transform.createTransformedShape(rightLight);
            g2d.setColor(Color.RED);
            g2d.fill(rotatedRightLight);

            rightLightPivotX += forwardLightWidth * Math.sin(ANGLE) / 2;
            rightLightPivotY -= forwardLightWidth * Math.cos(ANGLE) / 2;

            Rectangle2D rightYellowLight = new Rectangle2D.Double(
                    rightLightPivotX, rightLightPivotY, forwardLightWidth / 2, forwardLightHeight
            );

            transform = new AffineTransform();
            transform.rotate(-(Math.PI / 2 - ANGLE), rightLightPivotX, rightLightPivotY);

            Shape rotatedRightYellowLight = transform.createTransformedShape(rightYellowLight);
            g2d.setColor(Color.YELLOW);
            g2d.fill(rotatedRightYellowLight);
        }

        {
            forwardLightWidth = CAR_WIDTH / 10. * Math.cos(ANGLE);
            forwardLightHeight = CAR_HEIGHT / 4. * Math.cos(ANGLE);
            Rectangle2D forwardLight = new Rectangle2D.Double(
                    X, Y, forwardLightWidth, forwardLightHeight
            );

            AffineTransform transform = new AffineTransform();
            transform.rotate(ANGLE, X, Y);

            Shape rotatedForwardLight = transform.createTransformedShape(forwardLight);
            g2d.setColor(Color.WHITE);
            g2d.fill(rotatedForwardLight);

            Rectangle2D forwardYellowLight = new Rectangle2D.Double(
                    X, Y, forwardLightWidth / 2, forwardLightHeight
            );

            transform = new AffineTransform();
            transform.rotate(ANGLE, X, Y);

            Shape rotatedForwardYellowLight = transform.createTransformedShape(forwardYellowLight);
            g2d.setColor(Color.YELLOW);
            g2d.fill(rotatedForwardYellowLight);
        }
    }

    private void paintText(Graphics2D g2d) {
        Font font = new Font("Arial", Font.ITALIC, 24);
        g2d.setFont(font);
        g2d.setColor(Color.RED);
        g2d.drawString(
                "69",
                (int) (X - CAR_HEIGHT / 3 * Math.sin(ANGLE) + CAR_WIDTH / 2 * Math.cos(ANGLE)),
                (int) (Y + CAR_HEIGHT / 3 * Math.cos(ANGLE) + CAR_WIDTH / 2 * Math.sin(ANGLE))
        );
    }
}
