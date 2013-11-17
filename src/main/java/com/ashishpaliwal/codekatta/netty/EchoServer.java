package com.ashishpaliwal.codekatta.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;


/**
 *
 */
public class EchoServer {
  public static final int PORT = 9000;

  public void start() throws Exception {
    EventLoopGroup group = new NioEventLoopGroup();
    try {
      ServerBootstrap bootstrap = new ServerBootstrap();
      bootstrap.group(group).channel(NioServerSocketChannel.class).localAddress(new InetSocketAddress("localhost",PORT)).childHandler(new ChannelInitializer<SocketChannel>() {
        @Override
        protected void initChannel(SocketChannel socketChannel) throws Exception {
          socketChannel.pipeline().addLast(new EchoServerHandler());
        }
      });

      ChannelFuture f = bootstrap.bind().sync();
      System.out.println("Started Echo Server on port = "+PORT);
      f.channel().closeFuture().sync();
    } finally {
      group.shutdownGracefully().sync();
    }

  }

  public static void main(String[] args) throws Exception {
    new EchoServer().start();
  }

  class EchoServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
      ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
      System.out.println("Server received msg - "+msg);
      ctx.write(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
      cause.printStackTrace();
      ctx.close();
    }
  }
}
