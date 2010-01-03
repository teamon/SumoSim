package com.yayetee.sumosim

import processing.core.{PConstants, PApplet}
import javax.swing.JFrame

class DohyoFrame extends JFrame {
  val applet = new Dohyo
  applet.init
  getContentPane.add(applet)

  pack
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
  setVisible(true)
}

class Dohyo extends PApplet {
  val K = 3

  override def setup {
    size(K * 200, K * 160)
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
    pushMatrix
    translate(K * 3, K * 3)

    // history
    fill(0.15f)
    robot.history.foreach(t => ellipse(K * 154 * t._1.toFloat, K * 154 * t._2.toFloat, K * 0.7f, K * 0.7f))

    // on dohyo
    fill(255, 0, 0)
    translate(K * 154 * robot.x.toFloat, K * 154 * robot.y.toFloat)
    rotate(robot.angle.toFloat)
    rect(0, 0, K * 20, K * 20)

    // ground sensors
    fill(0, 255, 0)
    val ground = robot.onGround
    if (ground(0)) rect(K * 7.5f, K * 7.5f, K * 5, K * 5)
    if (ground(1)) rect(-K * 7.5f, K * 7.5f, K * 5, K * 5)
    if (ground(2)) rect(K * 7.5f, -K * 7.5f, K * 5, K * 5)
    if (ground(3)) rect(-K * 7.5f, -K * 7.5f, K * 5, K * 5)
    popMatrix

    // on panel
    pushMatrix
    pushStyle
    fill(255, 0, 0)
    rectMode(PConstants.CORNER)
    rect(K * 165, K * (index * 35 + 5), K * 30, K * 30)
    strokeWeight(1)
    stroke(1)
    fill(0.3f)
    rect(K * 168, K * (index * 35 + 8), K * 5, K * 24)
    rect(K * 187, K * (index * 35 + 8), K * 5, K * 24)
    fill(0, 255, 0)
    rect(K * 168, K * (index * 35 + 20 - 12 * robot.motorLeft / 100), K * 5, K * 12 * robot.motorLeft / 100)
    rect(K * 187, K * (index * 35 + 20 - 12 * robot.motorRight / 100), K * 5, K * 12 * robot.motorRight / 100)
    popStyle
    popMatrix

  }

  def drawPanel {
    fill(0.2f)
    rect(K * 180, K * 80, K * 40, K * 160)
  }

  def drawDohyo {
    pushMatrix
    translate(K * 80, K * 80)
    fill(1)
    ellipse(0, 0, K * 154, K * 154)
    fill(0)
    ellipse(0, 0, K * 144, K * 144)
    popMatrix
  }
}
