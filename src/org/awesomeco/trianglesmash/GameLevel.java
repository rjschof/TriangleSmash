package org.awesomeco.trianglesmash;

import sofia.util.Observable;
import sofia.graphics.Color;
import java.util.ArrayList;

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
    private ArrayList<Triangle> triangleList;
    private int levelNum;
    private Paddle paddle;
    private boolean gameLost;
    private SmashBall smashBall;
    private int numTriangles;
    private float viewHeight;
    private float viewWidth;
    private String backgroundImage;

    // ----------------------------------------------------------
    /**
     * Create a new GameLevel object.
     * @param levelNum number identifier for this level
     * @param numTriangles the number of triangles to put on the screen
     * @param height the height of the view
     * @param width the width of the view
     */
    public GameLevel(int levelNum, int numTriangles, float width, float height)
    {
        this.levelNum = levelNum;
        this.numTriangles = numTriangles;
        triangleList = new ArrayList<Triangle>();
        viewHeight = height;
        viewWidth = width;
        smashBall = new SmashBall(viewWidth / 2, viewHeight / 2,
            viewHeight / 24);
        paddle = new Paddle(viewWidth / 2, viewHeight - 10, viewWidth / 6,
            viewHeight / 20);
        backgroundImage = "NONE";
        gameLost = false;
    }

    // ----------------------------------------------------------
    // ----------------------------------------------------------
    /**
     * Create a new GameLevel object.
     * @param levelNum
     * @param numTriangles
     * @param width
     * @param height
     * @param background
     */
    public GameLevel(int levelNum, int numTriangles, float width, float height,
        String background)
    {
        this(levelNum, numTriangles, width, height);
        backgroundImage = background;
    }

    // ----------------------------------------------------------
    /**
     * Method to add triangles to the level.
     *
     * FIXME: Change the coordinate points to scale to the screen based on the
     * size of the device.
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
        addTriangle(new Triangle(centerX, yPos, size, Color.red));

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
                addTriangle(new Triangle(centerX, yPos, size, Color.red));
            }
            else
            {
                addTriangle(new Triangle(xPos, yPos, size, Color.red));
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
    public ArrayList<Triangle> getTriangleList()
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

    // ----------------------------------------------------------
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
     * Tells whether or not the game has been lost by the player.
     * @return true if edge was touched, false otherwise
     */
    public boolean isGameLost()
    {
        return gameLost;
    }

    /**
     * Resets the game level to its initial state.
     */
    public void reset()
    {
        triangleList = new ArrayList<Triangle>();
        gameLost = false;
        paddle.setPosition(new Position(viewWidth / 2, viewHeight - 10));
        addTrianglesToLevel();
    }

    /**
     * Set the gameLost flag
     * @param lost whether the game was lost or not
     */
    public void setGameLost(boolean lost)
    {
        gameLost = lost;
        notifyObservers();
    }

    // ----------------------------------------------------------
    /**
     * Removes the triangle at the specified index from the list.
     * @param index of the triangle to remove
     */
    public void removeTriangle(Triangle triangle)
    {
        triangleList.remove(triangle);
        notifyObservers();
    }
}
