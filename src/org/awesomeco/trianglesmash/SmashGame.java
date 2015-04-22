package org.awesomeco.trianglesmash;

import java.util.Stack;
import sofia.util.Observable;
import java.util.LinkedList;

// -------------------------------------------------------------------------
/**
 *  Write a one-sentence summary of your class here.
 *  Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 *
 *  @author Robert
 *  @version Apr 21, 2015
 */

public class SmashGame extends Observable
{
    private LinkedList<GameLevel> gameLevels;
    private GameLevel currentLevel;
    private static float viewWidth;
    private static float viewHeight;
    private boolean gameLost;

    // ---------- Private fields for the game objects -----------
    private Paddle paddle;
    private Edge[] edges;

    // ----------------------------------------------------------
    /**
     * Create a new SmashGame object.
     * @param width
     * @param height
     */
    public SmashGame(float width, float height)
    {
        gameLevels = new LinkedList<GameLevel>();
        viewWidth = width;
        viewHeight = height;
        paddle = new Paddle(viewWidth / 2, viewHeight - 10, viewWidth / 6,
            viewHeight / 20);
        gameLost = false;
        edges = new Edge[] {
            new Edge(0, -1, viewWidth, -1),
            new Edge(-1, 0, -1, viewHeight),
            new Edge(viewWidth, 0, viewWidth, viewHeight),
            new Edge(0, viewHeight, viewWidth, viewHeight)
        };
    }

    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * @param level
     */
    public void addLevel(GameLevel level)
    {
        gameLevels.add(level);
        if (gameLevels.size() == 1)
        {
            currentLevel = gameLevels.get(0);
            notifyObservers();
        }
    }

    /**
     *
     */
    public LinkedList<GameLevel> getLevelList()
    {
        return gameLevels;
    }

    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * @return
     */
    public void nextLevel()
    {
        currentLevel = gameLevels.get(currentLevel.getLevelNum());
        notifyObservers();
    }

    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * @param level
     */
    public void removeLevel(GameLevel level)
    {
        gameLevels.remove(level);
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
     * Place a description of your method here.
     * @return
     */
    public GameLevel getCurrentLevel()
    {
        return currentLevel;
    }

    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * @return
     */
    public Edge[] getEdges()
    {
        return edges;
    }

    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * @return
     */
    public static float getViewHeight()
    {
        return viewHeight;
    }

    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * @return
     */
    public static float getViewWidth()
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
        return (currentLevel.getNumTriangles() == 0);
    }

    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     */
    public void gameLost()
    {
        gameLost = true;
        notifyObservers();
    }

    // ----------------------------------------------------------
    /**
     * Tells whether or not the game has been lost by the player.
     * @return the gameLost field
     */
    public boolean isGameLost()
    {
        return gameLost;
    }

    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * @param x
     */
    public void movePaddle(float x)
    {
        paddle.setPosition(new Position(x, viewHeight - 10));
        notifyObservers();
    }

    /**
     * Resets the current level to its initial conditions.
     */
    public void resetLevel()
    {
        gameLost = false;
        currentLevel.reset();
    }

    public void removeTriangle(Triangle triangle)
    {
        currentLevel.removeTriangle(triangle);
        notifyObservers();
    }

    public void startOver()
    {
        for (GameLevel level: gameLevels)
        {
            level.reset();
        }
        currentLevel = gameLevels.getFirst();
    }

    public String toString()
    {
        String game = "Levels currently in the game: \n";
        for (GameLevel level: gameLevels)
        {
            game = game + level.toString() + "\n";
        }
        return game;
    }
}
