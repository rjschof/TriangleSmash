package org.awesomeco.trianglesmash;
import student.TestCase;

/**
 * // -------------------------------------------------------------------------
/**
  *  Tests implementation of PowerQueue class.
 *
 *  @author Lauren Malhotra (laurcm6)
 *  @author Robert Schofield (rjschof)
 *  @author Adam Zelenka (zadam7)
 *  @version 2015.04.08
 */
public class PowerQueueTest extends TestCase
{
    private PowerQueue<String> testQueue; //PowerQueue being tested

    /**
     * Constructor for test of PowerQueue has no attributes. It's constructor
     * is intentionally left blank.
     */
    public PowerQueueTest()
    {
        //Constructor intentionally left blank
    }

    /**
     * Instantiates the testQueue for method testing. This method is called at
     * the start of each test method to create a fresh instance of the
     * testQueue.
     */
    public void setUp()
    {
        testQueue = new PowerQueue<String>();
    }

    /**
     * Tests the enqueue method of the PowerQueue class.
     */
    public void testEnqueue()
    {
        setUp();
        testQueue.enqueue("hello");
        assertEquals(1, testQueue.size());
        testQueue.enqueue("goodbye");
        assertEquals(2, testQueue.size());
    }

    /**
     * Tests the dequeue method of the PowerQueue class in cases of empty and
     * non-empty queues.
     */
    public void testDequeue()
    {
        setUp();
        testQueue.enqueue("hello");
        testQueue.enqueue("goodbye");
        assertEquals("hello", testQueue.dequeue());
        assertEquals("goodbye", testQueue.dequeue());
        assertNull(testQueue.dequeue());
    }

    /**
     * Tests the lastAdded method of the PowerQueue class in cases of empty and
     * non-empty queues.
     */
    public void testLastAdded()
    {
        setUp();
        assertNull(testQueue.lastAdded());
        testQueue.enqueue("hello");
        assertEquals("hello", testQueue.lastAdded());
        testQueue.enqueue("goodbye");
        assertEquals("goodbye", testQueue.lastAdded());
    }

    /**
     * Tests the nextToRemove method of the PowerQueue class in cases of
     * empty and non-empty queues.
     */
    public void testNextToRemove()
    {
        setUp();
        assertNull(testQueue.nextToRemove());
        testQueue.enqueue("hello");
        assertEquals("hello", testQueue.nextToRemove());
        testQueue.enqueue("goodbye");
        assertEquals("hello", testQueue.nextToRemove());
    }

    /**
     * Tests the clear method of the PowerQueue class.
     */
    public void testClear()
    {
        setUp();
        testQueue.enqueue("hello");
        testQueue.enqueue("goodbye");
        testQueue.enqueue("hello again");
        testQueue.clear();
        assertEquals(0, testQueue.size());
    }

    /**
     * Tests the size method of the PowerQueue class.
     */
    public void testSize()
    {
        setUp();
        assertEquals(0, testQueue.size());
        testQueue.enqueue("hello");
        assertEquals(1, testQueue.size());
        testQueue.dequeue();
        assertEquals(0, testQueue.size());
    }

    /**
     * Tests the toString method of the PowerQueue class in cases of empty and
     * non-empty queues.
     */
    public void testToString()
    {
        setUp();
        assertEquals("[]", testQueue.toString());
        testQueue.enqueue("hello");
        testQueue.enqueue("goodbye");
        testQueue.enqueue("hello again");
        assertEquals("[hello, goodbye, hello again]", testQueue.toString());
    }

}
