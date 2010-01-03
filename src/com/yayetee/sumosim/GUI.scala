package com.yayetee.sumosim

import javax.swing.JFrame
import java.awt.event.{WindowEvent, WindowAdapter}
object GUI {
  def main(args: Array[String]) {
    ServerThread.start
    Simulator.start

    val dohyoFrame = new JFrame("Dohyo")
    val dohyoApplet = new Dohyo
    dohyoApplet.init
    dohyoFrame.getContentPane.add(dohyoApplet)
    dohyoFrame.addWindowListener(new WindowAdapter {
      override def windowClosing(e: WindowEvent) {exit}
    })
    dohyoFrame.setVisible(false)
    dohyoFrame.pack
    dohyoFrame.setVisible(true)
  }

  def log(message: String) {
    //println(message)
  }
}