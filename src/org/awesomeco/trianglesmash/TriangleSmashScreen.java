package org.awesomeco.trianglesmash;

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

    /**
     * Initializes the screen.
     */
    public void initialize()
    {
        gameStatus.setText("Game initialized successfully.");
        System.out.println("Test.");
    }
}
