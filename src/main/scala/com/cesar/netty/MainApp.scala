package com.cesar.netty

import com.cesar.netty.client.NettyClient
import org.slf4j.LoggerFactory

object MainApp {
  val logger = LoggerFactory.getLogger(getClass)

  def main(args: Array[String]): Unit = {
    //    val nettyServer = new NettyServer
    val nettyClient = new NettyClient
    logger.info("conectando")
    nettyClient.connect()
    //    logger.info("Iniciando")
    //    nettyServer.start()
  }
}
