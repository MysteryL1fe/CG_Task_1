package ru.vsu.cs.khanin.dmitrii.drawables;

import ru.vsu.cs.khanin.dmitrii.Drawable;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Rectangle2D;

public class Car implements Drawable {
    private int x, y, width, height;
    private double angle;

    public Car(int x, int y, int width, int height, double angle) {
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
        angle %= Math.PI;
        this.angle = angle;
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
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
                x, y, width * Math.cos(angle), height / 2. * Math.cos(angle)
        );

        AffineTransform transform = new AffineTransform();
        transform.rotate(angle, x, y);

        Shape rotatedSide = transform.createTransformedShape(side);
        g2d.setColor(Color.BLUE);
        g2d.fill(rotatedSide);
    }

    private void paintBack(Graphics2D g2d) {
        double backPivotX = x + width * Math.pow(Math.cos(angle), 2);
        double backPivotY = y + width * Math.cos(angle) * Math.sin(angle);

        Rectangle2D back = new Rectangle2D.Double(
                backPivotX, backPivotY,
                width / 3. * Math.sin(angle), height / 2. * Math.sin(angle)
        );

        AffineTransform transform = new AffineTransform();
        transform.rotate(-(Math.PI / 2 - angle), backPivotX, backPivotY);

        Shape rotatedBack = transform.createTransformedShape(back);
        g2d.setColor(Color.BLUE);
        g2d.fill(rotatedBack);
    }

    private void paintArc(Graphics2D g2d) {
        double arcWidth = height * Math.cos(angle);
        double arcHeight = height * Math.sin(angle);
        double arcPivotX = x + width * Math.pow(Math.cos(angle), 2) - arcWidth / 2;
        double arcPivotY = y + width * Math.cos(angle) * Math.sin(angle) - arcHeight / 2;

        Arc2D arc = new Arc2D.Double(
                arcPivotX, arcPivotY,
                arcWidth, arcHeight, 180, 90, Arc2D.PIE
        );

        AffineTransform transform = new AffineTransform();
        transform.rotate(
                -(Math.PI / 2 - angle), arcPivotX + arcWidth / 2, arcPivotY + arcHeight / 2
        );

        Shape rotatedArc = transform.createTransformedShape(arc);
        g2d.setColor(Color.BLUE);
        g2d.fill(rotatedArc);
    }

    private void paintBonnet(Graphics2D g2d) {
        Rectangle2D rect = new Rectangle2D.Double(
                x, y, width / 3. * Math.sin(angle), width * Math.cos(angle)
        );

        AffineTransform transform = new AffineTransform();
        transform.rotate(-(Math.PI / 2 - angle), x, y);

        Shape rotatedRect = transform.createTransformedShape(rect);
        g2d.setColor(Color.BLUE);
        g2d.fill(rotatedRect);
    }

    private void paintUpperArc(Graphics2D g2d) {
        double arcWidth = width * 0.75 * Math.cos(angle);
        double arcHeight = height * Math.cos(angle);
        double arcPivotX = x + width / 6. * Math.cos(angle) * Math.cos(angle);
        double arcPivotY = y - arcHeight / 2 + width / 6. * Math.sin(angle) * Math.cos(angle);

        {
            Arc2D arc = new Arc2D.Double(
                    arcPivotX, arcPivotY,
                    arcWidth, arcHeight, 0, 180, Arc2D.PIE
            );

            AffineTransform transform = new AffineTransform();
            transform.rotate(
                    angle, arcPivotX, arcPivotY + arcHeight / 2
            );

            Shape rotatedArc = transform.createTransformedShape(arc);
            g2d.setColor(Color.BLACK);
            g2d.draw(rotatedArc);
        }

        {
            arcPivotX += width / 3. * Math.sin(angle) * Math.sin(angle);
            arcPivotY -= width / 3. * Math.sin(angle) * Math.cos(angle);

            AffineTransform transform = new AffineTransform();
            transform.rotate(
                    angle, arcPivotX, arcPivotY + arcHeight / 2
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
                (int) (x + width / 6. * Math.cos(angle) * Math.cos(angle)),
                (int) (y + width / 6. * Math.sin(angle) * Math.cos(angle)),
                (int) arcPivotX, (int) (arcPivotY + arcHeight / 2)
        );

        g2d.drawLine(
                (int) (x + width / 6. * Math.cos(angle) * Math.cos(angle) + arcWidth * Math.cos(angle)),
                (int) (y + width / 6. * Math.sin(angle) * Math.cos(angle) + arcWidth * Math.sin(angle)),
                (int) (arcPivotX + arcWidth * Math.cos(angle)),
                (int) (arcPivotY + arcHeight / 2 + arcWidth * Math.sin(angle))
        );
    }

    private void paintWheels(Graphics2D g2d) {
        double wheelsRadius = height / 2. * Math.cos(angle);
        double leftWheelPivotX = x - height / 2. * Math.cos(angle) * Math.sin(angle)
                + width / 5. * Math.cos(angle) * Math.cos(angle) - wheelsRadius / 2;
        double leftWheelPivotY = y + height / 2. * Math.cos(angle) * Math.cos(angle)
                + width / 5. * Math.cos(angle) * Math.sin(angle) - wheelsRadius / 2;
        double rightWheelPivotX = x - height / 2. * Math.cos(angle) * Math.sin(angle)
                + width * 0.8 * Math.cos(angle) * Math.cos(angle) - wheelsRadius / 2;
        double rightWheelPivotY = y + height / 2. * Math.cos(angle) * Math.cos(angle)
                + width * 0.8 * Math.cos(angle) * Math.sin(angle) - wheelsRadius / 2;

        g2d.setColor(Color.BLACK);
        g2d.fillOval((int) leftWheelPivotX, (int) leftWheelPivotY, (int) wheelsRadius, (int) wheelsRadius);
        g2d.fillOval((int) rightWheelPivotX, (int) rightWheelPivotY, (int) wheelsRadius, (int) wheelsRadius);

        g2d.setColor(Color.WHITE);
        g2d.fillOval(
                (int) (leftWheelPivotX + wheelsRadius * 0.2 * Math.cos(angle)),
                (int) (leftWheelPivotY + wheelsRadius * 0.2 * Math.cos(angle)),
                (int) (wheelsRadius * 0.6), (int) (wheelsRadius * 0.6)
        );
        g2d.fillOval(
                (int) (rightWheelPivotX + wheelsRadius * 0.2 * Math.cos(angle)),
                (int) (rightWheelPivotY + wheelsRadius * 0.2 * Math.cos(angle)),
                (int) (wheelsRadius * 0.6), (int) (wheelsRadius * 0.6)
        );

        g2d.setColor(Color.WHITE);
    }

    private void paintGlasses(Graphics2D g2d) {
        {
            double sideGlassWidth = width * 0.75 * Math.cos(angle) * 0.75;
            double sideGlassHeight = height * Math.cos(angle) * 0.75;
            double sideGlassPivotX = x + width / 4. * Math.cos(angle) * Math.cos(angle);
            double sideGlassPivotY = y - sideGlassHeight / 2 + width / 5. * Math.sin(angle) * Math.cos(angle);

            Arc2D sideGlass = new Arc2D.Double(
                    sideGlassPivotX, sideGlassPivotY,
                    sideGlassWidth, sideGlassHeight, 0, 180, Arc2D.PIE
            );

            AffineTransform transform = new AffineTransform();
            transform.rotate(
                    angle, sideGlassPivotX, sideGlassPivotY + sideGlassHeight / 2
            );

            Shape rotatedSideGlass = transform.createTransformedShape(sideGlass);
            g2d.setColor(Color.BLACK);
            g2d.fill(rotatedSideGlass);
        }

        {
            double backGlassWidth = width * 0.2 * Math.sin(angle);
            double backGlassHeight = width * 2 / 9. * Math.sin(angle);
            double backGlassPivotX = x + width * 0.7 * Math.cos(angle) * Math.cos(angle)
                    + width / 6. * Math.cos(angle);
            double backGlassPivotY = y + width * 0.7 * Math.cos(angle) * Math.sin(angle);

            Rectangle2D backGlass = new Rectangle2D.Double(
                    backGlassPivotX, backGlassPivotY, backGlassWidth, backGlassHeight
            );

            AffineTransform transform = new AffineTransform();
            transform.rotate(
                    -(Math.PI * 5 / 6. - angle), backGlassPivotX, backGlassPivotY
            );

            Shape rotatedBackGlass = transform.createTransformedShape(backGlass);
            g2d.fill(rotatedBackGlass);
        }
    }

    private void paintLights(Graphics2D g2d) {
        double forwardLightWidth = width / 10. * Math.sin(angle);
        double forwardLightHeight = height / 4. * Math.sin(angle);
        double leftLightPivotX = x + width * Math.pow(Math.cos(angle), 2);
        double leftLightPivotY = y + width * Math.cos(angle) * Math.sin(angle);
        {
            Rectangle2D leftLight = new Rectangle2D.Double(
                    leftLightPivotX, leftLightPivotY, forwardLightWidth, forwardLightHeight
            );

            AffineTransform transform = new AffineTransform();
            transform.rotate(-(Math.PI / 2 - angle), leftLightPivotX, leftLightPivotY);

            Shape rotatedLeftLight = transform.createTransformedShape(leftLight);
            g2d.setColor(Color.RED);
            g2d.fill(rotatedLeftLight);

            double arcWidth = height / 4. * Math.cos(angle);
            double arcHeight = height / 2. * Math.sin(angle);
            double arcPivotX = x + width * Math.pow(Math.cos(angle), 2) - arcWidth / 2;
            double arcPivotY = y + width * Math.cos(angle) * Math.sin(angle) - arcHeight / 2;

            Arc2D arc = new Arc2D.Double(
                    arcPivotX, arcPivotY,
                    arcWidth, arcHeight, 180, 90, Arc2D.PIE
            );

            transform = new AffineTransform();
            transform.rotate(
                    -(Math.PI / 2 - angle), arcPivotX + arcWidth / 2, arcPivotY + arcHeight / 2
            );

            Shape rotatedArc = transform.createTransformedShape(arc);
            g2d.setColor(Color.YELLOW);
            g2d.fill(rotatedArc);

            Rectangle2D leftYellowLight = new Rectangle2D.Double(
                    leftLightPivotX, leftLightPivotY, forwardLightWidth / 2, forwardLightHeight
            );

            transform = new AffineTransform();
            transform.rotate(-(Math.PI / 2 - angle), leftLightPivotX, leftLightPivotY);

            Shape rotatedLeftYellowLight = transform.createTransformedShape(leftYellowLight);
            g2d.setColor(Color.YELLOW);
            g2d.fill(rotatedLeftYellowLight);
        }

        {
            double rightLightPivotX = leftLightPivotX + width / 3. * Math.sin(angle) * Math.sin(angle)
                    - forwardLightWidth * Math.sin(angle);
            double rightLightPivotY = leftLightPivotY - width / 3. * Math.sin(angle) * Math.cos(angle)
                    + forwardLightWidth * Math.cos(angle);

            Rectangle2D rightLight = new Rectangle2D.Double(
                    rightLightPivotX, rightLightPivotY, forwardLightWidth, forwardLightHeight
            );

            AffineTransform transform = new AffineTransform();
            transform.rotate(-(Math.PI / 2 - angle), rightLightPivotX, rightLightPivotY);

            Shape rotatedRightLight = transform.createTransformedShape(rightLight);
            g2d.setColor(Color.RED);
            g2d.fill(rotatedRightLight);

            rightLightPivotX += forwardLightWidth * Math.sin(angle) / 2;
            rightLightPivotY -= forwardLightWidth * Math.cos(angle) / 2;

            Rectangle2D rightYellowLight = new Rectangle2D.Double(
                    rightLightPivotX, rightLightPivotY, forwardLightWidth / 2, forwardLightHeight
            );

            transform = new AffineTransform();
            transform.rotate(-(Math.PI / 2 - angle), rightLightPivotX, rightLightPivotY);

            Shape rotatedRightYellowLight = transform.createTransformedShape(rightYellowLight);
            g2d.setColor(Color.YELLOW);
            g2d.fill(rotatedRightYellowLight);
        }

        {
            forwardLightWidth = width / 10. * Math.cos(angle);
            forwardLightHeight = height / 4. * Math.cos(angle);
            Rectangle2D forwardLight = new Rectangle2D.Double(
                    x, y, forwardLightWidth, forwardLightHeight
            );

            AffineTransform transform = new AffineTransform();
            transform.rotate(angle, x, y);

            Shape rotatedForwardLight = transform.createTransformedShape(forwardLight);
            g2d.setColor(Color.WHITE);
            g2d.fill(rotatedForwardLight);

            Rectangle2D forwardYellowLight = new Rectangle2D.Double(
                    x, y, forwardLightWidth / 2, forwardLightHeight
            );

            transform = new AffineTransform();
            transform.rotate(angle, x, y);

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
                (int) (x - height / 3 * Math.sin(angle) + width / 2 * Math.cos(angle)),
                (int) (y + height / 3 * Math.cos(angle) + width / 2 * Math.sin(angle))
        );
    }
}
