package com.demo.java.blockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

// [一个生产者消费者的例子](http://www.journaldev.com/1034/java-blockingqueue-example)
public class ProducerConsumerService {

  public static void main(String[] args) {
    BlockingQueue<String> queue = new ArrayBlockingQueue<>(1);
    Producer producer = new Producer(queue);
    Consumer consumer = new Consumer(queue);
    //starting producer to produce messages in queue
    new Thread(producer).start();
    new Thread(producer).start();
    //starting consumer to consume messages from queue
    new Thread(consumer).start();
    System.out.println("Producer and Consumer has been started");
  }

}