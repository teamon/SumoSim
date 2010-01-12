package com.yayetee.sumosim

import java.io.{BufferedReader, InputStreamReader, PrintWriter, OutputStreamWriter}
import collection.mutable.ListBuffer
import java.net.{SocketException, Socket}

object Simulator {
  val robots = new ListBuffer[(SocketWrapper, Robot)]
  var speed = 1
  @volatile var sim = new Simulator

  def waitTime = Math.pow(2, 10 - speed).toInt

  def running = sim.running

  def start {
    try {
      sim.running = true
      if (!sim.isAlive) sim.start
    } catch {
      case e: IllegalThreadStateException => reset
    }
  }

  def stop {sim.running = false}

  def reset {
    val keep_running = sim.running
    sim.interrupt
    sim.join
    sim = new Simulator
    if (keep_running) start
  }

  def acceptSocket(socket: SocketWrapper) {
    robots.append((socket, new Robot))
  }

  def step {
    robots.foreach(t => {
      val s = t._1
      try {
        val line = s.input.readLine
        if (line == null) throw new SocketException

        val robot = t._2
        val response = robot.parseMessage(line)
        robot.move
        s.output.println(response)
        s.output.flush
      } catch {
        case e: SocketException => {
          s.socket.close
          robots -= t
        }

      }

    })
  }
}


class Simulator extends Thread {
  var running = false

  override def run {
    try {
      while (true) {
        if (running) Simulator.step
        Thread.sleep(Simulator.waitTime);
      }
    } catch {case _ =>}

  }

}

class SocketWrapper(val socket: Socket) {
  val input = new BufferedReader(new InputStreamReader(socket.getInputStream))
  val output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream))
}
