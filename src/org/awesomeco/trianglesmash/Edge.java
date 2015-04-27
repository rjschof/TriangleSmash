package org.awesomeco.trianglesmash;

import sofia.graphics.LineShape;

/**
 * Creates edges to occupy the borders of the screen and prevent objects in the
 * screen from leaving the screen.
 *
 * @author Lauren Malhotra (laurcm6)
 * @author Robert Schofield (rjschof)
 * @author Adam Zelenka (zadam7)
 * @version 2015.04.07
 */

public class Edge extends LineShape
{
    private float left;
    private float top;
    private float right;
    private float bottom;

    // ----------------------------------------------------------
    /**
     * Create a new Edge object.
     * @param left the left coordinate point
     * @param top the top coordinate point
     * @param right the right coordinate point
     * @param bottom the bottom coordinate point
     */
    public Edge(float left, float top, float right, float bottom)
    {
        super(left, top, right, bottom);
        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;
    }

    //-----------------------------------------------------------
    /**
     * Determines whether the Edge object is equal to another object.
     * @param other the other object being compared
     * @return true if the objects are equal, false if they are not equal
     */
    @Override
    public boolean equals(Object other)
    {
        if (other == null)
        {
            return false;
        }
        else if (other instanceof Edge)
        {
            return (left == ((Edge)other).left) &&
                (right == ((Edge)other).right) &&
                (top == ((Edge)other).top) &&
                (bottom == ((Edge)other).bottom);
        }
        else
        {
            return false;
        }
    }
}
