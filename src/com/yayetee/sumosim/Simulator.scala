package com.yayetee.sumosim

import actors.Actor
import actors.Actor.loop
import scala.collection.mutable.Map

case class AddRobot(socket: SocketActor)
case class UpdateRobot(socket: SocketActor, message: String)
case class RemoveRobot(socket: SocketActor)
case class SendResponse(response: String)

object Simulator extends Actor {
  val robots = Map[SocketActor, Robot]()

  def act {
    loop {
      receive {
        case AddRobot(socket) =>
          GUI.log("[INFO] Robot added " + socket)
          robots += socket -> new Robot

        case UpdateRobot(socket, message) =>
          val response = robots(socket).parseMessage(message)
          GUI.log("[INFO] >> " + message)
          GUI.log("[INFO] << " + response)
          socket ! SendResponse(response)

        case RemoveRobot(socket) =>
          robots - socket
      }
    }
  }
}