package com.ashishpaliwal.codekatta.fun;

/**
 *
 */
public class ClassloaderTest {

    public void testClassLoader(long count) {
        long t1 = System.currentTimeMillis();

        for (int i = 0; i < count; i++) {
            try {
                this.getClass().getClassLoader().loadClass("com.ashishpaliwal.codekatta.fun.UnsafePlay");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                // noop
            }
        }
        long t2 = System.currentTimeMillis();
        System.out.println(String.format("Time for %d Million load calls = %d ms", count/1000000L, (t2 - t1)));
    }

    public static void main(String[] args) {
        ClassloaderTest classloaderTest = new ClassloaderTest();
        classloaderTest.testClassLoader(1000000);
        classloaderTest.testClassLoader(10000000);
        classloaderTest.testClassLoader(100000000);

    }

}
