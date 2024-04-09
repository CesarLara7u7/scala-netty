package com.cesar.netty.client

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.ReplayingDecoder

import java.util

class TimeDecoder extends ReplayingDecoder[Unit]{

  override def decode(ctx: ChannelHandlerContext, in: ByteBuf, out: util.List[AnyRef]): Unit = {
    out.add(in.readBytes(4))
  }
}
