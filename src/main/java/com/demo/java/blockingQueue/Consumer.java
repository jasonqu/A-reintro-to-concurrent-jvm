package com.demo.java.blockingQueue;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {

  private BlockingQueue<String> queue;

  public Consumer(BlockingQueue<String> q) {
    this.queue = q;
  }

  @Override
  public void run() {
    try {
      String msg;
      //consuming messages until exit message is received
      while (!(msg = queue.take()).equals("exit")) {
        Thread.sleep(1000);
        System.out.println("Consumed " + msg);
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}