package com.demo.java;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BolckingInExecutor {
  public static void main(String[] args) throws InterruptedException {
    test(Executors.newFixedThreadPool(4), "newFixedThreadPool");
    Thread.sleep(3000);
    test(Executors.newCachedThreadPool(), "newCachedThreadPool");
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
}
