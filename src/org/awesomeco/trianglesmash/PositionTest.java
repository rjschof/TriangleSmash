package org.awesomeco.trianglesmash;

import android.graphics.PointF;
import student.TestCase;

public class PositionTest extends TestCase
{
    private Position pos; //the Position object being tested

    //----------------------------------------------------------
    /**
     * Constructor for PositionTest does nothing.
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
        assertEquals(new PointF(5, 5), pos.toPointF());
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
