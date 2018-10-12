package com.demo.scala.akka

import akka.actor.{ ActorSystem, Props, Actor }

case object Start
case object Produce
case object Consumed
case object Done

class Master extends Actor {
  val producer = context.actorOf(Props[ProducerActor], "producer")

  def receive = {
    case Start =>
      producer ! Produce
  }
}

class ProducerActor extends Actor {
  // state
  var i = 0

  def receive = {
    case Produce =>
      context.actorOf(Props(classOf[ConsumerActor], i), "consumer" + i)
      println("Produced " + i)
      i = i + 1
      if(i >= 10) {
        self ! Done
      }
    case Done => context.stop(self)
  }
}

class ConsumerActor(i : Int) extends Actor {
  import context.dispatcher
  import scala.concurrent.duration._
  context.system.scheduler.scheduleOnce(1 second, self, Consumed)

  override def receive: Receive = {
    case Consumed =>
      println("Consumed " + i)
      context.parent ! Produce
      context.stop(self)
  }
}

object ProducerConsumerExampleInAkka extends App {
  val system = ActorSystem("ProducerConsumerExampleInAkka")
  val master = system.actorOf(Props[Master], "master")
  master ! Start
}