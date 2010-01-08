package com.yayetee.sumosim

import collection.mutable.ArrayBuffer
import java.net.Socket
import java.io.{BufferedReader, InputStreamReader, PrintWriter, OutputStreamWriter}

object Simulator {
  val robots = new ArrayBuffer[(SocketWrapper, Robot)]
  var speed = 10
  @volatile var sim = new Simulator

  def waitTime = (201 - speed)/8

  def running = sim.running

  def start {
    sim.running = true
    if (!sim.isAlive) sim.start
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
      val socket = t._1
      val line = socket.input.readLine
      if (line == null) {
        socket.close
        //robots.remove(t)
      } else {
        val robot = t._2
        val response = robot.parseMessage(line)
        robot.move
        socket.output.println(response)
        socket.output.flush
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
  def close = socket.close
}
