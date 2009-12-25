package com.yayetee.sumosim

import processing.core.{PConstants, PApplet}

class Dohyo extends PApplet {
  val K = 3
  
  override def setup {
    size(K*200, K*160)

    rectMode(PConstants.CENTER)
    smooth
    noStroke
    colorMode(PConstants.RGB, 1.0f)
  }

  override def draw {
    background(0.3f)
    drawPanel
    drawDohyo
    var i = 0
    Simulator.robots.foreach(t => {
      drawRobot(t._2, i)
      i += 1      
    })
  }

  def drawRobot(robot: Robot, index: Int) {
    // on dohyo
    pushMatrix
    translate(K*3, K*3)
    fill(255, 0, 0)
    rotate(robot.angle)
    translate(K*154*robot.x, K*154*robot.y)
    rect(0, 0, K*20, K*20)
    popMatrix

    // on panel
    pushMatrix
    rect(K*180, K*(index*25 + 15), K*20, K*20)
    popMatrix
  }

  def drawPanel {
    fill(0.2f)
    rect(K*180, K*80, K*40, K*160)
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
