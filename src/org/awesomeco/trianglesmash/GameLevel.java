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
    private LinkedList<Triangle> triangleList;  //triangles in level
    private int levelNum;                       //level number
    private SmashBall smashBall;                //the ball
    private float ballSpeed;                    //initial speed of ball
    private int numTriangles;                   //current number of triangles
    private int initialNumTriangles;            //initial number of triangles
    private float viewHeight;                   //screen height
    private float viewWidth;                    //screen width
    private String backgroundImage;             //background image

    // ----------------------------------------------------------
    /**
     * Create a new GameLevel object.
     * @param levelNum the current numbered level of the game
     * @param numTriangles the number of triangles to put on the screen
     * @param ballSpeed the speed of the ball
     */
    public GameLevel(int levelNum, int numTriangles, float ballSpeed)
    {
        this.levelNum = levelNum;
        this.numTriangles = numTriangles;
        this.initialNumTriangles = numTriangles;
        this.ballSpeed = ballSpeed;

        triangleList = new LinkedList<Triangle>();
        viewHeight = SmashGame.getViewHeight();
        viewWidth = SmashGame.getViewWidth();
        // start ball in center of screen
        smashBall = new SmashBall(viewWidth / 2, viewHeight / 2,
            viewHeight / 24, ballSpeed * (viewWidth / 8),
            ballSpeed * (viewHeight / 12));
        backgroundImage = "NONE";
        addTrianglesToLevel();
    }

    // ----------------------------------------------------------
    /**
     * Create a new GameLevel object.
     * @param levelNum the numbered level of the game
     * @param numTriangles the initial number of triangles on the screen for
     * the level
     * @param ballSpeed the initial speed of the ball
     * @param background the background image of the screen
     */
    public GameLevel(int levelNum, int numTriangles, float ballSpeed,
        String background)
    {
        this(levelNum, numTriangles, ballSpeed);
        backgroundImage = background;
    }

    // ----------------------------------------------------------
    /**
     * Method to add triangles to the level in a pattern centered on the
     * center of the screen in the x axis.
     */
    public void addTrianglesToLevel()
    {
        // scale triangle parts to screen size
        float size = getViewWidth() / 16;
        float xPos = size;
        float yPos = getViewHeight() / 13;
        int count = 1;
        addTriangle(new Triangle(xPos, yPos, size, Color.orangeRed, false));
        while (count < getInitialNumTriangles())
        {
            xPos += 2 * size;                 //adds next triangle to the right
            if (xPos > getViewWidth() - size)
            {

                xPos = size;
                yPos += 2 * size;             //moves to next row
            }
            Triangle triangle =
                new Triangle(xPos, yPos, size, Color.orangeRed, false);
            addTriangle(triangle);
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

    //-----------------------------------------------------------
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
     * Returns the speed multiplier for the SmashBall object that is part of
     * the data model.
     * @return the speed of the SmashBall
     */
    public float getBallSpeed()
    {
        return ballSpeed;
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

    //-----------------------------------------------------------
    /**
     * Resets the game level to its initial state.
     */
    public void reset()
    {
        triangleList.clear();
        numTriangles = initialNumTriangles;
        smashBall = new SmashBall(viewWidth / 2, viewHeight / 2,
            viewHeight / 24, ballSpeed * (viewWidth / 8),
            ballSpeed * (viewHeight / 12));
        addTrianglesToLevel();
    }

    // ----------------------------------------------------------
    /**
     * Removes the target triangle from the level's list of triangles.
     * @param triangle the triangle being removed
     */
    public void removeTriangle(Triangle triangle)
    {
        triangleList.remove(triangle);
        numTriangles--;
        notifyObservers();
    }

    /**
     * Converts the attributes of the GameLevel to a String representation of
     * the object. Example --> [#3] Triangles(s): 16 Background: starbackground
     * @return the String representation of the GameLevel
     */
    public String toString()
    {
        return "[#" + levelNum + "] Triangle(s): " + numTriangles
            + " Background: " + backgroundImage;
    }
}
