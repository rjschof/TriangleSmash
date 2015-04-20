package org.awesomeco.trianglesmash;


import android.graphics.PointF;
import sofia.graphics.Shape;
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
    private GameLevel[] levels;
    private TextView gameStatus;

    private float xMax;
    private float yMax;

    private RectangleShape paddle;
    private RectangleShape background;
    private OvalShape smashBall;
    private TextShape text;
    private ArrayList<TriangleShape> triangles;

    private Edge topEdge;
    private Edge leftEdge;
    private Edge rightEdge;
    private Edge bottomEdge;

    private GameLevel gameLevel;
    private int currentLevel;
    /**
     * Initializes the screen.
     */
    public void initialize()
    {
        xMax = getShapeView().getHeight() - 20;
        yMax = getShapeView().getWidth() + 20;

        triangles = new ArrayList<TriangleShape>();

        levels = new GameLevel[] {
            new GameLevel(0, 5, xMax, yMax),
            new GameLevel(1, 10, xMax, yMax, "myresource")
        };

        for (GameLevel l: levels)
        {
            l.addObserver(this);
            l.getPaddle().addObserver(this);
            l.addTrianglesToLevel();
        }

        currentLevel = 0;
        setUpForLevel();

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
        gameLevel.getPaddle().setPosition(new PointF(x, yMax - 10));
    }

    /**
     * When a finger moves across the screen, the paddle is moved to the
     * location represented by the movement.
     * @param x the x coordinate of the finger on the screen
     * @param y the y coordinate of the finger on the screen
     */
    public void onTouchMove(float x, float y)
    {
        gameLevel.getPaddle().setPosition(new PointF(x, yMax - 10));
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
            }
            else if (edge.equals(bottomEdge))
            {
                smashBall.setLinearVelocity(0, 0);
                text = new TextShape("You lose.", xMax / 2, yMax / 2);
                add(text);
            }
        }
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
        }
    }

    /**
     *
     */
    public void changeWasObserved(Paddle gamePaddle)
    {
        paddle.setPosition(gamePaddle.getPosition());
    }

    public void changeWasObserved(GameLevel level)
    {
        if (level.equals(levels[currentLevel]))
        {
            if (level.isGameWon())
            {
                smashBall.setLinearVelocity(0, 0);
                text = new TextShape("You win!", xMax / 2, yMax / 2);
                text.setColor(Color.black);
                add(text);

                // TODO: Consider a better way to do this.
                int count = 0;
                while (count < 3)
                {
                    final int c = count;
                    try {
                        runOnUiThread(new Runnable() {
                            public void run() {
                                TriangleSmashScreen.this.gameStatus.setText(
                                    3 - c + " seconds until next level...");
                            }
                        });
                        Thread.sleep(1000);
                    } catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    count++;
                }

                remove(paddle);
                remove(smashBall);
                remove(text);
                remove(background);
                final int levelNum = gameLevel.getLevelNum() + 2;
                currentLevel++;
                setUpForLevel();
                runOnUiThread(new Runnable() {
                    public void run() {
                        TriangleSmashScreen.this.gameStatus.setText(
                            "Level " + levelNum + "!");
                    }
                });

            }
        }
    }

    /**
     *
     */
    public void setUpForLevel()
    {
        gameLevel = levels[currentLevel];

        background = new RectangleShape(0, 0, xMax, yMax);
        if (!gameLevel.getBackground().equals("NONE"))
        {
            background.setImage(gameLevel.getBackground());
        }
        else
        {
            background.setColor(Color.beige);
        }
        background.setZIndex(0);
        background.setActive(false);
        add(background);

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

        for (Shape s: getShapeView().getShapes())
        {
            s.setZIndex(1);
        }
    }

    public void buttonClicked()
    {
        for (int i = 0; i < gameLevel.getTriangleList().size(); i++)
        {
            gameLevel.removeTriangleAt(i);
            triangles.remove(i);
        }
    }

    // ~~~~~ Everything below is included to make testing easier.

    /**
     * Returns the gameLevel object that represents the data model for this
     * game.
     * @return the gameLevel object
     */
    public GameLevel getGameLevel()
    {
        return gameLevel;
    }

    /**
     * Returns the paddle for the screen.
     * @return the game paddle
     */
    public RectangleShape getPaddle()
    {
        return paddle;
    }

    /**
     * Returns the ball from the screen
     * @return the smash ball
     */
    public OvalShape getSmashBall()
    {
        return smashBall;
    }

    /**
     * Returns all four edges from the screen as an array of Edge objects.
     * Index 0: top edge
     * Index 1: left edge
     * Index 2: bottom edge
     * Index 3: right edge
     * @return array of edges for the screen
     */
    public Edge[] getEdges()
    {
        Edge[] edges = new Edge[4];
        edges[0] = topEdge;
        edges[1] = leftEdge;
        edges[2] = bottomEdge;
        edges[3] = rightEdge;
        return edges;
    }


}
