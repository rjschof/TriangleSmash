package org.awesomeco.trianglesmash;

import android.graphics.Color;
import android.graphics.Path;
import sofia.graphics.internal.Box2DUtils;
import org.jbox2d.dynamics.Body;
import org.jbox2d.common.Vec2;
import android.graphics.PointF;
import sofia.graphics.Polygon;
import android.graphics.Canvas.VertexMode;
import sofia.graphics.FillableShape;
import android.graphics.Canvas;
import android.graphics.Paint;
import org.jbox2d.collision.shapes.PolygonShape;
import sofia.graphics.RectangleShape;
import android.graphics.RectF;
import sofia.graphics.Drawing;
import sofia.graphics.Shape;

// -------------------------------------------------------------------------
/**
 *  This class creates a new triangle shape that will be displayed in the view.
 *  Currently, triangles can only be oriented two different ways: with the tip
 *  of the triangle pointed up, with the tip pointed down.
 *
 *  TODO: Allow triangles to be oriented in any direction.
 *  TODO: Reorganize some of the operations and data in this class.
 *
 *  @author Robert Schofield (rjschof)
 *  @version 2015.03.30
 */

public class TriangleShape extends FillableShape
{
    private float size;

    private float top;
    private float left;
    private float right;
    private float bottom;

    private int[] colors = {
        Color.BLACK,
        Color.BLACK,
        Color.BLACK
    };

    // ----------------------------------------------------------
    /**
     * Creates a new TriangleShape object based on four different values that
     * describe the top, left, right, and bottom coordinate points of the
     * triangle.
     * @param left value for the left coordinate of the triangle
     * @param top value for the top coordinate of the triangle
     * @param right value for the right coordinate of the triangle
     * @param bottom value for the bottom coordinate of the triangle
     */
    public TriangleShape(float left, float top, float right, float bottom)
    {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;

        this.size = Math.abs(bottom - calculateCentroid().y);
        setPosition(calculateCentroid());

        this.getPaint().setAntiAlias(true);
    }

    // ----------------------------------------------------------
    /**
     * Creates a new TriangleShape object based on three different vertices.
     * @param xcenter the center of the triangle in the x direction
     * @param ycenter the center of the triangle in the y direction
     * @param size the size of the triangle, measured from center to vertex
     */
    public TriangleShape(float xcenter, float ycenter, float size)
    {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    @Override
    protected void createFixtures()
    {
        PolygonShape shape = new PolygonShape();
        Vec2[] vertices = { new Vec2(left + Math.abs((left-right)/2), top),
            new Vec2(left, bottom), new Vec2(right, bottom) };
        shape.set(vertices, 3);
        addFixtureForShape(shape);

    }

    // ----------------------------------------------------------
    /**
     * Draws this TriangleShape on the canvas.
     * @param drawing the drawing to put the TriangleShape on
     */
    @Override
    public void draw(Drawing drawing)
    {
        Canvas canvas = drawing.getCanvas();

        if (isFilled())
        {
            PointF origin = getPosition();
            Polygon tri = new Polygon(left + Math.abs((right-left)/2), top,
                left, bottom, right, bottom);
            getFill().fillPolygon(drawing, getAlpha(), tri,
                origin);
        }
        if (!getColor().isTransparent())
        {
            Paint paint = getPaint();
            Path linePath = new Path();
            linePath.moveTo(left + Math.abs((right - left) / 2), top);
            linePath.lineTo(left, bottom);
            linePath.moveTo(left, bottom);
            linePath.lineTo(right, bottom);
            linePath.moveTo(right, bottom);
            linePath.lineTo(left + Math.abs((right - left) / 2), top);
            linePath.close();
            canvas.drawPath(linePath, paint);
        }
    }

    // ----------------------------------------------------------
    /**
     * Calculates the centroid of the triangle.
     * @return PointF object that represents the coordinates of the centroid
     */
    public PointF calculateCentroid()
    {
        float x = (left + right + (left + Math.abs((left - right) / 2))) / 3;
        float y = (top + bottom + bottom) / 3;
        return new PointF(x, y);
    }

    // ----------------------------------------------------------
    /**
     * Retrieves the bounding box for this shape.
     * TODO: Figure out how to really implement this.
     * NOTICE: This method is entirely based off of the getBounds() method from
     * OvalShape.
     * @return the bounding box for this shape
     */
    @Override
    public RectF getBounds()
    {
        // If the body has been created, update the center point using the
        // body's current position.

        PointF center = calculateCentroid();

        Body b2Body = getB2Body();
        if (b2Body != null)
        {
            Box2DUtils.vec2ToPointF(b2Body.getPosition(), center);
        }

        return new RectF(center.x - size, center.y - size,
            center.x + size, center.y + size);
    }

    // ----------------------------------------------------------
    /**
     * Sets the bounding box for this shape.
     * TODO: Figure out how to really implement this.
     * NOTICE: This method is entirely based off of the getBounds() method from
     * OvalShape.
     */
    @Override
    public void setBounds(RectF newBounds)
    {
        updateTransform(newBounds.centerX(), newBounds.centerY());
        size = newBounds.width() / 2;

        recreateFixtures();
        conditionallyRepaint();
    }
}
