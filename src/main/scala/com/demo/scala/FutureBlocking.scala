package com.demo.scala

object FutureBlocking extends App {
  import scala.concurrent._
  import ExecutionContext.Implicits.global
  import scala.concurrent.duration._

  val startTime = System.nanoTime

  val futures = for (_ <- 0 until 16) yield Future {
    blocking {
      Thread.sleep(1000)
    }
  }

  for (f <- futures) Await.ready(f, Duration.Inf)

  val endTime = System.nanoTime

  println(s"Total execution time of the program = ${(endTime - startTime) / 1000000} ms")

}
