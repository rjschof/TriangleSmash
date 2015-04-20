package org.awesomeco.trianglesmash;

import android.graphics.PointF;

// -------------------------------------------------------------------------
/**
 * The Paddle class maintains the data for the paddle that will be placed
 * on the screen.
 *
 * @author Lauren Malhotra (laurcm6)
 * @author Robert Schofield (rjschof)
 * @author Adam Zelenka (zadam7)
 * @version 2015.03.23
 */

public class Paddle
{
    private float x;
    private float y;
    private float width;
    private float height;

    // ----------------------------------------------------------
    /**
     * Create a new Paddle object for the game.
     * @param x the x-coordinate for the center of the paddle
     * @param y the y-coordinate for the center of the paddle
     * @param width the distance from the center to the edge in the x direction
     * @param height the distance from the center to the edge in the y direction
     */
    public Paddle(float x, float y, float width, float height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Gets the height of the paddle.
     * @return paddle height
     */
    public float getHeight()
    {
        return height;
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
     * Gets the width of the paddle
     * @return width of the paddle
     */
    public float getWidth()
    {
        return width;
    }

    /**
     * Sets the height of the paddle.
     * @param newHeight the new height of the paddle
     */
    public void setHeight(float newHeight)
    {
        height = newHeight;
    }

    /**
     * Sets the width of the paddle.
     * @param newWidth the new width of the paddle
     */
    public void setWidth(float newWidth)
    {
        width = newWidth;
    }
}
