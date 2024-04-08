package com.cesar.netty.client

import io.netty.bootstrap.Bootstrap
import io.netty.channel.EventLoopGroup
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioSocketChannel

class NettyClient {
  val workerGroup: EventLoopGroup = new NioEventLoopGroup()

  try{
    val bootstrap: Bootstrap = new Bootstrap()
    bootstrap.group(workerGroup)
    bootstrap.channel(classOf[NioSocketChannel])
//    bootstrap.option(new ChannelInitializer[SocketChannel] {
//      override def initChannel(ch: SocketChannel): Unit = {
//        ch.pipeline().addLast(new TimeHand)
//      }
//    })
  }finally {}
}
