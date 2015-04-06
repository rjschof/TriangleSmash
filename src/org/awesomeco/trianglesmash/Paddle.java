package org.awesomeco.trianglesmash;

import org.jbox2d.collision.shapes.EdgeShape;
import sofia.graphics.ViewEdges;
import sofia.graphics.RectangleShape;

// -------------------------------------------------------------------------
/**
 *  The Paddle class maintains the data for the paddle that will be placed
 *  on the screen.
 *
 *  @author Lauren Malhotra (laurcm6)
 *  @author Robert Schofield (rjschof)
 *  @author Adam Zelenka (zadam7)
 *  @version 2015.03.23
 */

public class Paddle extends RectangleShape
{
    private float x;
    private float y;
    private float xSize;
    private float ySize;

    // ----------------------------------------------------------
    /**
     * Create a new Paddle object for the game.
     * @param x the x-coordinate for the center of the paddle
     * @param y the y-coordinate for the center of the paddle
     * @param xSize the distance from the center to the edge in the x direction
     * @param ySize the distance from the center to the edge in the y direction
     */
    public Paddle(float x, float y, float xSize, float ySize)
    {
        super(x - xSize, y - ySize, x + xSize, y + ySize);
        this.x = x;
        this.y = y;
        this.xSize = xSize;
        this.ySize = ySize;
    }
}
