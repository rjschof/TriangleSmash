package org.awesomeco.trianglesmash;

import sofia.graphics.Color;

// -------------------------------------------------------------------------
/**
 *  This class manages the information for the first level in the game.
 *
 * @author Lauren Malhotra (laurcm6)
 * @author Robert Schofield (rjschof)
 * @author Adam Zelenka (zadam7)
 *  @version 2015.04.07
 */

public class FirstLevel extends GameLevel
{
    // ----------------------------------------------------------
    /**
     * Create a new FirstLevel object.
     * @param levelNum the number of this level
     * @param height the height of the view
     * @param width the width of the view
     */
    public FirstLevel(int levelNum, float width, float height)
    {
        super(levelNum, width, height);
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
        float partOfScreenX = getViewWidth() / 12;
        float partOfScreenY = getViewHeight() / 14;
        for (int i = 0; i < 5; i++)
        {
            addTriangle(
                new Triangle((partOfScreenX * i*2) + (getViewWidth() / 12),
                (partOfScreenY) + (getViewHeight() / 18), getViewWidth() / 16,
                Color.red));
        }
    }

}
