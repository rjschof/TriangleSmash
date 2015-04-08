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

public class SmashBall extends OvalShape
{

    // ----------------------------------------------------------
    /**
     * Create a new SmashBall object.
     * @param x x-coordinate of center of ball
     * @param y y-coordinate of center of ball
     * @param radius the radius of the ball
     */
    public SmashBall(float x, float y, float radius)
    {
        super(x, y, radius);
        setRestitution(5.0f);
    }

    /**
     * Called when the ball collides with the paddle on the screen.
     *
     * @param paddle the paddle for the game
     */
    public void onCollisionWith(Paddle paddle)
    {
        setLinearVelocity(getLinearVelocity().x, -getLinearVelocity().y);
    }

}
