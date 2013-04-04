package com.ashishpaliwal.codekatta.fun;

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 *
 */
public class ByteBufferFun {

    public static void main(String[] args) {
        ByteBuffer bb = ByteBuffer.allocate(21);
        byte[] ones = new byte[10];
        Arrays.fill(ones, (byte)1);
        System.out.println(Arrays.toString(ones));
        bb.put(ones);
        System.out.println(bb.position());
        bb.put((byte) '|');
        bb.mark();
        System.out.println(bb.position());
        byte[] twos = new byte[10];
        Arrays.fill(twos, (byte)2);
        System.out.println(Arrays.toString(twos));
        bb.put(twos);
        System.out.println(bb.position());
        bb.reset();
        System.out.println("post reset:" + bb.position());
        for (int i = 0; i < twos.length; i++) {
            System.out.print(bb.get() + " ");
        }
    }

}
