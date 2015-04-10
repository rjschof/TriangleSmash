package org.awesomeco.trianglesmash;
import student.TestCase;
import android.graphics.PointF;

/**
 * // -------------------------------------------------------------------------
/**
 *  Write a one-sentence summary of your class here.
 *  Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 *
 * @author Lauren Malhotra (laurcm6)
 * @author Robert Schofield (rjschof)
 * @author Adam Zelenka (zadam7)
 * @version 2015.04.10
 */
public class SmashBallTest extends TestCase
{
    private SmashBall testBall; //the ball being tested

    /**
     * Constructor for the test class does nothing
     */
    public SmashBallTest()
    {
        //constructor left blank
    }

    /**
     * Instantiates the test ball for method testing. This is called at the
     * beginning of every method test.
     */
    public void setUp()
    {
        testBall = new SmashBall(100, 100, 20);
    }

    /**
     * Tests whether the getPosition method for the SmashBall correctly returns
     * the ball's position.
     */
    public void testGetPosition()
    {
        setUp();
        PointF testPoint = new PointF(100, 100);
        assertEquals(testPoint, testBall.getPosition());
    }

    /**
     * Tests whether the getRadius method for the SmashBall correctly returns
     * the ball's radius.
     */
    public void testGetRadius()
    {
        setUp();
        assertEquals(20.0, testBall.getRadius(), 0.0001);
    }

}
