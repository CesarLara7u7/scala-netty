package com.cesar.netty.client

import io.netty.buffer.ByteBuf
import io.netty.channel.{ChannelHandlerContext, ChannelInboundHandlerAdapter}
import org.slf4j.LoggerFactory

import java.util.Date

class TimeClientHandler() extends ChannelInboundHandlerAdapter{
  val logger = LoggerFactory.getLogger(getClass)
  override def channelRead(ctx: ChannelHandlerContext, msg: Any): Unit = {
    logger.info("READING")
    val message: ByteBuf = msg.asInstanceOf[ByteBuf]
    try{
      val currentTime: Long = (message.readUnsignedInt() - 2208988800L) * 1000L
      logger.info("DATE: {}", new Date(currentTime))
    } finally {
      message.release()
    }
  }

  override def exceptionCaught(ctx: ChannelHandlerContext, cause: Throwable): Unit = {
    cause.printStackTrace()
    ctx.close()
  }
}
