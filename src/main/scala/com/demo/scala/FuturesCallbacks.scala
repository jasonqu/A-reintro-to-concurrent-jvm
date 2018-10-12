package com.demo.scala

import scala.io.Source

object FuturesCallbacks extends App {
  import scala.concurrent._
  import ExecutionContext.Implicits.global

  def getUrlSpec(): Future[Seq[String]] = Future {
    val f = Source.fromURL("http://www.w3.org/Addressing/URL/url-spec.txt")
    try f.getLines.toList finally f.close()
  }

  val urlSpec: Future[Seq[String]] = getUrlSpec()

  def find(lines: Seq[String], word: String) = lines.zipWithIndex collect {
    case (line, n) if line.contains(word) => (n, line)
  } mkString("\n")

  urlSpec foreach {
    lines => log(s"Found occurrences of 'telnet'\n${find(lines, "telnet")}\n")
  }

  val pass = urlSpec map { lines =>
    lines.zipWithIndex.filter(_._1.contains("password")).mkString("\n")
  } // Future[String]

  pass foreach {
    lines => log(s"Found occurrences of 'password'\n$lines\n")
  }
  pass.failed foreach {
    ex => log(s"catch exception $ex\n")
  }
}