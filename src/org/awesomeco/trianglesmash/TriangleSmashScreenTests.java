package org.awesomeco.trianglesmash;

import android.widget.Button;
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

    private Button gameButton;

    // ----------------------------------------------------------
    /**
     * Create a new TriangleSmashScreenTests object.
     */
    public TriangleSmashScreenTests()
    {
        super(TriangleSmashScreen.class);
    }

    public void setUp()
    {
        GameLevel level = new GameLevel(9001, 1, 1.0f);
        getScreen().getSmashGame().addLevel(level);
        getScreen().goToLevel(level);
    }

    public void setUpFullRow()
    {
        GameLevel level = new GameLevel(9001, 8, 1.0f);
        getScreen().getSmashGame().addLevel(level);
        getScreen().goToLevel(level);
    }

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

    public void testOnTouchDown()
    {
        setUp();
        touchDown(getScreen().getShapeView(), 30, 40);
        assertEquals(30, getScreen().getPaddle().getPosition().x, 0.001);
        assertEquals(getScreen().getShapeView().getHeight() - 10,
            getScreen().getPaddle().getPosition().y, 0.001);
    }

    public void testOnTouchMove()
    {
        setUp();
        touchDown(getScreen().getShapeView(), 30, 40);
        assertEquals(30, getScreen().getPaddle().getPosition().x, 0.001);
        assertEquals(getScreen().getShapeView().getHeight() - 10,
            getScreen().getPaddle().getPosition().y, 0.001);
        touchMove(Float.valueOf(150), 60f);
        touchUp();
        assertEquals(150, getScreen().getPaddle().getPosition().x, 10);
        assertEquals(getScreen().getShapeView().getHeight() - 10,
            getScreen().getPaddle().getPosition().y, 0.001);
    }

    public void testGameButtonStart()
    {
        assertTrue(getScreen().getSmashBall().getLinearVelocity().x == 0);
        assertTrue(getScreen().getSmashBall().getLinearVelocity().y == 0);
        setUp();
        click(gameButton);
        assertTrue(getScreen().getSmashBall().getLinearVelocity().x > 0);
        assertTrue(getScreen().getSmashBall().getLinearVelocity().y > 0);
    }

    public void testGameButtonResetInGame()
    {
        assertTrue(getScreen().getSmashBall().getLinearVelocity().x == 0);
        assertTrue(getScreen().getSmashBall().getLinearVelocity().y == 0);
        setUpFullRow();
        click(gameButton);
        getScreen().getSmashBall().setLinearVelocity(0, 0);
        getScreen().getSmashBall().setPosition(
            getScreen().getShapeView().getWidth() / 2 - 10, 55);
        getScreen().getSmashBall().setLinearVelocity(0, -500);
        try
        {
            Thread.sleep(1000);
            assertEquals(7, getScreen().getSmashGame().getCurrentLevel()
                .getNumTriangles());
            Thread.sleep(1500);
            click(gameButton);
            assertEquals(8, getScreen().getSmashGame().getCurrentLevel()
                .getNumTriangles());
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public void testGameButtonStartOver()
    {
        setUp();
        GameLevel level = getScreen().getSmashGame().getLevelList().getLast();
        getScreen().goToLevel(level);
        click(gameButton);
        for (GameLevel l: getScreen().getSmashGame().getLevelList())
        {
            System.out.println(l);
        }
        getScreen().getSmashBall().setLinearVelocity(0, 0);
        getScreen().getSmashBall().setPosition(30, 50);
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
