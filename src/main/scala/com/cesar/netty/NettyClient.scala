package com.cesar.netty

import io.grpc.netty.shaded.io.netty.channel.{ChannelFuture, ChannelInitializer, ChannelOption, EventLoopGroup}
import io.grpc.netty.shaded.io.netty.channel.nio.NioEventLoopGroup
class NettyClient {
  val workerGroup: EventLoopGroup = new NioEventLoopGroup()

}
