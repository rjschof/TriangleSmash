package org.awesomeco.trianglesmash;

import sofia.graphics.Shape;
import sofia.graphics.Polygon;
import sofia.graphics.PolygonShape;
import sofia.graphics.Color;
import sofia.graphics.RectangleShape;
import sofia.graphics.ShapeView;
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
    private ShapeView shapeView;

    private TriangleShape t;
    private RectangleShape r;

    /**
     * Initializes the screen.
     */
    public void initialize()
    {
        // TODO: Remove this! This is just for testing the shape view.
        t = new TriangleShape(150, 50, 200, 100);
        t.setColor(Color.black);
        t.setFillColor(Color.blue);
        shapeView.add(t);
        // End TODO

        // TODO: Remove this! This is just for testing the shape view.
        TriangleShape t2 = new TriangleShape(175, 100, 225, 50);
        t2.setFillColor(Color.red);
        t2.setColor(Color.black);
        shapeView.add(t2);
        // End TODO

        r = new RectangleShape(50, 50, 100, 100);
        r.setColor(Color.black);
        r.setFillColor(Color.aqua);
        shapeView.add(r);

        t2.animate(6000).rotation(6000).play();

        gameStatus.setText("Game initialized successfully. ");
    }

    public void onCollisionBetween(Shape first, Shape second)
    {
        gameStatus.setText("Collision: " + first.toString() + " " +
            second.toString());
    }

    public void onTouchDown(float x, float y)
    {
        t.setPosition(x, y);
    }

    public void onTouchMove(float x, float y)
    {
        t.setPosition(x, y);
    }
}
