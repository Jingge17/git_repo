package test.client;

import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import test.client_handler.ClientHandler;

public class Client {
private final String host;
private final int port;
public Client(String host,int prot){
	this.host=host;
	this.port=prot;
}
public void start() throws Exception{
	EventLoopGroup group=new NioEventLoopGroup();
	Bootstrap b=new Bootstrap();
	b.group(group).channel(NioSocketChannel.class)
	.remoteAddress(new InetSocketAddress(host, port))
	.handler(new ChannelInitializer<SocketChannel>() {

		@Override
		protected void initChannel(SocketChannel ch) throws Exception {
			ch.pipeline().addLast(new ClientHandler());
		}
	});
	ChannelFuture f=b.connect().sync();
	System.out.println("client:connecting to server");
	f.channel().closeFuture().sync();
	group.shutdownGracefully().sync();
	System.out.println("client:connection close successfully");
}
public static void main(String[] args)throws Exception{
	new Client("localhost", 8080).start();
}
}
