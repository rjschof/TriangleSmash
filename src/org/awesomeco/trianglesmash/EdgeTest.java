package org.awesomeco.trianglesmash;

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
     * Tests the Edge equals method with null, non-Edge, equal and unequal
     * Edge objects.
     */
    public void testEquals()
    {
        setUp();
        Edge nullEdge = null;
        assertFalse(edge.equals(nullEdge));
        String notAnEdge = "";
        assertFalse(edge.equals(notAnEdge));
        Edge edge1 = new Edge(0, 300, 300, -1);
        assertFalse(edge.equals(edge1));
        edge1 = new Edge(0, -1, 300, -1);
        assertTrue(edge.equals(edge1));
    }

}
