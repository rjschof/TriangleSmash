package org.awesomeco.trianglesmash;

import sofia.util.Observable;
import java.util.LinkedList;

// -------------------------------------------------------------------------
/**
 *  Handles the TriangleSmash data model, the list of levels of the game, and
 *  other necessary data, excluding physics handling.
 *
 *  @author Robert Schofield (rjschof)
 *  @author Adam Zelenka (zadam7)
 *  @author Lauren Malhotra (laurcm6)
 *  @version 2015.04.21
 */

public class SmashGame extends Observable
{
    private LinkedList<GameLevel> gameLevels; //the levels of the game
    private GameLevel currentLevel; //the current level the player is on
    private static float viewWidth; //the width of the screen
    private static float viewHeight; //the height of the screen
    private boolean gameComplete; //whether all levels have been played
    private boolean gameLost; //whether the player lost the game
    private Paddle paddle; //the paddle
    private Edge[] edges; //the edges of the screen

    //----------------------------------------------------------
    /**
     * Create a new SmashGame object and initializes the model's features to
     * mirror the GUI
     * @param width the width of the screen
     * @param height the height of the screen
     */
    public SmashGame(float width, float height)
    {
        gameLevels = new LinkedList<GameLevel>();
        viewWidth = width;
        viewHeight = height;
        paddle = new Paddle(viewWidth / 2, viewHeight - 10, viewWidth / 6,
            viewHeight / 20);
        gameComplete = false;
        gameLost = false;
        edges = new Edge[] {
            new Edge(0, -1, viewWidth, -1),
            new Edge(-1, 0, -1, viewHeight),
            new Edge(viewWidth, 0, viewWidth, viewHeight),
            new Edge(0, viewHeight, viewWidth, viewHeight)
        };
    }

    //-----------------------------------------------------------
    /**
     * Adds a GameLevel to the contained list of playable levels
     * @param level the level being added to the game
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

    // ----------------------------------------------------------
    /**
     * Gives the list containing all the game levels
     * @return gameLevels the list of game levels
     */
    public LinkedList<GameLevel> getLevelList()
    {
        return gameLevels;
    }

    // ----------------------------------------------------------
    /**
     * Moves to the next level in the game.
     */
    public void nextLevel()
    {
        if (gameLevels.indexOf(currentLevel) == gameLevels.size() - 1)
        {
            gameComplete = true;
        }
        else
        {
            currentLevel = gameLevels.get(gameLevels.indexOf(currentLevel) + 1);
        }
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
     * Returns the level of the game currently displayed.
     * @return currentLevel the current level
     */
    public GameLevel getCurrentLevel()
    {
        return currentLevel;
    }

    // ----------------------------------------------------------
    /**
     * Returns the edges that are part of the data model.
     * @return the edges in this model
     */
    public Edge[] getEdges()
    {
        return edges;
    }

    // ----------------------------------------------------------
    /**
     * Returns the height of the screen.
     * @return the height of the screen
     */
    public static float getViewHeight()
    {
        return viewHeight;
    }

    // ----------------------------------------------------------
    /**
     * Returns the width of the screen.
     * @return the width of the screen
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
     * Sets the game to a state of being lost.
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
     * Tells whether or not the game has been won by the player.
     * @return the gameLost field
     */
    public boolean isGameComplete()
    {
        return gameComplete;
    }

    // ----------------------------------------------------------
    /**
     * Moves the paddle of the data model.
     * @param x the position to which the paddle moves in the x axis
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

    // ----------------------------------------------------------
    /**
     * If a triangle collided with the ball, it should be removed from the
     * level.
     * @param triangle the triangle that was hit by the ball
     */
    public void triangleCollided(Triangle triangle)
    {
        currentLevel.removeTriangle(triangle);
        notifyObservers();
    }

    // ----------------------------------------------------------
    /**
     * The startOver method resets all of the levels in the game and then sets
     * the currentLevel pointed to the first level in the game.
     */
    public void startOver()
    {
        for (GameLevel level: gameLevels)
        {
            level.reset();
        }
        gameComplete = false;
        gameLost = false;
        currentLevel = gameLevels.get(0);
        notifyObservers();
    }

    // ----------------------------------------------------------
    /**
     * Converts the list of levels contained by the model to be displayed as
     * a String.
     * @return game the String representation of the GameLevels
     */
    public String toString()
    {
        String game = "Levels currently in the game: \n";
        for (GameLevel level: gameLevels)
        {
            game = game + level.toString() + "\n";
        }
        return game;
    }

    /**
     * Sets the current level to be a target level.
     * @param level the level that the current level is being set to
     * @precondition: the level already exists in the gameLevels list.
     */
    public void goToLevel(GameLevel level)
    {
        for (GameLevel l: gameLevels)
        {
            if (l.equals(level))
            {
                currentLevel = l;
            }
        }
    }
}
