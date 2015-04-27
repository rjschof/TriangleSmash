package org.awesomeco.trianglesmash;

import student.TestCase;
/**
 * // -------------------------------------------------------------------------
/**
 *  Tests methods of the SmashGame model class.
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
     * Tests the SmashGame addLevel method, as well as the getLevelList method,
     * toString method.
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
        assertEquals(edges[0], myGame.getEdges()[0]);
        assertEquals(edges[1], myGame.getEdges()[1]);
        assertEquals(edges[2], myGame.getEdges()[2]);
        assertEquals(edges[3], myGame.getEdges()[3]);

    }

    //---------------------------------------------------------
    /**
     * Tests the SmashGame isGameLost and gameLost methods.
     */
    public void testIsGameLost()
    {
        setUp();
        myGame.addLevel(new GameLevel(1, 3, 5.0f));
        assertFalse(myGame.isGameLost());
        myGame.gameLost();
        assertTrue(myGame.isGameLost());
    }

    //---------------------------------------------------------
    /**
     * Tests the SmashGame getViewHeight and getViewWidth methods.
     */
    public void testGetView()
    {
        setUp();
        assertEquals(300.0, SmashGame.getViewHeight(), 0.0001);
        assertEquals(300.0, SmashGame.getViewWidth(), 0.0001);
    }

    //---------------------------------------------------------
    /**
     * Tests the SmashGame getPaddle method.
     */
    public void testGetPaddle()
    {
        setUp();
        Paddle paddle = new Paddle(SmashGame.getViewWidth() / 2,
            SmashGame.getViewHeight() - 10, SmashGame.getViewWidth() / 6,
            SmashGame.getViewHeight() / 20);
        assertEquals(paddle, myGame.getPaddle());
    }

    //----------------------------------------------------------
    /**
     * Tests the SmashGame isGameWon method.
     */
    public void testIsGameWon()
    {
        setUp();
        myGame.addLevel(new GameLevel(1, 1, 5.0f));
        assertFalse(myGame.isGameWon());
        myGame.getCurrentLevel().removeTriangle(
            myGame.getCurrentLevel().getTriangleList().getFirst());
        assertTrue(myGame.isGameWon());
    }

    //-----------------------------------------------------------
    /**
     * Tests the SmashGame resetLevel method for case where ther user loses the
     * game at the current level.
     */
    public void testResetLevelGameLost()
    {
        setUp();
        GameLevel level1 = new GameLevel(1, 3, 5.0f);
        myGame.addLevel(level1);
        myGame.gameLost();
        assertTrue(myGame.isGameLost());
        myGame.resetLevel();
        assertFalse(myGame.isGameLost());
    }

  //-----------------------------------------------------------
    /**
     * Tests the SmashGame resetLevel method for case where a triangle has been
     * removed from the level but the game is still in play.
     */
    public void testResetLevelTriangleRemoved()
    {
        setUp();
        GameLevel level1 = new GameLevel(1, 3, 5.0f);
        myGame.addLevel(level1);
        myGame.getCurrentLevel().removeTriangle(
            myGame.getCurrentLevel().getTriangleList().getFirst());
        assertEquals(2, myGame.getCurrentLevel().getNumTriangles());
        myGame.resetLevel();
        assertEquals(3, myGame.getCurrentLevel().getNumTriangles());
    }

    //----------------------------------------------------------
    /**
     * Tests the SmashGame movePaddle method.
     */
    public void testMovePaddle()
    {
        setUp();
        myGame.movePaddle(30);
        Position testPos = new Position(30, SmashGame.getViewHeight() - 10);
        assertEquals(testPos, myGame.getPaddle().getPosition());
    }

    //-----------------------------------------------------------
    /**
     * Tests the SmashGame triangleCollided method.
     */
    public void testTriangleCollided()
    {
        setUp();
        GameLevel level1 = new GameLevel(1, 3, 5.0f);
        myGame.addLevel(level1);
        myGame.triangleCollided(
            myGame.getCurrentLevel().getTriangleList().getFirst());
        assertEquals(2, myGame.getCurrentLevel().getNumTriangles());
    }

    //-----------------------------------------------------------
    /**
     * Tests the SmashGame startOver method.
     */
    public void testStartOver()
    {
        setUp();
        GameLevel level1 = new GameLevel(1, 3, 5.0f);
        GameLevel level2 = new GameLevel(2, 10, 5.0f);
        GameLevel level3 = new GameLevel(3, 10, 10.0f);
        myGame.addLevel(level1);
        myGame.addLevel(level2);
        myGame.addLevel(level3);
        myGame.getCurrentLevel().removeTriangle(
            myGame.getCurrentLevel().getTriangleList().getFirst());
        myGame.nextLevel();
        myGame.getCurrentLevel().removeTriangle(
            myGame.getCurrentLevel().getTriangleList().getFirst());
        myGame.nextLevel();
        myGame.getCurrentLevel().removeTriangle(
            myGame.getCurrentLevel().getTriangleList().getFirst());

        myGame.startOver();
        assertEquals(3, myGame.getLevelList().get(0).getNumTriangles());
        assertEquals(10, myGame.getLevelList().get(1).getNumTriangles());
        assertEquals(10, myGame.getLevelList().get(2).getNumTriangles());
    }

    //-------------------------------------------------------------
    /**
     * Tests the SmashGame goToLevel method.
     */
    public void testGoToLevel()
    {
        setUp();
        GameLevel level1 = new GameLevel(1, 3, 5.0f);
        GameLevel level2 = new GameLevel(2, 10, 5.0f);
        GameLevel level3 = new GameLevel(3, 10, 10.0f);
        myGame.addLevel(level1);
        myGame.addLevel(level2);
        myGame.addLevel(level3);
        assertEquals(1, myGame.getCurrentLevel().getLevelNum());
        myGame.goToLevel(level3);
        assertEquals(3, myGame.getCurrentLevel().getLevelNum());
    }


}
