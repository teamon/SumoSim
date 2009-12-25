package com.yayetee.sumosim

import processing.core.{PConstants, PApplet}
import swing.Component

class Dohyo extends PApplet {
  val K = 3
  
  override def setup {
    size(K*160, K*160)

    smooth
    noStroke
    rectMode(PConstants.CENTER)
    colorMode(PConstants.RGB, 1.0f)
  }

  override def draw {
    background(0.3f)
    drawDohyo
    pushMatrix
    translate(K*3, K*3)
    Simulator.robots.foreach(t => drawRobot(t._2))
    popMatrix
  }

  def drawRobot(robot: Robot) {
    fill(255, 0, 0)
    rotate(robot.angle)
    translate(K*154*robot.x, K*154*robot.y)
    rect(0, 0, K*20, K*20)
  }

  def drawDohyo {
    pushMatrix
    translate(K*80, K*80)
    fill(1)
    ellipse(0, 0, K*154, K*154)
    fill(0)
    ellipse(0, 0, K*144, K*144)
    popMatrix
  }
}
