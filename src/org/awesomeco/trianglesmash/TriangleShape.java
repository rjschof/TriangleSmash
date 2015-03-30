package org.awesomeco.trianglesmash;

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
    private float top;
    private float left;
    private float right;
    private float bottom;

    //TODO: Organize this.
    private float size;

    private Polygon polygon;

    // ----------------------------------------------------------
    /**
     * Creates a new TriangleShape object based on four different values that
     * describe the
     * @param top vertex for the top of the triangle
     * @param left vertex for the left of the triangle
     * @param right vertex for the right of the triangle
     * @param bottom
     */
    public TriangleShape(float top, float left, float right, float bottom)
    {
        this.top = top;
        this.left = left;
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
     * @param drawing
     */
    @Override
    public void draw(Drawing drawing)
    {
        Canvas canvas = drawing.getCanvas();

        if (isFilled())
        {
            // TODO: Organize this better.
            setPosition(calculateCentroid());
            PointF origin = getPosition();
            // END TODO

            Polygon tri = new Polygon(left + Math.abs((right-left)/2), top,
                left, bottom, right, bottom);
            getFill().fillPolygon(drawing, getAlpha(), tri,
                origin);
        }
        if (!getColor().isTransparent())
        {
            Paint paint = getFillPaint();
            float[] verts = { left + Math.abs((right-left)/2), top, left,
                bottom, right, bottom };

            //TODO: Fix the outline color of triangles.
            canvas.drawVertices(VertexMode.TRIANGLES, 3, verts, 0, null,
                0, null, 0, null, 0,
                0, paint);
        }
    }

    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
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
