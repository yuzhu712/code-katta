package com.ashishpaliwal.codekatta;

/**
 * Hello world!
 *
 */
public class App {

    public static void main( String[] args ) throws Exception {
        System.out.println("300".matches("^\\d\\d\\d"));
        System.out.println("30".matches("^\\d\\d\\d"));
        System.out.println("a0".matches("^\\d\\d\\d"));

    }
}
