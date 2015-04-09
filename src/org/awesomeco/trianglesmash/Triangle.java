package org.awesomeco.trianglesmash;

import android.graphics.PointF;
import sofia.graphics.Color;

// -------------------------------------------------------------------------
/**
 *  The Triangle class maintains the data for the triangles that will be placed
 *  on the screen.
 *
 *  @author Lauren Malhotra (laurcm6)
 *  @author Robert Schofield (rjschof)
 *  @author Adam Zelenka (zadam7)
 *  @version 2015.03.23
 */

public class Triangle
{
    private float x;
    private float y;
    private float size;
    private Color fillColor;

    // ----------------------------------------------------------
    /**
     * Create a new Triangle object for the game.
     * @param x the x coordinate of the triangle
     * @param y the y coordinate of the triangle
     * @param size the size of the triangle, measured from the centroid
     * @param color the color of the triangle
     */
    public Triangle(float x, float y, float size, Color color)
    {
        this.x = x;
        this.y = y;
        this.size = size;
        fillColor = color;
    }

    /**
     * Returns the fill color set for this triangle
     * @return fill color of triangle
     */
    public Color getFillColor()
    {
        return fillColor;
    }

    /**
     * Gets the position of a triangle in the form of a PointF object.
     * @return the triangle's position
     */
    public PointF getPosition()
    {
        return new PointF(x, y);
    }

    /**
     * Gets the size of this triangle, measured as distance from the centroid
     * @return distance of triangle vertices from centroid
     */
    public float getSize()
    {
        return size;
    }

    /**
     * Tells whether this object is equal to another object. If the other object
     * is an instance of Triangle, then the other object's position is compared
     * to this object's position for equality.
     * @return true if position is same, false if otherwise or not Triangle
     */
    @Override
    public boolean equals(Object other)
    {
        if (other instanceof Triangle)
        {
            return this.getPosition().equals(((Triangle)other).getPosition());
        }
        else
        {
            return false;
        }
    }
}
