package com.ashishpaliwal.codekatta.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;

/**
 *
 */
public class EchoClient {

  public static final int PORT = 9000;
  public static final String HOSTS = "localhost";

  public void start() throws Exception {
    EventLoopGroup group = new NioEventLoopGroup();
    try {
      Bootstrap bootstrap = new Bootstrap();
      bootstrap.group(group).channel(NioSocketChannel.class).remoteAddress(new InetSocketAddress(HOSTS, PORT)).
              handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                  socketChannel.pipeline().addLast(new EchoClientHandler());
                }
              });
      ChannelFuture future = bootstrap.connect().sync();
      future.channel().closeFuture().sync();
    } finally {
      group.shutdownGracefully().sync();
    }
  }

  public static void main(String[] args) throws Exception {
    new EchoClient().start();
  }
}

class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf>{

  @Override
  public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
    System.out.println("Channel Registered");
  }

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    System.out.println("Channel Active");
    ctx.writeAndFlush(Unpooled.copiedBuffer("Channel Active", CharsetUtil.UTF_8));

  }

  @Override
  protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
    System.out.println("Client rxd : "+ ByteBufUtil.hexDump(byteBuf.readBytes(byteBuf.readableBytes())));
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    cause.printStackTrace();
  }
}
