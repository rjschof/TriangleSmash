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

        o = new OvalShape(0, 30, 80);
        o.setColor(Color.black);
        o.setFillColor(Color.red);
        add(o);
        o.setActive(true);
        o.setShapeMotion(ShapeMotion.DYNAMIC);
        o.applyLinearImpulse(10, 7);

        o2 = new OvalShape(125, 250, 40);
        o2.setColor(Color.black);
        o2.setFillColor(Color.red);
        add(o2);
        o2.setActive(true);
        o2.setShapeMotion(ShapeMotion.DYNAMIC);
        o2.applyLinearImpulse(3, -10);

        o3 = new OvalShape(200, 0, 40);
        o3.setColor(Color.black);
        o3.setFillColor(Color.red);
        add(o3);
        o3.setActive(true);
        o3.setShapeMotion(ShapeMotion.DYNAMIC);
        o3.applyLinearImpulse(-5, 10);

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
