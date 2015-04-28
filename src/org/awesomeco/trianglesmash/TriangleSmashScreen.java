package org.awesomeco.trianglesmash;

import android.widget.Button;
import sofia.graphics.Shape;
import sofia.graphics.RectangleShape;
import sofia.graphics.OvalShape;
import sofia.graphics.ShapeMotion;
import sofia.graphics.Color;
import android.widget.TextView;
import sofia.app.ShapeScreen;

/**
 * The TriangleSmashScreen class handles all of the operations necessary for
 * the view to act properly.
 *
 * @author Lauren Malhotra (laurcm6)
 * @author Robert Schofield (rjschof)
 * @author Adam Zelenka (zadam7)
 * @version 2015.03.23
 */
public class TriangleSmashScreen extends ShapeScreen
{
    private TextView gameStatus;

    private float xMax;
    private float yMax;
    private boolean gameStarted;

    private RectangleShape paddle;
    private RectangleShape background;
    private OvalShape smashBall;
    private Button gameButton;

    private Edge topEdge;
    private Edge leftEdge;
    private Edge rightEdge;
    private Edge bottomEdge;

    private SmashGame smashGame;

    // ----------------------------------------------------------
    /**
     * Initializes the screen. The maximum x and maximum y values are set for
     * the screen, and passed to the SmashGame class. Observable objects are
     * told that this screen in an observer, and the levels we designed for the
     * game are added to the screen.
     */
    public void initialize()
    {
        xMax = getShapeView().getHeight() - 20;
        yMax = getShapeView().getWidth() + 20;

        smashGame = new SmashGame(xMax, yMax);
        smashGame.addObserver(this);
        smashGame.getPaddle().addObserver(this);

        smashGame.addLevel(new GameLevel(1, 8, 5.0f, "level1"));
        smashGame.addLevel(new GameLevel(2, 16, 10.0f, "level2"));
        smashGame.addLevel(new ComplexGameLevel(3, 15, 15.0f, "level3"));
        smashGame.addLevel(new ComplexGameLevel(4, 30, 15.0f, "level4"));
        smashGame.addLevel(new ComplexGameLevel(5, 45, 20.0f, "level5"));
        gameStarted = false;

        gameStatus.setText("Press start to begin the game!");
        gameButton.setText("Start");
        setUpForLevel();
    }

    // ----------------------------------------------------------
    /**
     * When a finger touches down on the screen, the paddle is moved to the
     * location represented by the movement.
     * @param x the x coordinate of the finger on the screen
     * @param y the y coordinate of the finger on the screen
     */
    public void onTouchDown(float x, float y)
    {
        smashGame.movePaddle(x);
    }

    // ----------------------------------------------------------
    /**
     * When a finger moves across the screen, the paddle is moved to the
     * location represented by the movement.
     * @param x the x coordinate of the finger on the screen
     * @param y the y coordinate of the finger on the screen
     */
    public void onTouchMove(float x, float y)
    {
        smashGame.movePaddle(x);
    }

    // ----------------------------------------------------------
    /**
     * When a collision occurs between the edge and the ball, the ball bounces
     * in the opposite direction. If the collision occurred between the ball
     * and the bottom edge, this information is sent to the model.
     * @param oval the oval that collided.
     * @param edge the edge object the ball collided with
     */
    public void onCollisionBetween(OvalShape oval, Edge edge)
    {
        if(oval.equals(smashBall))
        {
            if (edge.equals(topEdge))
            {
                smashBall.setLinearVelocity(
                    smashBall.getLinearVelocity().x,
                    -smashBall.getLinearVelocity().y);
            }
            else if (edge.equals(rightEdge) || edge.equals(leftEdge))
            {
                smashBall.setLinearVelocity(
                    -smashBall.getLinearVelocity().x,
                    smashBall.getLinearVelocity().y);
            }
            else if (edge.equals(bottomEdge))
            {
                smashGame.gameLost();
            }
        }
    }

    // ----------------------------------------------------------
    /**
     * When a collision occurs between a triangle and the ball, the triangle
     * is removed from the screen and the model is informed of this occurance.
     * @param oval the oval that collided
     * @param triangle the triangle the ball collided with
     */
    public void onCollisionBetween(OvalShape oval, Triangle triangle)
    {
        if (oval.equals(smashBall))
        {
            remove(triangle);
            smashGame.triangleCollided(triangle);
        }
    }

    // ----------------------------------------------------------
    /**
     * If a change is observed in the game, this method will handle the
     * change accordingly.
     * @param game the SmashGame where a change was observed
     */
    public void changeWasObserved(SmashGame game)
    {
        if (paddle != null)
        {
            paddle.setPosition(game.getPaddle().getPosition().toPointF());
        }
        if (smashGame.isGameWon())
        {
            smashBall.setLinearVelocity(0, 0);
            smashGame.nextLevel();
            if (!smashGame.isGameComplete())
            {

                displayMessage("You won! Press start to begin the next level.");
                runOnUiThread(new Runnable() {
                    /**
                     * This method is exexuted inside the Runnable class so
                     * the game button text can be changed without an error
                     * occurring. Before this was added, errors were thrown
                     * because the gameButton text was being edited outside of
                     * the screen thread.
                     */
                    public void run() {
                        TriangleSmashScreen.this.gameButton.setText("Start");
                    }
                });
                gameStarted = false;
                remove(paddle);
                remove(smashBall);
                remove(background);
                setUpForLevel();
            }
            else
            {
                displayMessage("You beat the game! There are no more levels.");
                runOnUiThread(new Runnable() {
                    /**
                     * This method is here for the same reason as the one above.
                     * The gameButton's text is changed to "Start Over" so the
                     * player has the option to begin the game from the start.
                     */
                    public void run() {
                        TriangleSmashScreen.this.gameButton.setText(
                            "Start Over");
                    }
                });
            }
        }
        else if (smashGame.isGameLost())
        {
            smashBall.setLinearVelocity(0, 0);
            displayMessage("You lost! Press reset to try again.");
        }
    }

    // ----------------------------------------------------------
    /**
     * The setUpForLevel method makes the screen represent the data in the model
     * class, SmashGame.
     */
    public void setUpForLevel()
    {
        if (!smashGame.isGameComplete())
        {
            GameLevel gameLevel = smashGame.getCurrentLevel();

            background = new RectangleShape(0, 0, xMax, yMax);
            if (!gameLevel.getBackground().equals("NONE"))
            {
                background.setImage(gameLevel.getBackground());
            }
            else
            {
                background.setColor(Color.beige);
            }
            background.setActive(false);
            add(background);

            paddle = smashGame.getPaddle().toRectangleShape();
            paddle.setFillColor(Color.black);


            for (Triangle triangle: gameLevel.getTriangleList())
            {
                add(triangle);
            }

            smashBall = smashGame.getCurrentLevel().getSmashBall().
                toOvalShape();
            smashBall.setFillColor(Color.aqua);
            smashBall.setColor(Color.black);
            smashBall.setRestitution(5.0f);

            topEdge = smashGame.getEdges()[0];
            leftEdge = smashGame.getEdges()[1];
            rightEdge = smashGame.getEdges()[2];
            bottomEdge = smashGame.getEdges()[3];
            add(topEdge);
            add(leftEdge);
            add(rightEdge);
            add(bottomEdge);

            add(paddle);

            add(smashBall);
            smashBall.setActive(true);
            smashBall.setShapeMotion(ShapeMotion.DYNAMIC);

            for (Shape s: getShapeView().getShapes())
            {
                s.setZIndex(1);
            }
            background.setZIndex(0);
        }
    }

    // ----------------------------------------------------------
    /**
     * When the game button is clicked, a couple of different things should
     * occur based on the game states:
     * 1. If the game has not yet been started, the game button will start the
     *    game and change the text to "Reset."
     * 2. If the game has started/was lost and the user presses the button, the
     *    game will be reset to the state it was at the beginning of the level.
     * 3. If the user has played all of the levels in the game and won, the
     *    game is reset to the first level and all the levels are reset to their
     *    initial states.
     */
    public void gameButtonClicked()
    {
        if (!gameStarted) // game was not yet started
        {
            smashBall.setLinearVelocity(
                smashGame.getCurrentLevel().getSmashBall().getVelocityX(),
                smashGame.getCurrentLevel().getSmashBall().getVelocityY());
            displayMessage(
                "Level " + smashGame.getCurrentLevel().getLevelNum() + "!");
            gameButton.setText("Reset");
            gameStarted = true;
        }
        else if (smashGame.isGameComplete()) // all levels have been played
        {
            smashGame.startOver();
            gameStarted = false;

            remove(paddle);
            remove(smashBall);
            remove(background);

            gameStatus.setText("Press start to begin the game!");
            gameButton.setText("Start");
            setUpForLevel();
        }
        else // player wants to reset the game during a level / after a loss
        {
            remove(paddle);
            remove(smashBall);
            remove(background);
            for (Triangle t: smashGame.getCurrentLevel().getTriangleList())
            {
                remove(t);
            }
            smashGame.resetLevel();
            setUpForLevel();
            displayMessage(
                "Level " + smashGame.getCurrentLevel().getLevelNum() + "!");
            smashBall.setLinearVelocity(
                smashGame.getCurrentLevel().getSmashBall().getVelocityX(),
                smashGame.getCurrentLevel().getSmashBall().getVelocityY());
        }
    }

    // ----------------------------------------------------------
    /**
     * The displayMessage method displays a message on in the gameStatus
     * TextView object on the screen.
     * @param message the method to display on the screen
     */
    public void displayMessage(String message)
    {
        final String text = message;
        runOnUiThread(new Runnable() {
            public void run() {
                TriangleSmashScreen.this.gameStatus.setText(text);
            }
        });

    }

    // ~~~~~ Everything below is included to make testing easier.

    // ----------------------------------------------------------
    /**
     * Returns the paddle for the screen.
     * @return the game paddle
     */
    public RectangleShape getPaddle()
    {
        return paddle;
    }

    // ----------------------------------------------------------
    /**
     * Returns the ball from the screen
     * @return the smash ball
     */
    public OvalShape getSmashBall()
    {
        return smashBall;
    }

    // ----------------------------------------------------------
    /**
     * Returns the SmashGame object that represents the data model
     * @return the data model SmashGame object
     */
    public SmashGame getSmashGame()
    {
        return smashGame;
    }

    // ----------------------------------------------------------
    /**
     * Moves the SmashGame to the level specified in the parameters.
     * @param level the level to switch the current level to
     */
    public void goToLevel(GameLevel level)
    {
        remove(paddle);
        remove(smashBall);
        remove(background);
        for (Triangle t: smashGame.getCurrentLevel().getTriangleList())
        {
            remove(t);
        }
        smashGame.goToLevel(level);
        setUpForLevel();
    }
}

