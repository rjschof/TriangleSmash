package org.awesomeco.trianglesmash;

/**
 * // -------------------------------------------------------------------------
/**
 *  Bonus... something something.
 *
 *  @author Lauren Malhotra (laurcm6)
 *  @author Robert Schofield (rjschof)
 *  @author Adam Zelenka (zadam7)
 *  @version 2015.04.08
 */
public class Bonus
{
    private String bonusName;

    /**
     * Enumerated type for the several game power-up and power-down bonus
     * features. When a queue contains these bonuses, the state of gameplay is
     * changed.
     */
    public enum BonusType
    {
        /**
         * Doubles the width of the paddle.
         */
        WIDEN_PADDLE,
        /**
         * Halves the width of the paddle.
         */
        NARROW_PADDLE,
        /**
         * Halves the velocity of the ball.
         */
        SLOW_BALL,
        /**
         * Doubles the velocity of the ball.
         */
        FAST_BALL,
        /**
         * Makes it so that the ball destroys a triangle on contact and passes
         * through it in the same direction instead of bouncing off of the
         * triangle.
         */
        BULLDOZE
    }

    public Bonus(String bonusName)
    {
        this.bonusName = bonusName;
    }

    public String getBonusName()
    {
        return bonusName;
    }

}
