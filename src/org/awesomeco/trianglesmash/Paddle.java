package org.awesomeco.trianglesmash;

import sofia.graphics.RectangleShape;
import sofia.util.Observable;
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

public class Paddle extends Observable
{
    private float x; //x coordinate of the paddle
    private float y; //y coordinate of the paddle
    private float width; //the width of the paddle
    private float height; //the height of the paddle

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

    //------------------------------------------------------------
    /**
     * Gets the height of the paddle.
     * @return paddle height
     */
    public float getHeight()
    {
        return height;
    }

    //------------------------------------------------------------
    /**
     * Gets the position of the paddle on the screen in the form of a Position
     * object.
     * @return position of the paddle as PointF
     */
    public Position getPosition()
    {
        return new Position(x, y);
    }

    //------------------------------------------------------------
    /**
     * Gets the width of the paddle
     * @return width of the paddle
     */
    public float getWidth()
    {
        return width;
    }

    //------------------------------------------------------------
    /**
     * Sets the height of the paddle.
     * @param newHeight the new height of the paddle
     */
    public void setHeight(float newHeight)
    {
        height = newHeight;
    }

    //------------------------------------------------------------
    /**
     * Sets the paddle to a new position on the screen.
     * @param position the position on the screen as a Position object
     */
    public void setPosition(Position position)
    {
        this.x = position.x();
        this.y = position.y();
        notifyObservers();
    }

    //-------------------------------------------------------------
    public RectangleShape toRectangleShape()
    {
        return new RectangleShape(x - (width / 2), y - (height / 2),
            x + (width / 2), y + (height / 2));
    }

    //-------------------------------------------------------------
    /**
     * Sets the width of the paddle.
     * @param newWidth the new width of the paddle
     */
    public void setWidth(float newWidth)
    {
        width = newWidth;
    }

    //--------------------------------------------------------------
    /**
     * Determines whether this Paddle is equal to another object.
     * @return true if the objects are equal, false if they are not equal
     */
    @Override
    public boolean equals(Object other)
    {
        if (other == null)
        {
            return false;
        }
        else if (other instanceof Paddle)
        {
            return (this.getPosition().equals(((Paddle)other).getPosition()) &&
                    this.getWidth() ==((Paddle)other).getWidth() &&
                    this.getHeight() == ((Paddle)other).getHeight());
        }
        else
        {
            return false;
        }
    }
}
