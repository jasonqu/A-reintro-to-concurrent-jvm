package com.demo.java;

import java.util.concurrent.*;

public class DeadlockInExecutor {
  public static void main(String[] args) throws ExecutionException, InterruptedException {
    ExecutorService executor = Executors.newSingleThreadExecutor();
    Future<String> f = executor.submit(() -> {
      Callable<String> task = () -> {
        System.out.println("try to execute task");
        return "111";
      };
      return "aaa" + executor.submit(task).get();
    });
    // 死锁
    System.out.println("will have deadlock in main-thread");
    System.out.println(f.get());
  }
}
