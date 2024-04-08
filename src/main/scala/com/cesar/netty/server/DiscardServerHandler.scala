package com.cesar.netty.server

import io.netty.buffer.ByteBuf
import io.netty.channel.{ChannelHandlerContext, ChannelInboundHandler}
import org.slf4j.LoggerFactory


class DiscardServerHandler() extends ChannelInboundHandler {
  val logger = LoggerFactory.getLogger(getClass)

  override def channelRegistered(channelHandlerContext: ChannelHandlerContext): Unit = {
    logger.info("Registering")
  }

  override def channelUnregistered(channelHandlerContext: ChannelHandlerContext): Unit = {
    logger.info("Unregistering")
  }

  override def channelActive(channelHandlerContext: ChannelHandlerContext): Unit = {
    logger.info("Active")
  }

  override def channelInactive(channelHandlerContext: ChannelHandlerContext): Unit = {
    logger.info("Inactive")
  }

  override def channelRead(channelHandlerContext: ChannelHandlerContext, o: Any): Unit = {
    logger.info("reading")
    (message: ByteBuf)=>
      message.release()
  }

  override def channelReadComplete(channelHandlerContext: ChannelHandlerContext): Unit = {
    logger.info("read complete")
  }

  override def userEventTriggered(channelHandlerContext: ChannelHandlerContext, o: Any): Unit = {
    logger.info("Tiggering event")
  }

  override def channelWritabilityChanged(channelHandlerContext: ChannelHandlerContext): Unit = {
    logger.info("Writabiling")
  }

  override def exceptionCaught(channelHandlerContext: ChannelHandlerContext, throwable: Throwable): Unit = {
    logger.info("Exception")
    throwable.printStackTrace()
    channelHandlerContext.close()
  }

  override def handlerAdded(channelHandlerContext: ChannelHandlerContext): Unit = {
    logger.info("adding handler")
  }

  override def handlerRemoved(channelHandlerContext: ChannelHandlerContext): Unit = {
    logger.info("removing handler")
  }
}
