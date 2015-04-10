package org.awesomeco.trianglesmash;

import student.AndroidTestCase;

// -------------------------------------------------------------------------
/**
 * Tests for the TriangleSmashScreen class.
 *
 * @author Lauren Malhotra (laurcm6)
 * @author Robert Schofield (rjschof)
 * @author Adam Zelenka (zadam7)
 * @version 2015.03.23
 */

public class TriangleSmashScreenTest
    extends AndroidTestCase<TriangleSmashScreen>
{

    private TriangleSmashScreen smashScreen;

    /**
     * Constructor for the TriangleSmashScreenTest class.
     */
    public TriangleSmashScreenTest(String activityClassName)
    {
        super(activityClassName);
    }

    /**
     * Sets up the test case.
     */
    public void setUp()
    {
        smashScreen = this.getScreen();
    }
}
