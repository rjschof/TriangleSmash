package org.awesomeco.trianglesmash;

import sofia.graphics.Color;
import student.TestCase;
import android.graphics.PointF;

// -------------------------------------------------------------------------
/**
 *  Tests implementation of Triangle class.
 *
 *  @author Lauren Malhotra (laurcm6)
 *  @author Robert Schofield (rjschof)
 *  @author Adam Zelenka (zadam7)
 *  @version 2015.04.10
 */

public class TriangleTest extends TestCase
{
    private Triangle testTri; //the Triangle being tested

    /**
     * Constructor for Triangle test class does nothing. It is intentionally
     * left blank.
     */
    public TriangleTest()
    {
        //constructor intentionally left blank
    }

    /**
     * Initializes the triangle being tested. Is called at the start of each
     * method test to create a fresh instance of the Triangle.
     */
    public void setUp()
    {
        testTri = new Triangle(100, 100, 30, Color.red);
    }

    /**
     * Tests the Triangle getFillColor method
     */
    public void testGetFillColor()
    {
        setUp();
        assertEquals(Color.red, testTri.getFillColor());
    }

    /**
     * Tests the Triangle getPosition method.
     */
    public void testGetPosition()
    {
        setUp();
        Position testPoint = new Position(100, 100);
        assertEquals(testPoint, testTri.getPosition());
    }

    /**
     * Tests the Triangle getSize method.
     */
    public void testGetSize()
    {
        setUp();
        assertEquals(30, testTri.getSize(), 0.0001);
    }

    /**
     * Tests the Triangle equals method for the null case, compared objects
     * that are not Triangles, and Triangles that are and are not at the same
     * position as this one.
     */
    public void testEquals()
    {
        setUp();
        String other = "hello";
        assertFalse(testTri.equals(other));
        Triangle other2 = null;
        assertFalse(testTri.equals(other2));
        Triangle other3 = new Triangle(100, 90, 30, Color.red);
        assertFalse(testTri.equals(other3));
        Triangle other4 = new Triangle(100, 100, 20, Color.red);
        assertTrue(testTri.equals(other4));
    }
}
