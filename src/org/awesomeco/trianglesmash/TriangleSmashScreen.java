package org.awesomeco.trianglesmash;

import sofia.graphics.TextShape;
import sofia.graphics.RectangleShape;
import sofia.graphics.OvalShape;
import java.util.ArrayList;
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

    private RectangleShape paddle;
    private OvalShape smashBall;
    private ArrayList<TriangleShape> triangles;

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

        triangles = new ArrayList<TriangleShape>();

        gameLevel = new FirstLevel(1, xMax, yMax);
        gameLevel.addTrianglesToLevel();

        Paddle modelPaddle = gameLevel.getPaddle();
        paddle = new RectangleShape(
            modelPaddle.getPosition().x - (modelPaddle.getWidth() / 2),
            modelPaddle.getPosition().y - (modelPaddle.getHeight() / 2),
            modelPaddle.getPosition().x + (modelPaddle.getWidth() / 2),
            modelPaddle.getPosition().y + (modelPaddle.getHeight() / 2));
        paddle.setFillColor(Color.black);

        for (Triangle triangle: gameLevel.getTriangleList())
        {
            TriangleShape triangleShape = new TriangleShape(
                triangle.getPosition().x - triangle.getSize(),
                triangle.getPosition().y - triangle.getSize(),
                triangle.getPosition().x + triangle.getSize(),
                triangle.getPosition().y + triangle.getSize());
            triangleShape.setColor(Color.black);
            triangleShape.setFillColor(Color.red);
            add(triangleShape);
            triangles.add(triangleShape);
        }

        SmashBall modelBall = gameLevel.getSmashBall();
        smashBall = new OvalShape(modelBall.getPosition().x,
            modelBall.getPosition().y, modelBall.getRadius());
        smashBall.setFillColor(Color.aqua);
        smashBall.setColor(Color.black);
        smashBall.setRestitution(5.0f);

        topEdge = new Edge(0, -1, xMax, -1);
        leftEdge = new Edge(-1, 0, -1, yMax);
        rightEdge = new Edge(xMax, 0, xMax, yMax);
        bottomEdge = new Edge(0, yMax, xMax, yMax);
        add(topEdge);
        add(leftEdge);
        add(rightEdge);
        add(bottomEdge);

        add(paddle);

        add(smashBall);
        smashBall.setActive(true);
        smashBall.setShapeMotion(ShapeMotion.DYNAMIC);
        smashBall.setLinearVelocity(getWidth() / 8,
            getHeight() / 12);
        smashBall.setAngularVelocity(15);


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
            }//
            else if (edge.equals(bottomEdge))
            {
                smashBall.setLinearVelocity(0, 0);
                TextShape text = new TextShape("You lose.", xMax / 2, yMax / 2);
                add(text);
            }
        }
    }

    /**
     * When a collision occurs between the paddle and the ball, the ball changes
     * direction.
     * @param oval the oval that collided.
     * @param rect the rectangle the oval collided with
     */
    public void onCollisionBetween(OvalShape oval, RectangleShape rect)
    {
        // Not yet implemented.
    }

    /**
     * When a collision occurs between a triangle and the ball, the triangle
     * is removed from the screen and removed from the model.
     * @param oval the oval that collided
     * @param triangle the triangle the ball collided with
     */
    public void onCollisionBetween(OvalShape oval, TriangleShape triangle)
    {
        if (oval.equals(smashBall))
        {
            remove(triangle);
            gameLevel.removeTriangleAt(triangles.indexOf(triangle));
            triangles.remove(triangle);
            if (gameLevel.isGameWon())
            {
                smashBall.setLinearVelocity(0, 0);
                TextShape text = new TextShape("You win!", xMax / 2, yMax / 2);
                add(text);
            }
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
