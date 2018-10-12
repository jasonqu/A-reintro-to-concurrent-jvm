package com.demo

/**
  * Created by jasonqu on 2017/7/23.
  */
package object scala {
  def log(msg: String) {
    println(s"${Thread.currentThread.getName}: $msg")
  }
}
