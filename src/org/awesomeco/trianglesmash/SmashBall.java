package org.awesomeco.trianglesmash;

import sofia.util.Observable;
import android.graphics.PointF;
import android.graphics.RectF;
import sofia.graphics.OvalShape;

// -------------------------------------------------------------------------
/**
 *  Write a one-sentence summary of your class here.
 *  Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 *
 * @author Lauren Malhotra (laurcm6)
 * @author Robert Schofield (rjschof)
 * @author Adam Zelenka (zadam7)
 * @version 2015.04.07
 */

public class SmashBall extends Observable
{
    private float x;    // the x coordinate of the ball center
    private float y;    //the y coordinate of the ball center
    private float radius; //the radius of the ball
    private float velocityX; // x-component for velocity of the ball
    private float velocityY; // y-component for velocity of the ball

    // ----------------------------------------------------------
    /**
     * Create a new SmashBall object.
     * @param x x-coordinate of center of ball
     * @param y y-coordinate of center of ball
     * @param radius the radius of the ball
     * @param velocityX x-component of velocity for the ball
     * @param velocityY y-component of velocity for the ball
     */
    public SmashBall(float x, float y, float radius, float velocityX, float
        velocityY)
    {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }

    /**
     * Gets the position of the paddle on the screen in the form of a PointF
     * object.
     * @return position of the paddle as a Position object
     */
    public Position getPosition()
    {
        return new Position(x, y);
    }

    /**
     * Gets the x component of velocity for the ball.
     * @return the x-component of velocity
     */
    public float getVelocityX()
    {
        return velocityX;
    }

    /**
     * Gets the y component of velocity for the ball.
     * @return the y-component of velocity
     */
    public float getVelocityY()
    {
        return velocityY;
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
