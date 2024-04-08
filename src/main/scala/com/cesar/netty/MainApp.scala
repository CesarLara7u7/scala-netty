package com.cesar.netty

import com.cesar.netty.client.NettyClient
import com.cesar.netty.server.NettyServer

object MainApp {
  def main(args: Array[String]): Unit = {
    val nettyServer = new NettyServer
    val nettyClient = new NettyClient
    nettyServer.start()
  }
}
