package org.awesomeco.trianglesmash;

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

    /**
     * Initializes the screen.
     */
    public void initialize()
    {
        // TODO: Remove this! This is just for testing the shape view.
        TriangleShape t = new TriangleShape(50, 150, 200, 100);
        t.setColor(Color.black);
        t.setFillColor(Color.blue);
        shapeView.add(t);
        // End TODO

        // TODO: Remove this! This is just for testing the shape view.
        TriangleShape t2 = new TriangleShape(100, 175, 225, 50);
        t2.setFillColor(Color.red);
        t2.setColor(Color.black);
        shapeView.add(t2);
        // End TODO

        gameStatus.setText("Game initialized successfully.");
    }
}
