package org.awesomeco.trianglesmash;

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

    private TriangleShape t;
    private OvalShape o;

    private OvalShape o2;
    private OvalShape o3;
    /**
     * Initializes the screen.
     */
    public void initialize()
    {
        // TODO: Remove this! This is just for testing the shape view.
        t = new TriangleShape(150, 50, 200, 100);
        t.setColor(Color.black);
        t.setFillColor(Color.blue);
        add(t);
        // End TODO

        //t.animate(3000).rotation(100).play();
        t.setActive(true);

        o = new OvalShape(50, 20, 30);
        o.setFillColor(Color.red);
        o.setColor(Color.black);
        add(o);
        o.setActive(true);
        o.setShapeMotion(ShapeMotion.DYNAMIC);
        o.setLinearVelocity(8, 2);

        gameStatus.setText("Game initialized successfully. ");
    }

    public void onCollisionBetween(Shape first, Shape second)
    {
        System.out.println("COLLISION DETECTED");
        second.setLinearVelocity(0, 0);
    }

    public void onTouchDown(float x, float y)
    {
        t.setPosition(x, y);
    }

    public void onTouchMove(float x, float y)
    {
        t.setPosition(x, y);
    }

    public void buttonClicked()
    {
        remove(o);
        remove(t);
        initialize();
    }
}
