package org.awesomeco.trianglesmash;

import sofia.graphics.ViewEdges;
import android.graphics.PointF;
import android.graphics.RectF;
import sofia.graphics.OvalShape;

// -------------------------------------------------------------------------
/**
 *  Write a one-sentence summary of your class here.
 *  Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 *
 *  @author Robert
 *  @version Apr 7, 2015
 */

public class SmashBall
{
    private float x;
    private float y;
    private float radius;

    // ----------------------------------------------------------
    /**
     * Create a new SmashBall object.
     * @param x x-coordinate of center of ball
     * @param y y-coordinate of center of ball
     * @param radius the radius of the ball
     */
    public SmashBall(float x, float y, float radius)
    {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    /**
     * Gets the position of the paddle on the screen in the form of a PointF
     * object.
     * @return position of the paddle as PointF
     */
    public PointF getPosition()
    {
        return new PointF(x, y);
    }

    /**
     * Gets the radius of the ball
     * @return ball radius
     */
    public float getRadius()
    {
        return radius;
    }
}
