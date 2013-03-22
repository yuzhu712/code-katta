package com.ashishpaliwal.codekatta;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception {
        try {
            System.out.println( "Hello World!" );
            throw new NullPointerException();
        } catch(Exception e) {
//            e.printStackTrace();
        } finally {
            throw new Exception("finally exception");
        }
    }
}
