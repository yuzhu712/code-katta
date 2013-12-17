package com.ashishpaliwal.codekatta.guava;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 *
 */
public class ThreadFactoryTest {

  public static void main(String[] args) {
    Executor executor = Executors.newFixedThreadPool(2, new ThreadFactoryBuilder().setNameFormat("Dummy THread bg-%d").build());
    executor.execute(new Runnable() {
      public void run() {
        System.out.println(Thread.currentThread().getName());
      }
    });
    executor.execute(new Runnable() {
      public void run() {
        System.out.println(Thread.currentThread().getName());
      }
    });

  }

}
