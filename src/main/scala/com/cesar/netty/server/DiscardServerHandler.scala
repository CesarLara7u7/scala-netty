package com.cesar.netty.server

import io.netty.buffer.ByteBuf
import io.netty.channel.{ChannelHandlerContext, ChannelInboundHandler, ChannelInboundHandlerAdapter}
import io.netty.util.CharsetUtil
import org.slf4j.LoggerFactory


class DiscardServerHandler()extends ChannelInboundHandlerAdapter {

  val logger = LoggerFactory.getLogger(getClass)


  override def channelRead(channelHandlerContext: ChannelHandlerContext, msg: Any): Unit = {
    logger.info("CHANNEL READ")
    logger.info("CHANNEL {}", channelHandlerContext)
    val message: ByteBuf = msg.asInstanceOf[ByteBuf]
    logger.info("Bytebuf: {}", message)
    logger.info("reading: {}", message.toString(CharsetUtil.UTF_8))
    //TODO: Manejar el mensaje
  }


  override def exceptionCaught(channelHandlerContext: ChannelHandlerContext, throwable: Throwable): Unit = {
    logger.info("Exception")
    throwable.printStackTrace()
    channelHandlerContext.close()
  }

  override def handlerAdded(ctx: ChannelHandlerContext): Unit = {
    logger.info("Adding connection: {}", ctx)
    //TODO: MANEJAR LA CONEXION PARA ALMACENARLA
  }

  override def handlerRemoved(ctx: ChannelHandlerContext): Unit = {
    logger.info("Removing connection: {}", ctx)
    //TODO: REMOVER LA CONEXION
  }
}
