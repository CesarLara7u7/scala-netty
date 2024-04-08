package com.cesar.netty.client

import io.netty.bootstrap.Bootstrap
import io.netty.channel.{ChannelFuture, ChannelInitializer, ChannelOption, EventLoopGroup}
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.SocketChannel
import io.netty.channel.socket.nio.NioSocketChannel
import org.slf4j.LoggerFactory

class NettyClient {
  val workerGroup: EventLoopGroup = new NioEventLoopGroup()
  val logger = LoggerFactory.getLogger(getClass)
  def connect(): Unit = {
    try {
      val bootstrap: Bootstrap = new Bootstrap()
      bootstrap.group(workerGroup)
      bootstrap.channel(classOf[NioSocketChannel])
      bootstrap.option(ChannelOption.SO_KEEPALIVE.asInstanceOf[ChannelOption[Any]], true)
      val timeClientHandler = new TimeClientHandler
      bootstrap.handler(new ChannelInitializer[SocketChannel] {
        override def initChannel(ch: SocketChannel): Unit = {
          ch.pipeline().addLast(timeClientHandler)
        }
      })
      logger.info("Connecting")
      val future: ChannelFuture = bootstrap.connect("localhost", 8888).sync()
      future
      logger.info("Connected")
      future.channel().closeFuture().sync()
    } finally {
      workerGroup.shutdownGracefully()
    }
  }
}
