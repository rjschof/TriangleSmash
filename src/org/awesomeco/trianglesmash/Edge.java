package org.awesomeco.trianglesmash;

import sofia.graphics.LineShape;

// -------------------------------------------------------------------------
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
    }

}
