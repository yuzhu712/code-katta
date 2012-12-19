package com.ashishpaliwal.codekatta.ds.basic;

import java.util.Iterator;

/**
 * Simple Stack Implementation
 */
public class Stack<E> implements Iterable<E> {

    // Data holder
    private Object[] data;

    // current capacity
    private int numItems = 0;

    /**
     * Default constructor for creating a fixed size stack
     *
     * @param numberOfItems number of items the stack can hold
     */
    public Stack(int numberOfItems) {
        data = new Object[numberOfItems];
    }

    /**
     * Returns the current number of items in Stack
     *
     * @return  Number of items the stack holds
     */
    public int getSize() {
        return numItems;
    }

    /**
     * Check if the stack contains any items
     *
     * @return  true if stack is empty, false otherwise
     */
    public boolean isEmpty() {
        return numItems == 0;
    }

    /**
     * Adds an element to the stack
     *
     * @param element   Element to be added
     *
     * @throws RuntimeException If stack capacity is reached
     */
    public void push(E element) {
        if(numItems >= data.length) {
            throw new RuntimeException("Stack Overflow");
        }
        data[numItems++] = element;
    }

    /**
     * Removes an element from the top of Stack
     *
     * @return  Top Element
     *
     * @throws RuntimeException if stack size is 0
     */
    public E pop() {
        if(numItems == 0) {
            throw new RuntimeException("Empty Stack");
        }
        return (E)data[--numItems];
    }

    /**
     * Peek on the top element on the stack
     *
     * @return  Top most element on the stack
     */
    public E peek() {
        if(numItems == 0) {
            throw new RuntimeException("Empty Stack");
        }
        return (E)data[numItems];
    }

    @Override
    public Iterator<E> iterator() {
        return new ReverseArrayIterator();
    }

    // Implement the Iterator
    private class ReverseArrayIterator implements Iterator<E> {

        private int i = numItems;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public E next() {
            return (E)data[--i];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("remove not supported");

        }
    }
}
