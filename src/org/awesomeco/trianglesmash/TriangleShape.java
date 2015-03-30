package org.awesomeco.trianglesmash;

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
 *
 *  @author Robert Schofield (rjschof)
 *  @version 2015.03.28
 */

public class TriangleShape extends FillableShape
{
    private RectF bounds;
    private float top;
    private float left;
    private float right;
    private float bottom;

    // ----------------------------------------------------------
    /**
     * Creates a new TriangleShape object based on three different vertices.
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
        bounds = new RectF(top, left, right, bottom);
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

    @Override
    public void draw(Drawing drawing)
    {
        Canvas canvas = drawing.getCanvas();

        if (isFilled())
        {
            Polygon tri = new Polygon(left + Math.abs((right-left)/2), top,
                left, bottom, right, bottom);
            getFill().fillPolygon(drawing, getAlpha(), tri,
                new PointF(top, left + (right-left)/2));
        }
        if (!getColor().isTransparent())
        {
            Paint paint = getFillPaint();
            float[] verts = { left + Math.abs((right-left)/2), top, left,
                bottom, right, bottom };
            canvas.drawVertices(VertexMode.TRIANGLES, 3, verts, 0, null,
                0, null, 0, null, 0,
                0, paint);
        }
    }

    /**
     * TODO: Figure out how to really implement this.
     */
    @Override
    public RectF getBounds()
    {
        return new RectF(0,0,0,0);
    }

    @Override
    public void setBounds(RectF rect)
    {
        bounds = rect;
    }
}
