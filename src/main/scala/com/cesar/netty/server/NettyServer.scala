package com.cesar.netty.server

import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.SocketChannel
import io.netty.channel.socket.nio.NioServerSocketChannel
import io.netty.channel.{ChannelFuture, ChannelInitializer, ChannelOption, EventLoopGroup}

class NettyServer {

  def start(): Unit = {
    val boosGroup: EventLoopGroup = new NioEventLoopGroup()
    val workerGroup: EventLoopGroup = new NioEventLoopGroup()
    try {
      val server: ServerBootstrap = new ServerBootstrap()
      server.group(boosGroup, workerGroup)
        .channel(classOf[NioServerSocketChannel])
        .childHandler(new ChannelInitializer[SocketChannel] {
          override def initChannel(c: SocketChannel): Unit = {
            c.pipeline().addLast(new DiscardServerHandler)
          }
        })
        .option(ChannelOption.SO_BACKLOG.asInstanceOf[ChannelOption[Any]], 128)
        .childOption(ChannelOption.SO_KEEPALIVE.asInstanceOf[ChannelOption[Any]], true)
      val future: ChannelFuture = server.bind(8888).sync()
      future.channel().closeFuture().sync()
    } finally {
      workerGroup.shutdownGracefully
      boosGroup.shutdownGracefully
    }
  }
}
