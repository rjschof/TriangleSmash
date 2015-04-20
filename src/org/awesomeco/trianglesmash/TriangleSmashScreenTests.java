package org.awesomeco.trianglesmash;

import student.AndroidTestCase;

// -------------------------------------------------------------------------
/**
 *  Write a one-sentence summary of your class here.
 *  Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 *
 *  @author Robert
 *  @version Apr 17, 2015
 */

public class TriangleSmashScreenTests
    extends AndroidTestCase<TriangleSmashScreen>
{

    // ----------------------------------------------------------
    /**
     * Create a new TriangleSmashScreenTests object.
     */
    public TriangleSmashScreenTests()
    {
        super(TriangleSmashScreen.class);
    }


    // ----------------------------------------------------------
    public void setUp()
    {

    }

    public void testCollisionTriangleBall()
    {
        getScreen().getShapeView();
    }
}
