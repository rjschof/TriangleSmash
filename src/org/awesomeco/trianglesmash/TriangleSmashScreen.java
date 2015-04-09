package org.awesomeco.trianglesmash;

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

    private Edge topEdge;
    private Edge leftEdge;
    private Edge rightEdge;
    private Edge bottomEdge;

    private GameLevel gameLevel;

    /**
     * Initializes the screen.
     */
    public void initialize()
    {
        xMax = getShapeView().getHeight() - 20;
        yMax = getShapeView().getWidth() + 20;

        gameLevel = new FirstLevel(1, xMax, yMax);
        gameLevel.addTrianglesToLevel();
        for (Triangle t: gameLevel.getTriangleList())
        {
            add(t);
        }

        add(gameLevel.getPaddle());

        // TODO: Should we put these in the model? Probably not.
        topEdge = new Edge(0, -1, xMax, -1);
        leftEdge = new Edge(-1, 0, -1, yMax);
        rightEdge = new Edge(xMax, 0, xMax, yMax);
        bottomEdge = new Edge(0, yMax, xMax, yMax);
        add(topEdge);
        add(leftEdge);
        add(rightEdge);
        add(bottomEdge);

        add(gameLevel.getSmashBall());
        gameLevel.getSmashBall().setActive(true);
        gameLevel.getSmashBall().setShapeMotion(ShapeMotion.DYNAMIC);
        gameLevel.getSmashBall().setLinearVelocity(getWidth() / 8,
            getHeight() / 12);
        gameLevel.getSmashBall().setAngularVelocity(15);

        gameStatus.setText("Game initialized successfully. ");
    }

    /**
     * When a finger touches down on the screen, the paddle is moved to the
     * location represented by the movement.
     * @param x the x coordinate of the finger on the screen
     * @param y the y coordinate of the finger on the screen
     */
    public void onTouchDown(float x, float y)
    {
        gameLevel.getPaddle().setPosition(x, yMax - 10);
    }

    /**
     * When a finger moves across the screen, the paddle is moved to the
     * location represented by the movement.
     * @param x the x coordinate of the finger on the screen
     * @param y the y coordinate of the finger on the screen
     */
    public void onTouchMove(float x, float y)
    {
        gameLevel.getPaddle().setPosition(x, yMax - 10);
    }

    /**
     * When a collision occurs between a triangle and the ball, the triangle
     * is removed from the screen and removed from the model.
     * @param ball the ball that collided
     * @param edge the edge object the ball collided with
     */
    public void onCollisionBetween(SmashBall ball, Edge edge)
    {
        if (edge.equals(topEdge))
        {
            gameLevel.getSmashBall().setLinearVelocity(
                gameLevel.getSmashBall().getLinearVelocity().x,
                -gameLevel.getSmashBall().getLinearVelocity().y);
        }
        else if (edge.equals(rightEdge) || edge.equals(leftEdge))
        {
            gameLevel.getSmashBall().setLinearVelocity(
                -gameLevel.getSmashBall().getLinearVelocity().x,
                gameLevel.getSmashBall().getLinearVelocity().y);
        }//
        else if (edge.equals(bottomEdge))
        {
            gameLevel.getSmashBall().setLinearVelocity(
                gameLevel.getSmashBall().getLinearVelocity().x,
                -gameLevel.getSmashBall().getLinearVelocity().y);
        }
    }

    /**
     * When a collision occurs between a triangle and the ball, the triangle
     * is removed from the screen and removed from the model.
     * @param ball the ball that collided
     * @param triangle the triangle the ball collided with
     */
    public void onCollisionBetween(SmashBall ball, Triangle triangle)
    {
        gameLevel.removeTriangle(triangle);
        remove(triangle);
        if (gameLevel.getNumTriangles() == 0)
        {
            gameLevel.getSmashBall().setLinearVelocity(0, 0);
        }
    }

    /**
     * Returns the gameLevel object that represents the data model for this
     * game.
     * @return the gameLevel object
     */
    public GameLevel getGameLevel()
    {
        return gameLevel;
    }
}
