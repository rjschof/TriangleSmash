package org.awesomeco.trianglesmash;

import java.util.LinkedList;
import sofia.graphics.Color;

// -------------------------------------------------------------------------
/**
 *  This class tests the methods in the GameLevel class.
 *
 *  @author Robert
 *  @version Apr 9, 2015
 */
public class GameLevelTest extends student.TestCase
{

    private GameLevel gameLevel; //the level being tested

    //-------------------------------------------------------------
    /**
     * Constructor for GameLevelTest does nothing.
     * @param name name of the test
     */
    public GameLevelTest(String name)
    {
        super(name);
    }

    //-------------------------------------------------------------
    /**
     * Instantiates the game level for testing.
     */
    public void setUp()
    {
        gameLevel = new GameLevel(1, 5, 1.0f);
    }

    //-------------------------------------------------------------
    /**
     * Tests the addTriangle() method in GameLevel.
     */
    public void testAddTriangle()
    {
        setUp();
        gameLevel.addTriangle(new Triangle (15, 15, 10, Color.black, true));
        assertEquals(6, gameLevel.getTriangleList().size());
        gameLevel.addTriangle(new Triangle (35, 35, 10, Color.black, true));
        assertEquals(7, gameLevel.getTriangleList().size());
    }

    //-------------------------------------------------------------
    /**
     * Tests the removeTriangleAt() method in GameLevel.
     */
    public void testRemoveTriangleAtIndex()
    {
        setUp();
        Triangle test = new Triangle(15, 15, 10, Color.black, true);
        gameLevel.addTriangle(test);
        assertEquals(6, gameLevel.getTriangleList().size());
        gameLevel.removeTriangle(new Triangle (15, 15, 10, Color.black, true));
        assertEquals(5, gameLevel.getTriangleList().size());
    }

    //--------------------------------------------------------------
    /**
     * Tests the getLevelNum() method in GameLevel.
     */
    public void testGetLevelNum()
    {
        setUp();
        assertEquals(1, gameLevel.getLevelNum());
    }

    //--------------------------------------------------------------
    /**
     * Tests the getSmashBall() method in GameLevel.
     */
    public void testGetSmashBall()
    {
        setUp();
        assertNotNull(gameLevel.getSmashBall());
        Position pos = new Position(gameLevel.getViewWidth() / 2,
            gameLevel.getViewHeight() / 2);
        assertEquals(pos.x(),
            gameLevel.getSmashBall().getPosition().x(), 0.0001);
        assertEquals(pos.y(),
            gameLevel.getSmashBall().getPosition().y(), 0.0001);
    }

    //--------------------------------------------------------------
    /**
     * Tests the getViewHeight() method in GameLevel.
     */
    public void testViewHeight()
    {
        setUp();
        float height = gameLevel.getViewHeight();
        assertEquals(height, gameLevel.getViewHeight(), 0.0001);
    }

    //--------------------------------------------------------------
    /**
     * Tests the getViewWidth() method in GameLevel.
     */
    public void testViewWidth()
    {
        setUp();
        float width = gameLevel.getViewWidth();
        assertEquals(width, gameLevel.getViewWidth(), 0.0001);
    }

    //--------------------------------------------------------------
    /**
     * Tests that setting up the game level adds the triangles in the proper
     * position and orientation.
     */
    public void testAddTrianglesToLevel()
    {
        setUp();
        LinkedList<Triangle> list = gameLevel.getTriangleList();
        float size = list.element().getSize();
        float xPos = size;
        float yPos = gameLevel.getViewHeight() / 13;
        assertEquals(false, list.element().isFlipped());
        for (int i = 0; i < list.size(); i++)
        {

            assertEquals(xPos, list.element().getPosition().x, 0.0001);
            assertEquals(yPos, list.element().getPosition().y, 0.0001);
            list.remove();
            xPos += 2 * size;
            if (xPos > gameLevel.getViewWidth() - size)
            {
                xPos = size;
                yPos += 2 * size;
            }
        }
    }

}
