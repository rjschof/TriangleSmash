package org.awesomeco.trianglesmash;

import android.graphics.PointF;
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

    private GameLevel gameLevel;

    public GameLevelTest(String name)
    {
        super(name);
    }

    public void setUp()
    {
        gameLevel = new GameLevel(1, 5, 1.0f);
    }

    /**
     * Tests the addTriangle() method in GameLevel.
     */
    public void testAddTriangle()
    {
        setUp();
        gameLevel.addTriangle(new Triangle (15, 15, 10, Color.black, true));
        assertEquals(1, gameLevel.getTriangleList().size());
        gameLevel.addTriangle(new Triangle (35, 35, 10, Color.black, true));
        assertEquals(2, gameLevel.getTriangleList().size());
    }

    /**
     * Tests the removeTriangleAt() method in GameLevel.
     */
    public void testRemoveTriangleAtIndex()
    {
        setUp();
        Triangle test = new Triangle(15, 15, 10, Color.black, true);
        gameLevel.addTriangle(test);
        assertEquals(1, gameLevel.getTriangleList().size());
        gameLevel.removeTriangle(new Triangle (15, 15, 10, Color.black, true));
        assertEquals(0, gameLevel.getTriangleList().size());
    }

    /**
     * Tests the getLevelNum() method in GameLevel.
     */
    public void testGetLevelNum()
    {
        setUp();
        assertEquals(1, gameLevel.getLevelNum());
    }

    /**
     * Tests the getSmashBall() method in GameLevel.
     */
    public void testGetSmashBall()
    {
        setUp();
        assertNotNull(gameLevel.getSmashBall());
        assertEquals(new Position(150, 150),
            gameLevel.getSmashBall().getPosition());
    }


    /**
     * Tests the getViewHeight() method in GameLevel.
     */
    public void testViewHeight()
    {
        setUp();
        assertEquals(300f, gameLevel.getViewHeight(), 0.001);
    }

    /**
     * Tests the getViewWidth() method in GameLevel.
     */
    public void testViewWidth()
    {
        setUp();
        assertEquals(300f, gameLevel.getViewWidth(), 0.001);
    }
}
