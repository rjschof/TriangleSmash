package org.awesomeco.trianglesmash;
import java.util.ArrayList;


// -------------------------------------------------------------------------
/**
 *  The PowerQueue is the data structure implemented to hold TriangleSmash
 *  game power-ups.
 *  @param <E> - Generic object type
 *
 *  @author Lauren Malhotra (laurcm6)
 *  @author Robert Schofield (rjschof)
 *  @author Adam Zelenka (zadam7)
 *  @version 2015.04.08
 */
public class PowerQueue<E>
{
    private ArrayList<E> myList;

    /**
     * Constructor initializes an ArrayList to hold the data in the PowerQueue.
     */
    public PowerQueue()
    {
        myList = new ArrayList<E>();
    }

    /**
     * Adds an item to the front end of the PowerQueue
     * @param item - the item being added
     */
    public void enqueue(E item)
    {
        myList.add(item);
    }

    /**
     * Removes the item at the rear end of the PowerQueue.
     * @return the item that was removed, or null if there is no item to remove
     */
    public E dequeue()
    {
        if (myList.size() == 0)
            return null;
        else
            return myList.remove(0);
    }

    /**
     * Gives the front item of the PowerQueue, the one that was added most
     * recently.
     * @return the most recently added item, or null if the PowerQueue is empty
     */
    public E lastAdded()
    {
        if (myList.size() == 0)
            return null;
        else
            return myList.get(myList.size() - 1);
    }

    /**
     * Gives the rear item of the PowerQueue, the one least recently added and
     * next available for removal.
     * @return the least recently added item, null if the PowerQueue is empty
     */
    public E nextToRemove()
    {
        if (myList.size() == 0)
            return null;
        else
            return myList.get(0);
    }

    /**
     * Gives the current size of the PowerQueue, the number of items in it.
     * @return the size of the PowerQueue
     */
    public int size()
    {
       return myList.size();
    }

    /**
     * Removes all items from the PowerQueue.
     */
    public void clear()
    {
        myList.clear();

    }

    /**
     * Converts the PowerQueue into String form. ["item", "item", "item"]
     * @return String representation of the PowerQueue
     */
    public String toString()
    {
        String result = "[";
        if (size() == 0)
        {
            result = result + "]";
        }
        else
        {
            for (int i = 0; i < size(); i++)
            {
                result = result + myList.get(i).toString();
                if (i < size() - 1)
                {
                    result = result + ", ";
                }
                else
                {
                    result = result + "]";
                }
            }
        }
        return result;
    }



}
