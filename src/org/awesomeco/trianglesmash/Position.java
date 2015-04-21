package org.awesomeco.trianglesmash;

import android.graphics.PointF;

// -------------------------------------------------------------------------
/**
 *  Write a one-sentence summary of your class here.
 *  Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 *
 *  @author Robert
 *  @version Apr 20, 2015
 */

public class Position
{
    private float x;
    private float y;

    // ----------------------------------------------------------
    /**
     * Create a new Position object.
     */
    public Position(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets the position as a PointF object.
     * @return this position as PointF
     */
    public PointF toPointF()
    {
        return new PointF(x, y);
    }

    /**
     * Gets the x coordinate for this position.
     * @return the x coordinate for this position
     */
    public float x()
    {
        return x;
    }

    /**
     * Gets the y coordinate for this position.
     * @return the y coordinate for this position
     */
    public float y()
    {
        return y;
    }

    /**
     * Tells whether this object is equal to another object. If the other object
     * is an instance of Position, then the other object's x and y coordinates
     * are compared with this object's x and y coordinates.
     * @return true if position is same, false if otherwise or not Position
     */
    @Override
    public boolean equals(Object other)
    {
        if (other == null)
        {
            return false;
        }
        else if (other instanceof Position)
        {
            return (this.x == ((Position)other).x) &&
                (this.y == ((Position)other).y);
        }
        else
        {
            return false;
        }
    }
}
