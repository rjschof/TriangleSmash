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
 *  The TriangleShape class is a version of the Shape object that has three
 *  vertices that are bounded inside a box. The bounding box's coordinate points
 *  are specified in the constructor. This class was created as an addition to
 *  the Shapes that are included in the sofia-graphics library.
 *
 *  Note: Everything in this class is utilized by the screen class. If the
 *  screen class is working properly -- since it depends largely on the
 *  proper operation of this code -- then it means that this class is working
 *  properly, as well.
 *
 *  @author Robert Schofield (rjschof)
 *  @version 2015.04.02
 */

public class TriangleShape extends FillableShape
{
    private float top;
    private float left;
    private float right;
    private float bottom;

    private Polygon polygon;

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

        polygon = new Polygon(left + Math.abs((right-left)/2), top,
            left, bottom, right, bottom);

    }

    //-----------------------------------------------------------
    /**
     * Creates fixtures for the JBox2D functions of the shape.
     */
    @Override
    protected void createFixtures()
    {
        PolygonShape shape = new PolygonShape();
        Vec2[] vertices = new Vec2[polygon.size()];
        for (int i = 0; i < vertices.length; i++)
        {
            PointF point = polygon.get(i);
            vertices[i] = Box2DUtils.pointFToVec2(point);
        }
        shape.set(vertices, vertices.length);
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
            getFill().fillPolygon(drawing, getAlpha(), polygon, origin);
        }
        if (!getColor().isTransparent())
        {
            // if the getColor method returns another color that is not
            // transparent, it means that there is an outline. this draws it:
            Paint paint = getPaint();
            RectF bounds = getBounds();
            Path linePath = new Path();
            if (bottom > top)
            {
                // the triangle points upward in a default coordinate system
                linePath.moveTo(bounds.left +
                    Math.abs((bounds.right - bounds.left) / 2), bounds.top);
                linePath.lineTo(bounds.left, bounds.bottom);
                linePath.moveTo(bounds.left, bounds.bottom);
                linePath.lineTo(bounds.right, bounds.bottom);
                linePath.moveTo(bounds.right, bounds.bottom);
                linePath.lineTo(bounds.left +
                    Math.abs((bounds.right - bounds.left) / 2), bounds.top);
                linePath.close();
            }
            else if (top > bottom)
            {
                // the triangle points down in a default coordinate system
                linePath.moveTo(bounds.left +
                    Math.abs((bounds.right - bounds.left) / 2), bounds.bottom);
                linePath.lineTo(bounds.left, bounds.top);
                linePath.moveTo(bounds.left, bounds.top);
                linePath.lineTo(bounds.right, bounds.top);
                linePath.moveTo(bounds.right, bounds.top);
                linePath.lineTo(bounds.left +
                    Math.abs((bounds.right - bounds.left) / 2), bounds.bottom);
                linePath.close();
            }
            canvas.drawPath(linePath, paint); // the path is drawn on the view
        }
    }

    // ----------------------------------------------------------
    /**
     * Sets the position of the triangle. The left, right, top, and bottom
     * fields are all updated because they are used to calculate different parts
     * of the TriangleShape.
     * @param x the x coordinate of the position
     * @param y the y coordinate of the position
     */
    @Override
    public void setPosition(float x, float y)
    {
        left = x - distanceX;
        right = x + distanceX;

        if (bottom > top)
        {
            top = y - distanceYTop;
            bottom = y + distanceYBottom;
        }
        else
        {
            top = y + distanceYTop;
            bottom = y - distanceYBottom;
        }
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
     * Calculates the centroid of the triangle.
     * @return PointF object that represents the coordinates of the centroid
     */
    public PointF calculateCenterOfBox()
    {
        float x = Math.abs((left + right) / 2);
        float y = Math.abs((bottom + top) / 2);
        return new PointF(x, y);
    }

    // ----------------------------------------------------------
    /**
     * Retrieves the bounding box for this shape.
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

        if (bottom > top)
        {
            return new RectF(center.x - distanceX, center.y - distanceYTop,
                center.x + distanceX, center.y + distanceYBottom);
        }
        else
        {
            return new RectF(center.x - distanceX, center.y - distanceYBottom,
                center.x + distanceX, center.y + distanceYTop);
        }
    }

    // ----------------------------------------------------------
    /**
     * Sets the bounding box for this shape.
     * @param newBounds the new bounding box for this shape
     */
    @Override
    public void setBounds(RectF newBounds)
    {
        left = newBounds.left;
        right = newBounds.right;
        bottom = newBounds.bottom;
        top = newBounds.top;

        updateTransform(newBounds.centerX(), newBounds.centerY());
        float y = (top + bottom + bottom) / 3;

        this.distanceYTop = Math.abs(top - calculateCentroid().y);
        this.distanceYBottom = Math.abs(bottom - calculateCentroid().y);
        this.distanceX = Math.abs(left - calculateCentroid().x);

        polygon = new Polygon(left + Math.abs((right-left)/2), top,
            left, bottom, right, bottom);

        recreateFixtures();
        conditionallyRepaint();
    }
}
