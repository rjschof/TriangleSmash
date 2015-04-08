package org.awesomeco.trianglesmash;

import sofia.graphics.Color;

// -------------------------------------------------------------------------
/**
 *  This class manages the information for the first level in the game.
 *
 *  @author Robert Schofield (rjschof)
 *  @version 2015.04.07
 */

public class FirstLevel extends GameLevel
{

    // ----------------------------------------------------------
    /**
     * Create a new FirstLevel object.
     * @param levelNum
     */
    public FirstLevel(int levelNum)
    {
        super(levelNum);
    }

    // ----------------------------------------------------------
    /**
     * Method to add triangles to the level.
     *
     * FIXME: Change the coordinate points to scale to the screen based on the
     * size of the device.
     */
    @Override
    public void addTrianglesToLevel()
    {
        addTriangle(new Triangle(25, 25, 20, Color.red));
        addTriangle(new Triangle(75, 25, 20, Color.red));
        addTriangle(new Triangle(125, 25, 20, Color.red));
        addTriangle(new Triangle(175, 25, 20, Color.red));
        addTriangle(new Triangle(225, 25, 20, Color.red));
        addTriangle(new Triangle(125, 25, 20, Color.red));

        for (Triangle t: getTriangleList())
        {
            t.setRestitution(0.0f); // Restitution is the "bounciness" of a
                                    // shape. Make this higher in other levels
                                    // to increase the difficulty.
        }
    }

}
