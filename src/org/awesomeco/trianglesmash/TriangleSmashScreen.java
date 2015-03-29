package org.awesomeco.trianglesmash;

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
        RectangleShape r = new RectangleShape(5, 5, 40, 40);
        r.setColor(Color.black);
        r.setFillColor(Color.aqua);
        shapeView.add(r);
        // End TODO

        gameStatus.setText("Game initialized successfully.");
    }
}
