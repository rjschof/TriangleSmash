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
public class SmashGameTest extends TestCase
{
    private SmashGame myGame; //the game model used for testing

    //-------------------------------------------------------
    /**
     * Constructor for SmashGameTest does nothing.
     * @param name the name of the test
     */
    public SmashGameTest(String name)
    {
        super(name);
    }

    //--------------------------------------------------------
    /**
     * Instantiates the game model for testing.
     */
    public void setUp()
    {
        myGame = new SmashGame(300, 300);
    }

    //--------------------------------------------------------
    /**
     * Tests the SmashGame addLevel method, as well as the getLevelList method.
     */
    public void testAddLevel()
    {
        setUp();
        myGame.addLevel(new GameLevel(1, 3, 5.0f));
        assertEquals(1, myGame.getLevelList().size());
        myGame.addLevel(new GameLevel(2, 5, 10.0f));
        assertEquals(2, myGame.getLevelList().size());
    }

    //-------------------------------------------------------
    /**
     * Tests the SmashGame nextLevel method, as well as the getCurrent and
     * isGameComplete method.
     */
    public void testNextLevel()
    {
        setUp();
        GameLevel level1 = new GameLevel(1, 3, 5.0f);
        GameLevel level2 = new GameLevel(2, 5, 10.0f);
        GameLevel level3 = new GameLevel(3, 5, 5.0f);
        myGame.addLevel(level1);
        myGame.addLevel(level2);
        myGame.addLevel(level3);
        assertEquals(level1, myGame.getCurrentLevel());
        myGame.nextLevel();
        assertEquals(level2, myGame.getCurrentLevel());
        myGame.nextLevel();
        assertEquals(level3, myGame.getCurrentLevel());
        assertFalse(myGame.isGameComplete());
        myGame.nextLevel();
        assertEquals(level3, myGame.getCurrentLevel());
        assertTrue(myGame.isGameComplete());
    }

    //---------------------------------------------------------
    /**
     * Tests the SmashGame getEdges method.
     */
    public void testGetEdges()
    {
        setUp();
        Edge[] edges = new Edge[] {
            new Edge(0, -1, 300, -1),
            new Edge(-1, 0, -1, 300),
            new Edge(300, 0, 300, 300),
            new Edge(0, 300, 300, 300)};
        assertEquals(edges, myGame.getEdges());
    }


}
