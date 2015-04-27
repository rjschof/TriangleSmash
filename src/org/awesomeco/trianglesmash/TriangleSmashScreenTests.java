package org.awesomeco.trianglesmash;

import android.widget.Button;
import student.AndroidTestCase;

/**
 * This class tests the TriangleSmashScreen class. All of the conditions that
 * may occur on the screen are tested.
 *
 * @author Lauren Malhotra (laurcm6)
 * @author Robert Schofield (rjschof)
 * @author Adam Zelenka (zadam7)
 * @version 2015.04.22
 */

public class TriangleSmashScreenTests
    extends AndroidTestCase<TriangleSmashScreen>
{

    private Button gameButton;

    // ----------------------------------------------------------
    /**
     * Create a new TriangleSmashScreenTests object.
     */
    public TriangleSmashScreenTests()
    {
        super(TriangleSmashScreen.class);
    }

    // ----------------------------------------------------------
    /**
     * This method sets up the game with a level that has only one triangle.
     */
    public void setUp()
    {
        GameLevel level = new GameLevel(9001, 1, 1.0f);
        getScreen().getSmashGame().addLevel(level);
        getScreen().goToLevel(level);
    }

    // ----------------------------------------------------------
    /**
     * This method sets up the game with a level that has a full row of
     * triangles.
     */
    public void setUpFullRow()
    {
        GameLevel level = new GameLevel(9001, 8, 1.0f);
        getScreen().getSmashGame().addLevel(level);
        getScreen().goToLevel(level);
    }

    // ----------------------------------------------------------
    /**
     * This method tests the operation of the screen when the ball collides with
     * a triangle object.
     */
    public void testCollisionTriangleBall()
    {
        try
        {
            GameLevel level = new GameLevel(9001, 8, 1.0f);
            getScreen().getSmashGame().addLevel(level);
            getScreen().goToLevel(level);
            getScreen().getSmashBall().setPosition(
                getScreen().getShapeView().getWidth() / 2 - 10, 55);
            getScreen().getSmashBall().setLinearVelocity(0, -500);
            Thread.sleep(1500);
            assertEquals(7, getScreen().getSmashGame().getCurrentLevel()
                .getTriangleList().size());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            testCollisionTriangleBall();
        }
    }

    // ----------------------------------------------------------
    /**
     * This method tests the operation of the screen when the ball collides with
     * the right edge object.
     */
    public void testCollisionBallRightEdge()
    {
        try {
            setUp();
            getScreen().getSmashBall().setPosition(
                getScreen().getWidth() - 40, getScreen().getHeight() / 2);
            getScreen().getSmashBall().setLinearVelocity(-25, 0);
            Thread.sleep(1500);
            assertEquals(-25, getScreen().getSmashBall().getLinearVelocity().x,
                0.001);
        } catch (Exception e)
        {
            e.printStackTrace();
            testCollisionBallRightEdge();
        }
    }

    // ----------------------------------------------------------
    /**
     * This method tests the operation of the screen when the ball collides with
     * the left edge object.
     */
    public void testCollisionBallLeftEdge()
    {
        setUp();
        try {
            getScreen().getSmashBall().setPosition(
                40, getScreen().getHeight() / 2);
            getScreen().getSmashBall().setLinearVelocity(-25, 0);
            Thread.sleep(1500);
            assertEquals(25, getScreen().getSmashBall().getLinearVelocity().x,
                0.001);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            testCollisionBallLeftEdge();
        }
    }

    // ----------------------------------------------------------
    /**
     * This method tests the operation of the screen when the ball collides with
     * the top edge object.
     */
    public void testCollisionBallTopEdge()
    {
        setUp();
        getScreen().getSmashBall().setPosition(100, 40);
        getScreen().getSmashBall().setLinearVelocity(0, -25);
        try
        {
            Thread.sleep(1500);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        assertEquals(25, getScreen().getSmashBall().getLinearVelocity().y,
            0.001);
    }

    // ----------------------------------------------------------
    /**
     * This method tests the operation of the screen when the ball collides with
     * the bottom edge object.
     */
    public void testCollisionBallBottomEdge()
    {
        try
        {
            setUp();
            getScreen().getSmashBall().setPosition(40,
                getScreen().getShapeView().getHeight() - 40);
            getScreen().getSmashBall().setLinearVelocity(0, 25);
            Thread.sleep(1000);
            assertEquals(0, getScreen().getSmashBall().getLinearVelocity().x,
                0.001);
            assertEquals(0, getScreen().getSmashBall().getLinearVelocity().y,
                0.001);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // ----------------------------------------------------------
    /**
     * This method tests the operation of the screen when the user touches their
     * finger down on the screen. The paddle should reposition itself to match
     * with the x-coordinate of the finger on the screen.
     */
    public void testOnTouchDown()
    {
        setUp();
        touchDown(getScreen().getShapeView(), 30, 40);
        assertEquals(30, getScreen().getPaddle().getPosition().x, 0.001);
        assertEquals(getScreen().getShapeView().getHeight() - 10,
            getScreen().getPaddle().getPosition().y, 0.001);
    }

    // ----------------------------------------------------------
    /**
     * This method tests the operation of the screen when the user moves their
     * finger across the screen. The paddle should reposition itself to match
     * with the x-coordinate of the finger on the screen.
     */
    public void testOnTouchMove()
    {
        setUp();
        touchDown(getScreen().getShapeView(), 30, 40);
        assertEquals(30, getScreen().getPaddle().getPosition().x, 0.001);
        assertEquals(getScreen().getShapeView().getHeight() - 10,
            getScreen().getPaddle().getPosition().y, 0.001);
        touchMove(Float.valueOf(150), 60f);
        touchUp();
        assertEquals(150, getScreen().getPaddle().getPosition().x, 20);
        assertEquals(getScreen().getShapeView().getHeight() - 10,
            getScreen().getPaddle().getPosition().y, 0.001);
    }

    // ----------------------------------------------------------
    /**
     * This method tests the operation of the game button when the game has not
     * yet been started.
     */
    public void testGameButtonStart()
    {
        assertTrue(getScreen().getSmashBall().getLinearVelocity().x == 0);
        assertTrue(getScreen().getSmashBall().getLinearVelocity().y == 0);
        setUp();
        click(gameButton);
        assertTrue(getScreen().getSmashBall().getLinearVelocity().x > 0);
        assertTrue(getScreen().getSmashBall().getLinearVelocity().y > 0);
    }

    // ----------------------------------------------------------
    /**
     * Test method for the game button when the game has been started and the
     * user desires to reset the game state.
     */
    public void testGameButtonResetInGame()
    {
        assertTrue(getScreen().getSmashBall().getLinearVelocity().x == 0);
        assertTrue(getScreen().getSmashBall().getLinearVelocity().y == 0);
        setUpFullRow();
        click(gameButton);
        try
        {
            Thread.sleep(500);
            assertEquals(8, getScreen().getSmashGame().getCurrentLevel()
                .getNumTriangles());
            getScreen().getSmashBall().setPosition(135, 150);
            getScreen().getSmashBall().setLinearVelocity(0, -500);
            Thread.sleep(3000);
            assertEquals(7, getScreen().getSmashGame().getCurrentLevel()
                .getNumTriangles());
            click(gameButton);
            assertEquals(8, getScreen().getSmashGame().getCurrentLevel()
                .getNumTriangles());
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    // ----------------------------------------------------------
    /**
     * Test method for the game button when the game needs to be started from
     * the very beginning. This method tests the operation of the screen when
     * the last level in the game was completed.
     */
    public void testGameButtonStartOver()
    {
        GameLevel level = getScreen().getSmashGame().getLevelList().getLast();
        getScreen().goToLevel(level);
        click(gameButton);
        getScreen().getSmashBall().setLinearVelocity(0, 0);
        getScreen().getSmashBall().setPosition(30, 60);
        getScreen().getSmashBall().setLinearVelocity(0, -500);
        try
        {
            Thread.sleep(1000);
            assertEquals(0, getScreen().getSmashGame().getCurrentLevel()
                .getNumTriangles());
            click(gameButton);
            Thread.sleep(1000);
            assertEquals(1, getScreen().getSmashGame().getCurrentLevel()
                .getLevelNum());
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

}
