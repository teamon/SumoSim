package com.yayetee.sumosim

import processing.core.{PConstants, PApplet}

class Dohyo extends PApplet {
  val K = 3
  
  override def setup {
    size(K*200, K*160)
    frameRate(30)

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
      t._2.move
      drawRobot(t._2, i)
      i += 1      
    })
  }
  
  def drawRobot(robot: Robot, index: Int) {
    // on dohyo
    pushMatrix
    translate(K*3, K*3)
    fill(255, 0, 0)
    translate(K*154*robot.x.toFloat, K*154*robot.y.toFloat)
    rotate(robot.angle.toFloat)
    rect(0, 0, K*20, K*20)

    fill(0, 255, 0)
//    rect(-K*7.5f, -K*7.5f, K*5, K*5)
//    rect(K*7.5f,  -K*7.5f, K*5, K*5)
//    rect(-K*7.5f, K*7.5f,  K*5, K*5)
//    rect(K*7.5f,  K*7.5f,  K*5, K*5)
    popMatrix

    // on panel
    pushMatrix
    pushStyle
    fill(255, 0, 0)
    rect(K*180, K*(index*25 + 15), K*20, K*20)
    strokeWeight(1)
    stroke(1)
    rectMode(PConstants.CORNER)
    fill(0.3f)
    rect(K*172, K*(index*25 + 7), K*5, K*16)
    rect(K*183, K*(index*25 + 7), K*5, K*16)
    fill(0, 255, 0)
    rect(K*172, K*(index*25 + 15 - 8*robot.motorLeft/100), K*5, K*8*robot.motorLeft/100)
    rect(K*183, K*(index*25 + 15 - 8*robot.motorRight/100), K*5, K*8*robot.motorRight/100)
    popStyle
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
