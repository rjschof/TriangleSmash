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
        gameLevel = new GameLevel(1, 5, 300, 300);
    }

    /**
     * Tests the addTriangle() method in GameLevel.
     */
    public void testAddTriangle()
    {
        setUp();
        gameLevel.addTriangle(new Triangle (15, 15, 10, Color.black));
        assertFalse(gameLevel.isGameWon());
        assertEquals(1, gameLevel.getTriangleList().size());
        gameLevel.addTriangle(new Triangle (35, 35, 10, Color.black));
        assertEquals(2, gameLevel.getTriangleList().size());
    }

    /**
     * Tests the removeTriangleAt() method in GameLevel.
     */
    public void testRemoveTriangleAtIndex()
    {
        setUp();
        Triangle test = new Triangle (15, 15, 10, Color.black);
        gameLevel.addTriangle(test);
        assertFalse(gameLevel.isGameWon());
        assertEquals(1, gameLevel.getTriangleList().size());
        gameLevel.removeTriangleAt(0);
        assertEquals(0, gameLevel.getTriangleList().size());
        assertTrue(gameLevel.isGameWon());
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
     * Tests the getPaddle() method in GameLevel.
     */
    public void testGetPaddle()
    {
        setUp();
        assertNotNull(gameLevel.getPaddle());
        assertEquals(new Position(150, 290),
            gameLevel.getPaddle().getPosition());
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

    /**
     * Tests the isGameWon() method in GameLevel.
     */
    public void testIsGameWon()
    {
        setUp();
        Triangle test = new Triangle (15, 15, 10, Color.black);
        gameLevel.addTriangle(test);
        assertFalse(gameLevel.isGameWon());
        gameLevel.removeTriangleAt(0);
        assertTrue(gameLevel.isGameWon());
    }
}
