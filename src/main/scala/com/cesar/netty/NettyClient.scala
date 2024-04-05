package com.cesar.netty

import io.netty.channel.EventLoopGroup
import io.netty.channel.nio.NioEventLoopGroup

class NettyClient {
  val workerGroup: EventLoopGroup = new NioEventLoopGroup()
}
