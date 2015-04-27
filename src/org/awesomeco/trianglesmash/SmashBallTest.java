package org.awesomeco.trianglesmash;
import sofia.graphics.OvalShape;
import student.TestCase;

/**
 * This class tests the SmashBall class.
 *
 * @author Lauren Malhotra (laurcm6)
 * @author Robert Schofield (rjschof)
 * @author Adam Zelenka (zadam7)
 * @version 2015.04.10
 */
public class SmashBallTest extends TestCase
{
    private SmashBall testBall; //the ball being tested

    //-----------------------------------------------------
    /**
     * Constructor for the test class does nothing
     */
    public SmashBallTest()
    {
        //constructor left blank
    }

    //-----------------------------------------------------
    /**
     * Instantiates the test ball for method testing. This is called at the
     * beginning of every method test.
     */
    public void setUp()
    {
        testBall = new SmashBall(100, 100, 20, 5, 7);
    }

    //-----------------------------------------------------
    /**
     * Tests whether the getPosition method for the SmashBall correctly returns
     * the ball's position.
     */
    public void testGetPosition()
    {
        setUp();
        Position testPoint = new Position(100, 100);
        assertEquals(testPoint, testBall.getPosition());
    }

    //------------------------------------------------------
    /**
     * Tests whether the getRadius method for the SmashBall correctly returns
     * the ball's radius.
     */
    public void testGetRadius()
    {
        setUp();
        assertEquals(20.0, testBall.getRadius(), 0.0001);
    }

    //------------------------------------------------------
    /**
     * Tests whether the getRadius method for the SmashBall correctly returns
     * the ball's radius.
     */
    public void testToOvalShape()
    {
        setUp();
        OvalShape ball = testBall.toOvalShape();
        assertEquals(100, ball.getPosition().x, 0.001);
        assertEquals(100, ball.getPosition().y, 0.001);
        assertEquals(80, ball.getBounds().left, 0.001);
        assertEquals(80, ball.getBounds().top, 0.001);
        assertEquals(120, ball.getBounds().right, 0.001);
        assertEquals(120, ball.getBounds().bottom, 0.001);
    }

}
