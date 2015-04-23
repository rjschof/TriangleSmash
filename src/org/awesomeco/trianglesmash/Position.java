package org.awesomeco.trianglesmash;

import android.graphics.PointF;

// -------------------------------------------------------------------------
/**
 * Creates Position objects to contain x and y coordinates to be used in the
 * model in place of PointF objects.
 *
 *  @author Robert Schofield (rjschof)
 *  @author Lauren Malhotra (laurcm6)
 *  @author Adam Zelenka (zadam7)
 *  @version 2015.04.20
 */

public class Position
{
    private float x; //x coordinate of position
    private float y; //y coordinate of position

    // ----------------------------------------------------------
    /**
     * Create a new Position object.
     */
    public Position(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    //-----------------------------------------------------------
    /**
     * Gets the position as a PointF object.
     * @return this position as PointF
     */
    public PointF toPointF()
    {
        return new PointF(x, y);
    }

    //-----------------------------------------------------------
    /**
     * Gets the x coordinate for this position.
     * @return the x coordinate for this position
     */
    public float x()
    {
        return x;
    }

    //-----------------------------------------------------------
    /**
     * Gets the y coordinate for this position.
     * @return the y coordinate for this position
     */
    public float y()
    {
        return y;
    }

    //-----------------------------------------------------------
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
