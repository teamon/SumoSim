package com.yayetee.sumosim

import java.io.{OutputStreamWriter, PrintWriter, InputStreamReader, BufferedReader}
import actors.Actor
import java.net.{Socket}

class SocketActor(val socket: Socket) extends Actor {
  Simulator ! AddRobot(this)

  val input = new BufferedReader(new InputStreamReader(socket.getInputStream))
  val output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream))

  override def act {
    try {
      val line = input.readLine
      if (line == null) {
        Simulator ! RemoveRobot(this)
        socket.close
        return
      }

      Simulator ! UpdateRobot(this, line)

      react {
        case SendResponse(response) =>
          output.println(response)
          output.flush
          act
      }
    } catch {
      case e: SocketException =>
        Simulator ! RemoveRobot(this)
        socket.close
        return
    }


  }

}