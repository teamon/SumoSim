package com.yayetee.sumosim

import Math._

class Robot {
  var x = 0.5
  var y = 0.5
  var angle = Pi

  var history = List((x, y))

  var motorLeft = 0
  var motorRight = 0

  val T = 0.0001
  val D = 0.12987
  val R2 = pow(0.4675324, 2)


  def move {
    val diff = (motorLeft - motorRight).toFloat

    if (diff == 0) {
      val s = motorLeft * T
      x += -s * sin(angle)
      y += s * cos(angle)
    } else {
      val beta = diff * T / D
      var r = ((if(motorLeft > motorRight) motorLeft else motorRight) / diff) * D
      r += D / 2

      x += -r * (cos(angle) - cos(angle + beta))
      y += r * (sin(angle + beta) - sin(angle))

      angle += beta
    }

    history = (x, y) :: history
  }

  def onGround = {
    val a = D * cos(angle) / 2
    val b = D * sin(angle) / 2

    List((x + a - b, y + a + b), (x - a - b, y + a - b),
      (x + a + b, y - a + b), (x - a + b, y - a - b)).map(t => touchesLine(t._1, t._2))
  }

  def touchesLine(x: Double, y: Double) = pow(x - 0.5, 2) + pow(y - 0.5, 2) >= R2

  def parseMessage(message: String) = {
    val parts = message.split(":")
    motorLeft = parts(0).toInt
    motorRight = parts(1).toInt
    (false :: false :: onGround).map(if (_) "1" else "0").mkString(":")
  }

}