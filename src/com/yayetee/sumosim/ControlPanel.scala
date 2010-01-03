package com.yayetee.sumosim

import java.awt.event.{ActionListener, ActionEvent}
import javax.swing.{JPanel, JButton, JFrame}

class ControlPanel extends JFrame {
  setTitle("Simple")
  setLocation(600, 0)

  val panel = new JPanel()

  val startButton = new JButton("Start")
  startButton.addActionListener(new ActionListener {
    def actionPerformed(event: ActionEvent) {

    }
  })
  val stopButton = new JButton("Stop")
  stopButton.addActionListener(new ActionListener {
    def actionPerformed(event: ActionEvent) {

    }
  })
  val resetButton = new JButton("Reset")
  resetButton.addActionListener(new ActionListener {
    def actionPerformed(event: ActionEvent) {

    }
  })

  panel.add(startButton)
  panel.add(stopButton)
  panel.add(resetButton)

  add(panel)

  pack
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
  setVisible(true)
}