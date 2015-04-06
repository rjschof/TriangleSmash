package org.awesomeco.trianglesmash;


import android.graphics.Path;
import sofia.graphics.internal.Box2DUtils;
import org.jbox2d.dynamics.Body;
import org.jbox2d.common.Vec2;
import android.graphics.PointF;
import sofia.graphics.Polygon;
import sofia.graphics.FillableShape;
import android.graphics.Canvas;
import android.graphics.Paint;
import org.jbox2d.collision.shapes.PolygonShape;
import android.graphics.RectF;
import sofia.graphics.Drawing;

// -------------------------------------------------------------------------
/**
 *  This class creates a new triangle shape that will be displayed in the view.
 *  Currently, triangles can only be oriented two different ways when they are
 *  initially create: with the tip of the triangle pointed up, or with the tip
 *  pointed down. However, they can be rotated and moved using all of the
 *  operations provided by the Sofia Library.
 *
 *  TODO: Reorganize some of the operations and data in this class.
 *  FIXME: Collision detection [createFixtures() method]
 *  FIXME: Movement of polygon's outlines with Sofia's physics methods.
 *
 *  @author Robert Schofield (rjschof)
 *  @version 2015.04.06
 */

public class TriangleShape extends FillableShape
{
    private float top;
    private float left;
    private float right;
    private float bottom;

    private float distanceX;
    private float distanceYTop;
    private float distanceYBottom;

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

        this.distanceYTop = Math.abs(top - calculateCentroid().y);
        this.distanceYBottom = Math.abs(bottom - calculateCentroid().y);
        this.distanceX = Math.abs(left - calculateCentroid().x);

        setPosition(calculateCentroid().x, calculateCentroid().y);

        getPaint().setAntiAlias(true);
    }

    /**
     * This method creates the fixtures that are used by JBox2D for physics.
     * FIXME: Collision detection does not work yet. I'm slowly but surely
     * figuring out why it does not work.
     */
    @Override
    protected void createFixtures()
    {
        PolygonShape shape = new PolygonShape();
        /*Vec2[] vertices = { new Vec2(left + Math.abs((left-right)/2), top),
            new Vec2(left, bottom), new Vec2(right, bottom) }; */
        Vec2[] vertices = { new Vec2(-80, 50),
            new Vec2(-105, 100), new Vec2(-55, 100) };
        /*
        Vec2[] vertices = { new Vec2(calculateCentroid().x,
                calculateCentroid().y - distanceYTop),
            new Vec2(calculateCentroid().x - distanceX,
                calculateCentroid().y - distanceYBottom),
            new Vec2(calculateCentroid().x + distanceX,
                calculateCentroid().y - distanceYBottom) }; */
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
    @Override
    public void setPosition(float x, float y)
    {
        left = x - distanceX;
        top = y - distanceYTop;
        right = x + distanceX;
        bottom = y + distanceYBottom;
        super.setPosition(x, y);
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

        return new RectF(center.x - distanceX, center.y - distanceYTop,
            center.x + distanceX, center.y + distanceYBottom);
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
        float y = (top + bottom + bottom) / 3;

        distanceX = newBounds.width() / 2;
        distanceYTop = newBounds.top - y;
        distanceYBottom = newBounds.bottom - y;

        recreateFixtures();
        conditionallyRepaint();
    }
}
