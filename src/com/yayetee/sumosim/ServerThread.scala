package com.yayetee.sumosim

import java.net.ServerSocket

object ServerThread extends Thread {
  override def run {
    val serverSocket = new ServerSocket(30000)
    while (true) {
      Simulator.acceptSocket(new SocketWrapper(serverSocket.accept))
    }
  }
}

