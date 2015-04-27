package org.awesomeco.trianglesmash;
import sofia.graphics.RectangleShape;
import student.TestCase;

/**
 * // -------------------------------------------------------------------------
/**
 * Tests implementation of the Paddle Class
 *
 * @author Lauren Malhotra (laurcm6)
 * @author Robert Schofield (rjschof)
 * @author Adam Zelenka (zadam7)
 * @version 2015.04.10
 */
public class PaddleTest extends TestCase
{
    private Paddle testPaddle; // the paddle being tested

    //-----------------------------------------------------------
    /**
     * Constructor for PaddleTest does nothing. It is intentionally left blank.
     */
    public PaddleTest()
    {
        //constructor intentionally left blank
    }

    //-----------------------------------------------------------
    /**
     * Instantiates the Paddle for method testing. This is called at the start
     * of each method test.
     */
    public void setUp()
    {
        testPaddle = new Paddle(100, 100, 100, 20);
    }


    //----------------------------------------------------------
    /**
     * tests the Paddle getPosition method
     */
    public void testGetPosition()
    {
        Position testPoint = new Position(100, 100);
        assertEquals(testPoint, testPaddle.getPosition());
    }

    //-----------------------------------------------------------
    /**
     * Tests the Paddle equals method for null, non-Paddle, equal, and non-equal
     * Paddle objects.
     */
    public void testGetEquals()
    {
        Paddle nullPaddle = null;
        assertFalse(testPaddle.equals(nullPaddle));
        String notAPaddle = "";
        assertFalse(testPaddle.equals(notAPaddle));
        Paddle paddle1 = new Paddle(100, 100, 100, 30);
        assertFalse(testPaddle.equals(paddle1));
        paddle1 = new Paddle(100, 100, 80, 20);
        assertFalse(testPaddle.equals(paddle1));
        paddle1 = new Paddle(100, 100, 80, 30);
        assertFalse(testPaddle.equals(paddle1));
        paddle1 = new Paddle(100, 90, 100, 30);
        assertFalse(testPaddle.equals(paddle1));
        paddle1 = new Paddle(100, 90, 80, 20);
        assertFalse(testPaddle.equals(paddle1));
        paddle1 = new Paddle(100, 90, 80, 30);
        assertFalse(testPaddle.equals(paddle1));
        paddle1 = new Paddle(100, 90, 100, 20);
        assertFalse(testPaddle.equals(paddle1));
        paddle1 = new Paddle(100, 100, 100, 20);
        assertTrue(testPaddle.equals(paddle1));
    }

    //-----------------------------------------------------------
    /**
     * Tests the toRectangleShape method in the Paddle class.
     */
    public void testToRectangleShape()
    {
        setUp();
        RectangleShape rect = testPaddle.toRectangleShape();
        assertEquals(50, rect.getBounds().left, 0.001);
        assertEquals(90, rect.getBounds().top, 0.001);
        assertEquals(150, rect.getBounds().right, 0.001);
        assertEquals(110, rect.getBounds().bottom, 0.001);
    }
}
