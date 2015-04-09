package org.awesomeco.trianglesmash;

import student.TestCase;

/**
 * // -------------------------------------------------------------------------
/**
 *  Write a one-sentence summary of your class here.
 *  Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 *
 *  @author Adam
 *  @version Apr 8, 2015
 */
public class PowerQueueTest extends TestCase
{
    private PowerQueue<String> testQueue;

    public PowerQueueTest()
    {
        //Constructor intentionally left blank
    }

    public void setUp()
    {
        testQueue = new PowerQueue<String>();
    }

    public void testEnqueue()
    {
        setUp();
        testQueue.enqueue("hello");
        assertEquals(1, testQueue.size());
        testQueue.enqueue("goodbye");
        assertEquals(2, testQueue.size());
    }
    public void testDequeue()
    {
        setUp();
        testQueue.enqueue("hello");
        testQueue.enqueue("goodbye");
        assertEquals("hello", testQueue.dequeue());
        assertEquals("goodbye", testQueue.dequeue());
        assertNull(testQueue.dequeue());
    }
    public void testLastAdded()
    {
        setUp();
        assertNull(testQueue.lastAdded());
        testQueue.enqueue("hello");
        assertEquals("hello", testQueue.lastAdded());
        testQueue.enqueue("goodbye");
        assertEquals("goodbye", testQueue.lastAdded());
    }
    public void testNextToRemove()
    {
        setUp();
        assertNull(testQueue.nextToRemove());
        testQueue.enqueue("hello");
        assertEquals("hello", testQueue.nextToRemove());
        testQueue.enqueue("goodbye");
        assertEquals("hello", testQueue.nextToRemove());
    }
    public void testClear()
    {
        setUp();
        testQueue.enqueue("hello");
        testQueue.enqueue("goodbye");
        testQueue.enqueue("hello again");
        testQueue.clear();
        assertEquals(0, testQueue.size());
    }
    public void testSize()
    {
        setUp();
        assertEquals(0, testQueue.size());
        testQueue.enqueue("hello");
        assertEquals(1, testQueue.size());
        testQueue.dequeue();
        assertEquals(0, testQueue.size());
    }
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
