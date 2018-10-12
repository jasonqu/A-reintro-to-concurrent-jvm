package com.demo.java;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;

public class ManagedBlockerTest {
  static class SimpleBlocker implements ForkJoinPool.ManagedBlocker {
    volatile Integer result = null;
    public boolean block() throws InterruptedException {
      Thread.sleep(1000);
      result = 5;
      return true;
    }
    public boolean isReleasable() {
      return result != null;
    }
    public Integer getItem() { // call after pool.managedBlock completes
      return result;
    }
  }

  public static void main(String[] args) throws InterruptedException {
    ForkJoinPool pool = new ForkJoinPool(4);
    test(pool, "ForkJoinPool");
    Thread.sleep(3000);
    testManagedBlocker(pool, "ManagedBlocker");
    Thread.sleep(3000);
  }

  static void test(ExecutorService service, String type) {
    System.out.println("test " + type);
    long start = System.currentTimeMillis();
    for(int i = 0; i < 8; i++) {
      int j = i;
      service.submit(() -> {
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) { }
        System.out.println("Thread" + j + " finished after " + (System.currentTimeMillis() - start) + "ms");
      });
    }
  }

  static void testManagedBlocker(ForkJoinPool service, String type) {
    System.out.println("test " + type);
    long start = System.currentTimeMillis();
    for(int i = 0; i < 8; i++) {
      int j = i;
      service.submit(() -> {
        SimpleBlocker blocker = new SimpleBlocker();
        try {
          service.managedBlock(blocker);
        } catch (InterruptedException e) { }

        // 注意，这里就不能长时间阻塞线程了
        while (!blocker.isReleasable()) {
          try {
            Thread.sleep(100);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
        System.out.println("Thread" + j + " finished after " + (System.currentTimeMillis() - start) + "ms");
      });
    }
  }
}