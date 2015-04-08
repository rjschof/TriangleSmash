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

    private Paddle paddle;
    private SmashBall smashBall;

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
        Triangle t2 = new Triangle(160, 50, 15, Color.blue);
        t2.setColor(Color.black);
        t2.setFillColor(Color.blue);
        //add(t2);

        xMax = getShapeView().getHeight() - 20;
        yMax = getShapeView().getWidth() + 20;

        paddle = new Paddle(xMax / 2, yMax - 10, getWidth() / 6,
            getHeight() / 20);
        paddle.setFillColor(Color.black);
        add(paddle);

        topEdge = new Edge(0, -1, xMax, -1);
        leftEdge = new Edge(-1, 0, -1, yMax);
        rightEdge = new Edge(xMax, 0, xMax, yMax);
        bottomEdge = new Edge(0, yMax, xMax, yMax);
        add(topEdge);
        add(leftEdge);
        add(rightEdge);
        add(bottomEdge);

        smashBall = new SmashBall(getWidth() / 2, getHeight() / 2, 
            getHeight() / 24);
        smashBall.setFillColor(Color.aqua);
        smashBall.setColor(Color.black);
        add(smashBall);
        smashBall.setActive(true);
        smashBall.setShapeMotion(ShapeMotion.DYNAMIC);
        smashBall.setLinearVelocity(getWidth() / 8, getHeight() / 12);

        gameLevel = new FirstLevel(1, getHeight(), getWidth());
        gameLevel.addTrianglesToLevel();
        for (Triangle t: gameLevel.getTriangleList())
        {
            add(t);
        }

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
        paddle.setPosition(x, yMax - 10);
    }

    /**
     * When a finger moves across the screen, the paddle is moved to the
     * location represented by the movement.
     * @param x the x coordinate of the finger on the screen
     * @param y the y coordinate of the finger on the screen
     */
    public void onTouchMove(float x, float y)
    {
        paddle.setPosition(x, yMax - 10);
    }

    /**
     * When a collision occurs between a triangle and the ball, the triangle
     * is removed from the screen and removed from the model.
     * @param ball the ball that collided
     * @param triangle the triangle the ball collided with
     */
    public void onCollisionBetween(SmashBall ball, Edge edge)
    {
        if (edge.equals(topEdge))
        {
            smashBall.setLinearVelocity(smashBall.getLinearVelocity().x,
                -smashBall.getLinearVelocity().y);
        }
        else if (edge.equals(rightEdge) || edge.equals(leftEdge))
        {
            smashBall.setLinearVelocity(-smashBall.getLinearVelocity().x,
                smashBall.getLinearVelocity().y);
        }//
        else if (edge.equals(bottomEdge))
        {
            smashBall.setLinearVelocity(smashBall.getLinearVelocity().x,
                -smashBall.getLinearVelocity().y);
            //gameStatus.setText("Bottom was hit! You lose.");
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
            smashBall.setLinearVelocity(0, 0);
        }
    }

    /**
     * Currently, this button serves no real purpose.
     */
    public void buttonClicked()
    {

    }
}
