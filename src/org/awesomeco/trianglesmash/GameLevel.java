package org.awesomeco.trianglesmash;

import sofia.graphics.Color;
import java.util.LinkedList;
import java.util.Queue;
import sofia.graphics.ShapeView;
import java.util.ArrayList;

// -------------------------------------------------------------------------
/**
 *  This class manages information for the game levels.
 *
 *  @author Robert Schofield (rjschof)
 *  @version 2015.04.07
 */

public abstract class GameLevel
{
    private ArrayList<Triangle> triangleList;
    private int levelNum;
    private Paddle paddle;
    private SmashBall smashBall;
    private float viewHeight;
    private float viewWidth;

    // ----------------------------------------------------------
    /**
     * Create a new GameLevel object.
     * @param levelNum number identifier for this level
     * @param height the height of the view
     * @param width the width of the view
     */
    public GameLevel(int levelNum, float width, float height)
    {
        this.levelNum = levelNum;
        triangleList = new ArrayList<Triangle>();
        viewHeight = height;
        viewWidth = width;
        smashBall = new SmashBall(viewWidth / 2, viewHeight / 2,
            viewHeight / 24);
        smashBall.setFillColor(Color.aqua);
        smashBall.setColor(Color.black);
        paddle = new Paddle(viewWidth / 2, viewHeight - 10, viewWidth / 6,
            viewHeight / 20);
        paddle.setPosition(viewWidth / 2, viewHeight - 10);
        paddle.setFillColor(Color.black);
    }

    // ----------------------------------------------------------
    /**
     * Method to add triangles to the level. The settings for triangles will
     * change with each level, which is why this is abstract.
     */
    public abstract void addTrianglesToLevel();

    // ----------------------------------------------------------
    /**
     * Adds a triangle to the list of triangles
     * @param triangle the triangle to add to the list
     */
    public void addTriangle(Triangle triangle)
    {
        triangleList.add(triangle);
    }

    // ----------------------------------------------------------
    /**
     * Gets the number identifier for this level
     * @return the level number for this level
     */
    public int getLevelNum()
    {
        return levelNum;
    }

    /**
     * Returns the paddle that is part of the data model.
     * @return the paddle from this model
     */
    public Paddle getPaddle()
    {
        return paddle;
    }

    // ----------------------------------------------------------
    /**
     * Gets the number of triangles in the list for a level
     * @return the number of triangles in the list
     */
    public int getNumTriangles()
    {
        return triangleList.size();
    }

    /**
     * Returns the SmashBall object that is part of the data model.
     * @return the ball from the model
     */
    public SmashBall getSmashBall()
    {
        return smashBall;
    }

    // ----------------------------------------------------------
    /**
     * Gets the list of triangles for the level
     * @return the list of triangles
     */
    public ArrayList<Triangle> getTriangleList()
    {
        return triangleList;
    }

    /**
     * Returns the height of the view
     * @return the view height
     */
    public float getViewHeight()
    {
        return viewHeight;
    }

    /**
     * Returns the width of the view
     * @return the view width
     */
    public float getViewWidth()
    {
        return viewWidth;
    }

    /**
     * Tells whether or not the game has been won by the player.
     * @return true if the list of triangles is empty, false if otherwise
     */
    public boolean isGameWon()
    {
        return (triangleList.size() == 0);
    }

    // ----------------------------------------------------------
    /**
     * Removes the specified triangle from the list.
     * @param triangle the triangle to remove from the list
     */
    public void removeTriangle(Triangle triangle)
    {
        triangleList.remove(triangle);
    }

}
