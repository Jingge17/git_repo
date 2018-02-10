package test.server;

import java.net.InetSocketAddress;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import test.server_handler.ServerHandler;

public class Server {
private final int port;
public Server(int port){
	this.port=port;
}
public static void main(String[] args) throws Exception{
	new Server(8080).start();
}
public void start() throws Exception{
	final ServerHandler serverHandler=new ServerHandler();
	EventLoopGroup group=new NioEventLoopGroup();
	ServerBootstrap b=new ServerBootstrap();
	b.group(group).channel(NioServerSocketChannel.class)
	.localAddress(new InetSocketAddress(port)).childHandler(
			new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(serverHandler);
					
				}
				
	});
	ChannelFuture f=b.bind().sync();
	System.out.println("server:waiting for connect...");
	f.channel().closeFuture().sync();
	group.shutdownGracefully().sync();
	System.out.println("server:connection close successfully");
}
}
