package org.awesomeco.trianglesmash;

import android.graphics.RectF;
import sofia.graphics.Color;

/**
 * The Triangle class maintains the data for the triangles that will be placed
 * on the screen.
 *
 * @author Lauren Malhotra (laurcm6)
 * @author Robert Schofield (rjschof)
 * @author Adam Zelenka (zadam7)
 * @version 2015.03.23
 */

public class Triangle extends TriangleShape
{
    private float size;
    private boolean flipped;

    // ----------------------------------------------------------
    /**
     * Create a new Triangle object for the game.
     * @param x the x coordinate of the triangle
     * @param y the y coordinate of the triangle
     * @param size the size of the triangle, measured from the centroid
     * @param color the color of the triangle
     * @param flipped whether the triangle is flipped
     */
    public Triangle(float x, float y, float size, Color color, boolean flipped)
    {
        super(x - size, y - size, x + size, y + size);
        if (flipped)
        {
            this.setBounds(new RectF(x - size, y + size, x + size, y - size));
            this.recreateFixtures();
        }
        this.size = size;
        this.flipped= flipped;
        setColor(Color.black);
        setFillColor(color);
        setPosition(x, y);
    }

    //---------------------------------------------------------
    /**
     * Gets the size of this triangle, measured as distance from the centroid
     * @return distance of triangle vertices from centroid
     */
    public float getSize()
    {
        return size;
    }

    //----------------------------------------------------------
    /**
     * Tells whether this object is equal to another object. If the other object
     * is an instance of Triangle, then the other object's position is compared
     * to this object's position for equality.
     * @return true if position is same, false if otherwise or not Triangle
     */
    @Override
    public boolean equals(Object other)
    {
        if (other == null)
        {
            return false;
        }
        else if (other instanceof Triangle)
        {
            return getPosition().x == ((Triangle)other).getPosition().x &&
                getPosition().y == ((Triangle)other).getPosition().y;
        }
        else
        {
            return false;
        }
    }

    /**
     * Tells whether the triangle is flipped (pointing downward) or unflipped
     * (pointing upward).
     * @return true if the triangle is flipped, false if the triangle is not
     * flipped
     */
    public boolean isFlipped()
    {
        return flipped;
    }
}
