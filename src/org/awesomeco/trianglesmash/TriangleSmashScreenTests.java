package org.awesomeco.trianglesmash;

import sofia.util.Timer;
import sofia.graphics.Shape;
import sofia.graphics.OvalShape;
import sofia.graphics.Color;
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

    private OvalShape smashBall;

    // ----------------------------------------------------------
    /**
     * Create a new TriangleSmashScreenTests object.
     */
    public TriangleSmashScreenTests()
    {
        super(TriangleSmashScreen.class);
    }

    public void testCollisionTriangleBall()
    {
        assertEquals(5, getScreen().getGameLevel().getTriangleList().size());
        getScreen().getSmashBall().setPosition(
            getScreen().getShapeView().getWidth() / 2, 55);
        getScreen().getSmashBall().setLinearVelocity(0, -500);
        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        assertEquals(4, getScreen().getGameLevel().getTriangleList().size());
    }

    public void testCollisionBallRightEdge()
    {
        getScreen().getSmashBall().setPosition(
            getScreen().getWidth() - 40, getScreen().getHeight() / 2);
        getScreen().getSmashBall().setLinearVelocity(-25, 0);
        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        assertEquals(-25, getScreen().getSmashBall().getLinearVelocity().x,
            0.001);
    }

    public void testCollisionBallLeftEdge()
    {
        getScreen().getSmashBall().setPosition(
            40, getScreen().getHeight() / 2);
        getScreen().getSmashBall().setLinearVelocity(-25, 0);
        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        assertEquals(25, getScreen().getSmashBall().getLinearVelocity().x,
            0.001);
    }

    public void testCollisionBallTopEdge()
    {
        getScreen().getSmashBall().setPosition(25, 40);
        getScreen().getSmashBall().setLinearVelocity(0, -25);
        try
        {
            Thread.sleep(1000);
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
        getScreen().getSmashBall().setPosition(40,
            getScreen().getShapeView().getHeight() - 40);
        getScreen().getSmashBall().setLinearVelocity(0, 25);
        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        assertEquals(0, getScreen().getSmashBall().getLinearVelocity().x,
            0.001);
        assertEquals(0, getScreen().getSmashBall().getLinearVelocity().y,
            0.001);
    }

    public void testOnTouchDown()
    {
        touchDown(getScreen().getShapeView(), 30, 40);
        assertEquals(30, getScreen().getPaddle().getPosition().x, 0.001);
        assertEquals(getScreen().getShapeView().getHeight() - 10,
            getScreen().getPaddle().getPosition().y, 0.001);
    }

    public void testOnTouchMove()
    {
        touchDown(getScreen().getShapeView(), 30, 40);
        assertEquals(30, getScreen().getPaddle().getPosition().x, 0.001);
        assertEquals(getScreen().getShapeView().getHeight() - 10,
            getScreen().getPaddle().getPosition().y, 0.001);
        touchMove(Float.valueOf(150), 60f);
        touchUp();
        float x = getScreen().getPaddle().getPosition().x;
        assertEquals(150, x, 15);
        assertEquals(getScreen().getShapeView().getHeight() - 10,
            getScreen().getPaddle().getPosition().y, 0.001);
    }
}
