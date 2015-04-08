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
    private float maxHeight;
    private float maxWidth;

    // ----------------------------------------------------------
    /**
     * Create a new FirstLevel object.
     * @param levelNum
     */
    public FirstLevel(int levelNum)
    {
        super(levelNum);
    }

    /**
     * Create a new FirstLevel object.
     * @param levelNum the number for the level
     * @param maxHeight the maximum height
     * @param maxWidth the maximum width
     */
    public FirstLevel(int levelNum, float maxHeight, float maxWidth)
    {
        super(levelNum);
        this.maxHeight = maxHeight;
        this.maxWidth = maxWidth;
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
        float partOfScreenX = maxWidth / 12;
        float partOfScreenY = maxHeight / 14;
        for (int i = 0; i < 5; i++)
        {
            addTriangle(new Triangle((partOfScreenX * i*2) + (maxWidth / 12),
                (partOfScreenY) + (maxHeight / 18), maxWidth / 16,
                Color.red));
        }


        for (Triangle t: getTriangleList())
        {
            t.setRestitution(0.0f); // Restitution is the "bounciness" of a
                                    // shape. Make this higher in other levels
                                    // to increase the difficulty.
        }
    }

}
