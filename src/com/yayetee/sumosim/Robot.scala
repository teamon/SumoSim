package com.yayetee.sumosim

import Math._

class Robot {
  var x = 0.5
  var y = 0.5
  var angle = Pi/2

  var motorLeft = 0
  var motorRight = 0

  val T = 0.0001
  val D = 0.13


  def move {
    val diff = (motorRight - motorLeft).toFloat

    if(diff == 0){
      val s = motorLeft * T
      x += -s * sin(angle)
      y += s * cos(angle)
    } else {
      val beta = diff * T / D
      var r = (motorLeft / diff) * D
      r += D/2

      x += -r * (cos(angle) - cos(angle + beta))
      y += r * (sin(angle + beta) - sin(angle))

      angle += beta
    }

    println
  }

  def parseMessage(message: String) = {
    val parts = message.split(":")
    motorLeft = parts(0).toInt
    motorRight = parts(1).toInt
    "0:0:0:0:0:0"
  }

}