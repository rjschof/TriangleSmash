package org.awesomeco.trianglesmash;

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

public class Triangle extends TriangleShape
{
    private float x;
    private float y;

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
        super(x - size, y - size, x + size, y + size);
        this.x = x;
        this.y = y;
        setFillColor(color);
        setColor(Color.black);
    }
}
