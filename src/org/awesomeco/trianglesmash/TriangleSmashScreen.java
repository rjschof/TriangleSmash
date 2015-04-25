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
     * Initializes the screen.
     */
    public void initialize()
    {
        xMax = getShapeView().getHeight() - 20;
        yMax = getShapeView().getWidth() + 20;

        smashGame = new SmashGame(xMax, yMax);
        smashGame.addObserver(this);
        smashGame.getPaddle().addObserver(this);

        smashGame.addLevel(new GameLevel(1, 8, 1.0f, "starbackground"));
        smashGame.addLevel(new GameLevel(2, 16, 5.0f, "doomface"));
        smashGame.addLevel(new ComplexGameLevel(3, 15, 10.0f,
            "starbackground"));
        smashGame.addLevel(new ComplexGameLevel(4, 30, 10.0f,
            "starbackground"));
        smashGame.addLevel(new ComplexGameLevel(5, 45, 10.0f,
            "starbackground"));

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
     * in the opposite direction.
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
     * is removed from the screen and removed from the model.
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
     *
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
            if (smashGame.getLevelList().indexOf(smashGame.getCurrentLevel())
                < smashGame.getLevelList().size() - 1)
            {
                smashGame.nextLevel();
                displayMessage("You won! Press start to begin the next level.");
                runOnUiThread(new Runnable() {
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
     *
     */
    public void setUpForLevel()
    {
        int index = 0;
        for (int i = 0; i < smashGame.getLevelList().size(); i++)
        {
            if (smashGame.getCurrentLevel()
                .equals(smashGame.getLevelList().get(i)))
            {
                index = i;
            }
        }

        if (index != smashGame.getLevelList().size())
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
     *
     */
    public void gameButtonClicked()
    {
        if (!gameStarted)
        {
            smashBall.setLinearVelocity(
                smashGame.getCurrentLevel().getSmashBall().getVelocityX(),
                smashGame.getCurrentLevel().getSmashBall().getVelocityY());
            //smashBall.setLinearVelocity(0, -20);
            displayMessage(
                "Level " + smashGame.getCurrentLevel().getLevelNum() + "!");
            gameButton.setText("Reset");
            gameStarted = true;
        }
        else if (smashGame.getLevelList().indexOf(smashGame.getCurrentLevel())
            == smashGame.getLevelList().size() - 1)
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
        else
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
            //smashBall.setLinearVelocity(0, -20);
        }
    }

    // ----------------------------------------------------------
    /**
     *
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
}
