package org.awesomeco.trianglesmash;

import java.util.LinkedList;
import sofia.util.Observable;
import sofia.graphics.Color;

// -------------------------------------------------------------------------
/**
 *  This class manages information for the game levels.
 *
 * @author Lauren Malhotra (laurcm6)
 * @author Robert Schofield (rjschof)
 * @author Adam Zelenka (zadam7)
 * @version 2015.04.07
 */

public class GameLevel extends Observable
{
    private LinkedList<Triangle> triangleList;
    private int levelNum;
    private boolean gameLost;
    private SmashBall smashBall;
    private int numTriangles;
    private int initialNumTriangles;
    private float viewHeight;
    private float viewWidth;
    private String backgroundImage;

    // ----------------------------------------------------------
    /**
     * Create a new GameLevel object.
     * @param numTriangles the number of triangles to put on the screen
     * @param ballSpeed the speed of the ball
     * @param height the height of the view
     * @param width the width of the view
     */
    public GameLevel(int levelNum, int numTriangles, float ballSpeed)
    {
        this.levelNum = levelNum;
        this.numTriangles = numTriangles;
        this.initialNumTriangles = numTriangles;
        triangleList = new LinkedList<Triangle>();
        viewHeight = SmashGame.getViewHeight();
        viewWidth = SmashGame.getViewWidth();
        smashBall = new SmashBall(viewWidth / 2, viewHeight / 2,
            viewHeight / 24, ballSpeed * (viewWidth / 8),
            ballSpeed * (viewHeight / 12));
        backgroundImage = "NONE";
        gameLost = false;
        addTrianglesToLevel();
    }

    // ----------------------------------------------------------
    /**
     * Create a new GameLevel object.
     * @param levelNum
     * @param numTriangles
     * @param ballSpeed
     * @param width
     * @param height
     * @param background
     */
    public GameLevel(int levelNum, int numTriangles, float ballSpeed,
        String background)
    {
        this(levelNum, numTriangles, ballSpeed);
        backgroundImage = background;
    }

    // ----------------------------------------------------------
    /**
     * Method to add triangles to the level.
     */
    public void addTrianglesToLevel()
    {
        float centerX = getViewWidth() / 2;
        float centerY = getViewHeight() / 13;
        float size = getViewWidth() / 16;
        float distance = getViewWidth() / 7;

        int count = 1;
        float row = 1;
        float s = 1;

        float xPos = centerX;
        float yPos = centerY;
        addTriangle(new Triangle(centerX, yPos, size, Color.red, true));

        while (count < numTriangles)
        {
            xPos = centerX + distance*s;
            if (xPos > viewWidth || xPos < 0)
            {
                row++;
                xPos = centerX;
                s = -1;
                distance = viewWidth / 7;
                yPos = ((row - 1) * size) + (row * centerY);
                addTriangle(new Triangle(centerX, yPos, size, Color.red, true));
            }
            else
            {
                addTriangle(new Triangle(xPos, yPos, size, Color.red, true));
                if (count % 2 == 0)
                {
                    if (row % 2 == 1)
                    {
                        distance *= 2;
                    }
                }
                else if (count % 2 == 1 && row % 2 == 0)
                {
                    distance *= 2;
                }
            }
            s *= -1;
            count++;
        }
        notifyObservers();
    }

    // ----------------------------------------------------------
    /**
     * Adds a triangle to the list of triangles
     * @param triangle the triangle to add to the list
     */
    public void addTriangle(Triangle triangle)
    {
        triangleList.add(triangle);
    }

    /**
     * Tells whether this object is equal to another object. If the other object
     * is an instance of Triangle, then the other object's position is compared
     * to this object's position for equality.
     * @return true if position is same, false if otherwise or not Triangle
     */
    @Override
    public boolean equals(Object other)
    {
        if (other == null)
        {
            return false;
        }
        else if (other instanceof GameLevel)
        {
            return levelNum == ((GameLevel) other).levelNum;
        }
        else
        {
            return false;
        }
    }


    // ----------------------------------------------------------
    /**
     * Gets the string that represents the file name of the image.
     * @return the background image string
     */
    public String getBackground()
    {
        return backgroundImage;
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
     * Gets the initial number of triangles for this level.
     * @return the number of triangles in the list
     */
    public int getInitialNumTriangles()
    {
        return initialNumTriangles;
    }

    // ----------------------------------------------------------
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
    public LinkedList<Triangle> getTriangleList()
    {
        return triangleList;
    }

    // ----------------------------------------------------------
    /**
     * Returns the height of the view
     * @return the view height
     */
    public float getViewHeight()
    {
        return viewHeight;
    }

    // ----------------------------------------------------------
    /**
     * Returns the width of the view
     * @return the view width
     */
    public float getViewWidth()
    {
        return viewWidth;
    }

    /**
     * Resets the game level to its initial state.
     */
    public void reset()
    {
        triangleList = new LinkedList<Triangle>();
        numTriangles = initialNumTriangles;
        addTrianglesToLevel();
    }

    // ----------------------------------------------------------
    /**
     * Removes the triangle at the specified index from the list.
     * @param index of the triangle to remove
     */
    public void removeTriangle(Triangle triangle)
    {
        triangleList.remove(triangle);
        numTriangles--;
        notifyObservers();
    }

    public String toString()
    {
        return "[#" + levelNum + "] Triangle(s): " + numTriangles
            + " Background: " + backgroundImage;
    }
}
