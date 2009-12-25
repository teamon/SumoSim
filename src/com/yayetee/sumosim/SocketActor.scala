package com.yayetee.sumosim

import java.net.Socket
import java.io.{OutputStreamWriter, PrintWriter, InputStreamReader, BufferedReader}
import actors.Actor

class SocketActor(val socket: Socket) extends Actor {
  Simulator ! ('addRobot, this)

  val input = new BufferedReader(new InputStreamReader(socket.getInputStream))
  val output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream))

  override def act {
    val line = input.readLine
    if (line == null) {
      socket.close
      return
    }

    Simulator ! ('updateRobot, this, line)
    
    react {
      case ('sendResponse, response: String) =>
        output.write(response)
        output.flush
        act
    }
    
  }

}