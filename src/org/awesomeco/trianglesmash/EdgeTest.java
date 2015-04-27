package org.awesomeco.trianglesmash;

import student.TestCase;

/**
 *  Tests the operation of the Edge class.
 *
 *  @author Adam Zelenka (zadam7)
 *  @author Robert Schofield (rjschof)
 *  @author Lauren Malhotra (laurcm6)
 *  @version 2015.04.26
 */
public class EdgeTest extends TestCase
{
    private Edge edge;

    //-------------------------------------------------------
    /**
     * Constructor for EdgeTest class does nothing.
     */
    public EdgeTest()
    {
        //constructor intentionally left blank
    }

    //-------------------------------------------------------
    /**
     * Instantiates the Edge object for testing.
     */
    public void setUp()
    {
        edge = new Edge(0, -1, 300, -1);
    }

    //-------------------------------------------------------
    /**
     * Tests the edge object for equality when the parameter object is null.
     */
    public void testEqualsNull()
    {
        setUp();
        Edge nullEdge = null;
        assertFalse(edge.equals(nullEdge));
    }

    //-------------------------------------------------------
    /**
     * Tests the edge object for equality when the parameter object is not an
     * instance of Edge.
     */
    public void testEqualsOtherObject()
    {
        setUp();
        String string = "Hello!";
        assertFalse(edge.equals(string));
    }

    //-------------------------------------------------------
    /**
     * Tests the edge object for equality when the parameter Edge object's left
     * coordinate is not equal to the edge's left coordinate.
     */
    public void testEqualsLeftFalse()
    {
        setUp();
        Edge edge2 = new Edge(1, -1, 300, -1);
        assertFalse(edge.equals(edge2));
    }

    //-------------------------------------------------------
    /**
     * Tests the edge object for equality when the parameter Edge object's right
     * coordinate is not equal to the edge's right coordinate.
     */
    public void testEqualsRightFalse()
    {
        setUp();
        Edge edge2 = new Edge(0, -2, 300, -1);
        assertFalse(edge.equals(edge2));
    }

    //-------------------------------------------------------
    /**
     * Tests the edge object for equality when the parameter Edge object's top
     * coordinate is not equal to the edge's top coordinate.
     */
    public void testEqualsTopFalse()
    {
        setUp();
        Edge edge2 = new Edge(0, -1, 200, -1);
        assertFalse(edge.equals(edge2));
    }

    //-------------------------------------------------------
    /**
     * Tests the edge object for equality when the parameter Edge object's
     * bottom coordinate is not equal to the edge's bottom coordinate.
     */
    public void testEqualsBottomFalse()
    {
        setUp();
        Edge edge2 = new Edge(0, -1, 300, -4);
        assertFalse(edge.equals(edge2));
    }

    //-------------------------------------------------------
    /**
     * Tests the edge object for equality when the parameter Edge object's
     * coordinate points are all the same as the edge's coordinates.
     */
    public void testEqualsTrue()
    {
        setUp();
        Edge edge2 = new Edge(0, -1, 300, -1);
        assertTrue(edge.equals(edge2));
    }


}
