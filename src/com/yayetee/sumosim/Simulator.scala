package com.yayetee.sumosim

import actors.Actor
import actors.Actor.loop
import scala.collection.mutable.Map

object Simulator extends Actor {
  val robots = Map[SocketActor, Robot]()

  def act {
    loop {
      receive {
        case ('addRobot, socket: SocketActor) =>
          robots += socket -> new Robot
          "0:0:0:0:0:0"

        case ('updateRobot, socket: SocketActor, message: String) =>
          socket ! ('sendResponse, robots(socket).parseMessage(message))
      }
    }
  }
}