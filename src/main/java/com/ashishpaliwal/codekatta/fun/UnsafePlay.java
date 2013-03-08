package com.ashishpaliwal.codekatta.fun;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * Playing with Unsafe
 */
public class UnsafePlay {

    class Pointer {
        Object pointer;
    }

    private Unsafe unsafe = getUnsafe();

     class LockTrick {
        int id = 4;

         public LockTrick(int id) {
            this.id = id;
         }

         public synchronized void testObjectLock() {
            System.out.println("Locked the Object");
        }

         @Override
         public String toString() {
             return "Id-"+id;
         }
     }

    public static Unsafe getUnsafe() {
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            return (Unsafe)f.get(null);
        } catch (Exception e) { /* ... */ }
        return null;
    }

    public long getObjectAddressUsingNestedObject(Object o) {
        Pointer pointer = new Pointer();
        pointer.pointer = o;
        try {
            long offset = unsafe.objectFieldOffset(Pointer.class.getDeclaredField("pointer"));
            return unsafe.getLong(pointer, offset);
        } catch (NoSuchFieldException e) {
            return -1;
        }
    }

    public long getObjectAddess(Object o) {
        Object[] objects = {o};
        long baseOffset = unsafe.arrayBaseOffset(Object[].class);
        return unsafe.getLong(objects, baseOffset);
    }

    public void playWithReferences() throws NoSuchFieldException {
        LockTrick lock1 = new LockTrick(1);
        LockTrick lock2 = new LockTrick(2);

        long add2 = getObjectAddess(lock2);

        Pointer pointer = new Pointer();
        pointer.pointer = lock1;
        System.out.println("Pointer = "+pointer.pointer);
        unsafe.putLong(pointer, unsafe.objectFieldOffset(Pointer.class.getDeclaredField("pointer")), add2);
        System.out.println("Pointer after reset "+pointer.pointer);
    }

    public void playWithAddress() {
        LockTrick lock1 = new LockTrick(1);
        LockTrick lock2 = new LockTrick(2);
        LockTrick[] lockTricks = {lock1, lock2};
        System.out.println("Locks = "+ Arrays.asList(lockTricks));

        // address using Pointer method
        System.out.println("Address using Pointer method");
        System.out.println("Pointer = "+((~0L >>> 32) & getObjectAddressUsingNestedObject(lock1)));

        long addressLock1 = getObjectAddess(lockTricks);
        System.out.println("addressLock1 = "+((~0L >>> 32) & addressLock1));
//        long addressLock2 = addressLock1 + 4L;
//        unsafe.putLong(lockTricks, 12L, addressLock2);
//        System.out.println("Locks = "+ Arrays.asList(lockTricks));
    }


    public void playWithUnsafe() throws NoSuchFieldException {
        // get the
        System.out.println(unsafe.addressSize());
        LockTrick lockTrick = new LockTrick(1);
        LockTrick[] lockTricks = {lockTrick};
        class Test {
            LockTrick lockTrick;

            public Test(LockTrick lockTrick1) {
                this.lockTrick = lockTrick1;
            }
        }
        long baseOffset = unsafe.arrayBaseOffset(LockTrick[].class);
        System.out.println("Add = "+(unsafe.getLong(lockTricks, baseOffset)));
        System.out.println("Obj Add = "+getObjectAddess(lockTrick));
        long add = getObjectAddess(lockTrick);
//        System.out.println("unsafe add = "+unsafe.getAddress(add + 12L));

        long lockTrickOffset = unsafe.objectFieldOffset(Test.class.getDeclaredField("lockTrick"));
        System.out.println("lockTrickOffset = "+lockTrickOffset);
        Test t = new Test(new LockTrick(1));
        long add1 = unsafe.getLong(t, lockTrickOffset);
        System.out.println("add = "+((~0L >>> 32) & add1));
//        System.out.println("Value = "+unsafe.getInt(add1 + 12L));

//        System.out.println("Lets get value = "+unsafe.getInt(getObjectAddess(lockTrick)+12L));
//        long off = unsafe.objectFieldOffset(Test.class.getDeclaredField("lockTrick"));
//        System.out.println("off = "+off);
//        long str = unsafe.getLong(new Test(lockTrick), off);
//        System.out.println("Str = "+str);
////        System.out.println("Obj Addrs = "+unsafe.getLong(str));
//        System.out.println("Class Offset = "+unsafe.getLong(new Test(lockTrick), off));
//        System.out.println("Class Offset Int = "+unsafe.getInt(new Test(lockTrick), off));
//        long trickOffset = unsafe.objectFieldOffset(LockTrick.class.getDeclaredField("trick"));
//        System.out.println("Trick offset = "+trickOffset);
//        System.out.println("trick = "+unsafe.getInt(lockTrick, trickOffset));
    }

    public long sizeOf(Object object) {
        return unsafe.getAddress( normalize( unsafe.getInt(object, 4L) ) + 12L );
    }

    public static long normalize(int value) {
        if(value >= 0) return value;
        return (~0L >>> 32) & value;
    }


    public static void main(String[] args) throws NoSuchFieldException {

        UnsafePlay unsafePlay = new UnsafePlay();
//        unsafePlay.playWithUnsafe();
//          unsafePlay.playWithAddress();
        unsafePlay.playWithReferences();
    }

}
