package com.yayetee.sumosim

import swing._
import event.{ValueChanged, ButtonClicked}
import java.awt.Point

object SumoSim extends SimpleGUIApplication {
  Dohyo.init
  
  def top = new MainFrame {
    title = "Control panel"
    location = new Point(602, 0)

    val startButton = new Button {text = "Start"}
    val stepButton = new Button {text = "Step"}
    val resetButton = new Button {text = "Reset"}
    val speedSlider = new Slider {
      min = 0
      max = 100
      majorTickSpacing = 20
      paintTicks = true
      paintLabels = true
    }

    listenTo(startButton, stepButton, resetButton, speedSlider)
    reactions += {
      case ButtonClicked(`startButton`) => Simulator.start
      case ButtonClicked(`stepButton`) => Simulator.step
      case ButtonClicked(`resetButton`) => Simulator.reset
      case ValueChanged(`speedSlider`) => println(speedSlider.value)
    }

    contents = new BoxPanel(Orientation.Vertical) {
      contents += new FlowPanel {
        contents += startButton
        contents += stepButton
        contents += resetButton
      }
      contents += new FlowPanel {
        contents += speedSlider
      }
    }
  }
}



