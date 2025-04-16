package drawshapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

public class Triangle extends AbstractShape {
    private int size;

    public Triangle(Color color, Point center, int size) {
        super(center);
        this.color = color;
        this.size = size;
        int half = size / 2;
        setBoundingBox(center.x - half, center.x + half, center.y - half, center.y + half);
    }

    @Override
    public void draw(Graphics g) {
        int half = size / 2;
        int[] xPoints = {
            anchorPoint.x,
            anchorPoint.x - half,
            anchorPoint.x + half
        };
        int[] yPoints = {
            anchorPoint.y - half, // top point
            anchorPoint.y + half, // bottom left
            anchorPoint.y + half  // bottom right
        };

        g.setColor(isSelected() ? color.darker() : color);
        g.fillPolygon(xPoints, yPoints, 3);
    }

    @Override
    public void setAnchorPoint(Point p) {
        this.anchorPoint = p;
        int half = size / 2;
        setBoundingBox(p.x - half, p.x + half, p.y - half, p.y + half);
    }

    @Override
    public void scaleup() {
        size = (int)(size * 1.25);
        setAnchorPoint(anchorPoint);
    }

    @Override
    public void scaleDown() {
        size = (int)(size * 0.8);
        setAnchorPoint(anchorPoint);
    }

    @Override
    public IShape copy() {
        return new Triangle(color, new Point(anchorPoint.x, anchorPoint.y), size);
    }

    @Override
    public String toString() {
        return String.format("TRIANGLE %d %d %d %s %s", 
            anchorPoint.x,
            anchorPoint.y,
            size,
            Util.colorToString(getColor()),
            selected);
    }
}
