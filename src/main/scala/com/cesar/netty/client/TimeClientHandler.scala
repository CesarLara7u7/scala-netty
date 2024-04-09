package com.cesar.netty.client

import io.netty.buffer.{ByteBuf, Unpooled}
import io.netty.channel.{ChannelHandlerContext, ChannelInboundHandlerAdapter}
import io.netty.util.CharsetUtil
import org.slf4j.LoggerFactory

import java.util.Date

class TimeClientHandler() extends ChannelInboundHandlerAdapter {
  val logger = LoggerFactory.getLogger(getClass)
  private var buf: Option[ByteBuf] = None
  private var context: Option[ChannelHandlerContext]= None

  override def handlerAdded(ctx: ChannelHandlerContext): Unit = {
    logger.info("Adding connection")
    buf = Some(ctx.alloc().buffer(4))
    context = Some(ctx)
  }

  override def handlerRemoved(ctx: ChannelHandlerContext): Unit = {
    logger.info("Removing")
    buf.get.release()
    buf = None
  }

  override def channelRead(ctx: ChannelHandlerContext, msg: Any): Unit = {
    logger.info("READING")
    val message: ByteBuf = msg.asInstanceOf[ByteBuf]
    buf.get.writeBytes(message)
    logger.info("MESSAGE: {}", message)
    message.release()
    logger.info("RELEASED")
    if (buf.get.readableBytes() > 3) {
      val currentTime: Long = (message.readUnsignedInt() - 2208988800L) * 1000L
      logger.info("DATE: {}", new Date(currentTime))
    }
  }

  override def exceptionCaught(ctx: ChannelHandlerContext, cause: Throwable): Unit = {
    cause.printStackTrace()
    ctx.close()
  }

  override def channelUnregistered(ctx: ChannelHandlerContext): Unit = {
    logger.info("Close ")
  }

  def sendMessage(message: String): Unit = {
    val buffer = buf.get.writeBytes(message.getBytes(CharsetUtil.UTF_8))
    context.get.channel().writeAndFlush(buffer)
    logger.info("Message sent: {}", message)
  }

}
