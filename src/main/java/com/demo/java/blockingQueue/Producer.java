package com.demo.java.blockingQueue;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
  private BlockingQueue<String> queue;

  public Producer(BlockingQueue<String> q) {
    this.queue = q;
  }

  @Override
  public void run() {
    try {
      for (int i = 0; i < 10; i++) {
        //Thread.sleep(i * 200);
        queue.put("" + i);
        System.out.println("Produced " + i);
      }
      queue.put("exit");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}