package com.demo.java.blockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// [一个生产者消费者的例子](http://www.journaldev.com/1034/java-blockingqueue-example)
public class ProducerConsumerService2 {

  public static void main(String[] args) {
    ExecutorService exec = Executors.newFixedThreadPool(10);
    BlockingQueue<String> queue = new ArrayBlockingQueue<>(1);

    for(int i = 0; i < 5; i++) {
      exec.submit(new Producer(queue));
      exec.submit(new Consumer(queue));
    }
    System.out.println("Producer and Consumer has been started");
  }

}