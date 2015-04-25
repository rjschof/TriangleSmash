package org.awesomeco.trianglesmash;
import sofia.graphics.Color;

// -------------------------------------------------------------------------
/**
 *  This class creates a level in the TriangleSmash game that incorporates
 *  flipped triangles within the negative space left between right-side-up
 *  triangles.
 *
 *  @author Lauren Malhotra (laurcm6)
 *  @author Robert Schofield (rjschof)
 *  @author Adam Zelenka (zadam7)
 *  @version 2015.04.22
 */
public class ComplexGameLevel extends GameLevel
{
    //-----------------------------------------------------------
    /**
     * Creates a new ComplexGameLevel with a blank background
     * @param levelNum the number of the current level
     * @param numTriangles the initial number of triangles in the level
     * @param ballSpeed the speed of the ball
     */
    public ComplexGameLevel(int levelNum, int numTriangles, float ballSpeed)
    {
        super(levelNum, numTriangles, ballSpeed);
    }

    // ----------------------------------------------------------
    /**
     * Create a new ComplexGameLevel with a starry background
     * @param levelNum the number of the current level
     * @param numTriangles the initial number of triangles in the level
     * @param ballSpeed the speed of the ball
     * @param background the background
     */
    public ComplexGameLevel(int levelNum, int numTriangles, float ballSpeed,
        String background)
    {
        super(levelNum, numTriangles, ballSpeed, background);
    }

    //------------------------------------------------------------
    /**
     * Adds triangles onto the screen in a pattern centered on the center of
     * the screen in the x axis.
     */
    @Override
    public void addTrianglesToLevel()
    {
        float size = getViewWidth() / 16;
        float xPos = size;
        float yPos = getViewHeight() / 13;
        int count = 1;
        boolean flipped = false;
        addTriangle(new Triangle(xPos, yPos, size, Color.red, flipped));
        while (count < getInitialNumTriangles())
        {
            xPos += size;
            if (xPos > getViewWidth() - size)
            {

                xPos = size;
                yPos += 2 * size;
            }
            flipped = !flipped;
            Triangle triangle;
            triangle = new Triangle(xPos, yPos, size, Color.red, flipped);
            if (flipped)
            {
                float diffY = Math.abs(triangle.calculateCenterOfBox().y -
                    triangle.calculateCentroid().y);
                triangle.setPosition(xPos, yPos - 2 * diffY);
                triangle.setFillColor(Color.blue);
            }
            addTriangle(triangle);
            count++;
        }
        notifyObservers();
    }
}
