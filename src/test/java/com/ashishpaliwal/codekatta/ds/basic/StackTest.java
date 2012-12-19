package com.ashishpaliwal.codekatta.ds.basic;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test class for Stack
 */
public class StackTest {

    @Test
    public void testStackCreation() {
        Stack<String> stringStack = new Stack<String>(10);
        assertEquals(0, stringStack.getSize());
        assertTrue(stringStack.isEmpty());
    }

    @Test
    public void testStackOperation() {
        Stack<String> stringStack = new Stack<String>(10);
        String string1 = "String-1";
        String string2 = "String-2";
        String string3 = "String-3";

        stringStack.push(string1);
        stringStack.push(string2);
        stringStack.push(string3);
        String returnedString = stringStack.pop();
        assertEquals(string3, returnedString);
        assertEquals(string2, stringStack.pop());
        assertEquals(string1, stringStack.pop());
    }

    @Test
    public void testIterator() {
        Stack<String> stringStack = new Stack<String>(10);
        String string1 = "String-1";
        String string2 = "String-2";
        String string3 = "String-3";

        stringStack.push(string1);
        stringStack.push(string2);
        stringStack.push(string3);

        Iterator<String> iterator = stringStack.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(string3, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(string2, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(string1, iterator.next());
    }

}
