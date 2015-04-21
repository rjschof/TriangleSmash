package org.awesomeco.trianglesmash;
import android.graphics.PointF;

import student.TestCase;

/**
 * // -------------------------------------------------------------------------
/**
 * Tests implementation of the Paddle Class
 *
 *  @author Lauren Malhotra (laurcm6)
 *  @author Robert Schofield (rjschof)
 *  @author Adam Zelenka (zadam7)
 *  @version 2015.04.10
 */
public class PaddleTest extends TestCase
{
    private Paddle testPaddle; // the paddle being tested

    /**
     * Constructor for PaddleTest does nothing. It is intentionally left blank.
     */
    public PaddleTest()
    {
        //constructor intentionally left blank
    }

    /**
     * Instantiates the Paddle for method testing. This is called at the start
     * of each method test.
     */
    public void setUp()
    {
        testPaddle = new Paddle(100, 100, 100, 20);
    }

    /**
     * tests the Paddle getHeight method
     */
    public void testGetHeight()
    {
        setUp();
        assertEquals(20, testPaddle.getHeight(), 0.0001);
    }

    /**
     * Tests the Paddle getWidth method
     */
    public void testGetWidth()
    {
        setUp();
        assertEquals(100, testPaddle.getWidth(), 0.0001);
    }

    /**
     * tests the Paddle getPosition method
     */
    public void testGetPosition()
    {
        Position testPoint = new Position(100, 100);
        assertEquals(testPoint, testPaddle.getPosition());
    }

    /**
     * tests the Paddle setWidth method
     */
    public void testSetWidth()
    {
        setUp();
        testPaddle.setWidth(80);
        assertEquals(80, testPaddle.getWidth(), 0.0001);
    }

    /**
     * tests the Paddle setHeight method
     */
    public void testSetHeight()
    {
        setUp();
        testPaddle.setHeight(20);
        assertEquals(20, testPaddle.getHeight(), 0.0001);
    }

}
