package com.yayetee.sumosim

import collection.mutable.ArrayBuffer

object Simulator {
  val robots = new ArrayBuffer[Robot]
  var speed = 10
  @volatile var sim = new Simulator

  def waitTime = (100 - speed) * 10

  def running = sim.running

  def start {
    sim.running = true
    if (!sim.isAlive) sim.start
  }

  def step {sim.step}

  def stop {sim.running = false}

  def reset {
    val keep_running = sim.running
    sim.interrupt
    sim.join
    sim = new Simulator
    if (keep_running) start
  }
}


class Simulator extends Thread {
  var running = false

  override def run {
    try {
      while (true) {
        if (running) step
        Thread.sleep(Simulator.waitTime);
      }
    } catch {
      case _ =>
    }

  }

  def step {
    
  }
}
