package org.awesomeco.trianglesmash;

import android.graphics.PointF;
import student.TestCase;
/**
 * // -------------------------------------------------------------------------
/**
 *  Write a one-sentence summary of your class here.
 *  Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 *
 *  @author Adam Zelenka (zadam7)
 *  @author Robert Schofield (rjschof)
 *  @author Lauren Malhotra (laurcm6)
 *  @version 2015.04.27
 */
public class PositionTest extends TestCase
{
    private Position pos; //the Position object being tested

    //----------------------------------------------------------
    /**
     * Constructor for PositionTest does nothing.
     * @param name name of the test
     */
    public PositionTest(String name)
    {
        super(name);
    }

    //----------------------------------------------------------
    /**
     * Instantiates the Position for testing.
     */
    public void setUp()
    {
        pos = new Position(5, 5);
    }

    //----------------------------------------------------------
    /**
     * Tests the Position PointF method.
     */
    public void testToPointF()
    {
        Position position = new Position(5, 6);
        PointF point = new PointF(5, 6);
        assertEquals(point.x, position.x(), 0.01);
        assertEquals(point.y, position.y(), 0.01);
    }

    //----------------------------------------------------------
    /**
     * Tests the Position x method.
     */
    public void testX()
    {
        assertEquals(5, pos.x(), 0.0001);
    }

    //----------------------------------------------------------
    /**
     * Tests the Position y method.
     */
    public void testY()
    {
        assertEquals(5, pos.y(), 0.0001);
    }

    //----------------------------------------------------------
    /**
     * Tests the equals method for a null object, a non-Position object,
     * unequal Positions, and an equal Position.
     */
    public void testEquals()
    {
        String other = new String("");
        assertFalse(pos.equals(other));
        Position nullPos = null;
        assertFalse(pos.equals(nullPos));
        Position otherPos = new Position(4, 4);
        assertFalse(pos.equals(otherPos));
        otherPos = new Position(5, 4);
        assertFalse(pos.equals(otherPos));
        otherPos = new Position(4, 5);
        assertFalse(pos.equals(otherPos));
        otherPos = new Position(5, 5);
        assertTrue(pos.equals(otherPos));
    }

}
