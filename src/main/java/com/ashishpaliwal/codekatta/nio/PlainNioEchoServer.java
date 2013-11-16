package com.ashishpaliwal.codekatta.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 *
 */
public class PlainNioEchoServer {

  public void server(int port) throws IOException {
    System.out.println("Listening on port : "+port);
    ServerSocketChannel serverChannel = ServerSocketChannel.open();
    ServerSocket serverSocket = serverChannel.socket();
    serverSocket.bind(new InetSocketAddress(port));
    serverChannel.configureBlocking(false);

    // open the selector
    Selector selector = Selector.open();

    serverChannel.register(selector, SelectionKey.OP_ACCEPT);

    while(true) {
      try {
        selector.select();
      } catch (IOException ioEx) {
        ioEx.printStackTrace();
        break;
      }

      Set<SelectionKey> readKeys = selector.selectedKeys();
      for (Iterator<SelectionKey> iterator = readKeys.iterator(); iterator.hasNext(); ) {
        SelectionKey key =  iterator.next();
        iterator.remove();

        try {

          if(key.isAcceptable()) {
            ServerSocketChannel server = (ServerSocketChannel) key.channel();
            SocketChannel client = server.accept();
            System.out.println("Accepted connect from "+client);
            client.configureBlocking(true);
            client.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE, ByteBuffer.allocate(100));
          }

          if(key.isReadable()) {
            SocketChannel client = (SocketChannel)key.channel();
            ByteBuffer output = (ByteBuffer) key.attachment();
            client.read(output);
          }

          if(key.isWritable()) {
            SocketChannel client = (SocketChannel)key.channel();
            ByteBuffer output = (ByteBuffer) key.attachment();
            output.flip();
            client.write(output);
            output.compact();
          }

        } catch (IOException ioex) {
          ioex.printStackTrace();
          key.channel();
          try {
            key.channel().close();
          } catch (IOException ioexcep) {
            // NOOP
          }
        }

      }

    }
  }

  public static void main(String[] args) throws IOException {
    PlainNioEchoServer plainNioEchoServer = new PlainNioEchoServer();
    plainNioEchoServer.server(9000);
  }

}
