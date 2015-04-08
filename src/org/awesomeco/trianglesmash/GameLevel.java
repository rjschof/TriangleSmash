package org.awesomeco.trianglesmash;

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



    private Queue<Bonus> powers;

    // ----------------------------------------------------------
    /**
     * Create a new GameLevel object.
     * @param levelNum number identifier for this level
     */
    public GameLevel(int levelNum)
    {
        this.levelNum = levelNum;
        triangleList = new ArrayList<Triangle>();
        powers = new LinkedList<Bonus>();
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

    // ----------------------------------------------------------
    /**
     * Gets the number of triangles in the list for a level
     * @return the number of triangles in the list
     */
    public int getNumTriangles()
    {
        return triangleList.size();
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
