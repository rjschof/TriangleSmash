package org.awesomeco.trianglesmash;

import sofia.graphics.ViewEdges;
import sofia.graphics.ShapeMotion;
import sofia.graphics.Timings;
import sofia.graphics.OvalShape;
import sofia.graphics.Shape;
import sofia.graphics.Polygon;
import sofia.graphics.PolygonShape;
import sofia.graphics.Color;
import sofia.graphics.RectangleShape;
import sofia.graphics.ShapeView;
import android.widget.TextView;
import sofia.app.ShapeScreen;
import android.view.MotionEvent;

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

    private TriangleShape t;
    private OvalShape o;
    private Paddle paddle;

    /**
     * Initializes the screen.
     */
    public void initialize()
    {
        // TODO: Remove this! This is just for testing the shape view.
        t = new TriangleShape(100, 50, 150, 100);
        t.setColor(Color.black);
        t.setFillColor(Color.blue);
        add(t);
        // End TODO

        xMax = getShapeView().getHeight();
        yMax = getShapeView().getWidth();

        paddle = new Paddle(xMax / 2, yMax - 2, 40, 10);
        paddle.setPosition(xMax / 2, yMax - 10);
        paddle.setFillColor(Color.black);
        add(paddle);

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

    public void buttonClicked()
    {
        initialize();
    }
}
