package com.ashishpaliwal.codekatta.ds.basic;

import junit.framework.Assert;
import junit.framework.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

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

}
