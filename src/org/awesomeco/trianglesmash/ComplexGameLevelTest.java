package org.awesomeco.trianglesmash;

import java.util.LinkedList;
import student.TestCase;
/**
 * // -------------------------------------------------------------------------
/**
 *  Tests the methods in the ComplexGameLevel class.
 *
 *  @author Adam Zelenka (zadam7)
 *  @author Robert Schofield (rjschof)
 *  @author Lauren Malhotra (laurcm6)
 *  @version 2015.04.26
 */
public class ComplexGameLevelTest extends TestCase
{
    private ComplexGameLevel level; //the level being tested

    //--------------------------------------------------------------
    /**
     * Constructor for ComplexGameLevelTest doe nothing.
     */
    public ComplexGameLevelTest(String name)
    {
        super(name);
    }

    //--------------------------------------------------------------
    /**
     * Instantiates ComplexGameLevel for testing.
     */
    public void setUp()
    {
        level = new ComplexGameLevel(3, 16, 5.0f);
    }

    //-------------------------------------------------------------
    /**
     * Tests if setting up the game level puts the triangles in the proper
     * positions and orientation.
     */
    public void testAddTrianglesToLevel()
    {
        setUp();
        LinkedList<Triangle> list = level.getTriangleList();
        boolean flipped = false;
        float size = list.element().getSize();
        float xPos = size;
        float yPos = level.getViewHeight() / 13;
        for (int i = 0; i < list.size(); i++)
        {
            assertEquals(flipped, list.element().isFlipped());
            assertEquals(xPos, list.element().getPosition().x, 0.0001);
            assertEquals(yPos, list.element().getPosition().y, 0.0001);
            list.remove();
            flipped = !flipped;
            xPos += size;
            if (xPos > level.getViewWidth() - size)
            {
                xPos = size;
                yPos += 2 * size;
            }
        }
    }
}
