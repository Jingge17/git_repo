package test2;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class HelloClientIntHandler2 extends ChannelInboundHandlerAdapter {  

    @Override  
    // 读取服务端的信息  
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {  
        System.out.println("HelloClientIntHandler2.channelRead");
        ByteBuf result = (ByteBuf) msg;  
        byte[] result1 = new byte[result.readableBytes()];  
        result.readBytes(result1);  
        result.release();  
        ctx.close();  
        System.out.println("Server said:" + new String(result1));  
    }  
    
    // 当连接建立的时候向服务端发送消息 ，channelActive 事件当连接建立的时候会触发  
    public void channelActive(ChannelHandlerContext ctx) throws Exception {  
        System.out.println("HelloClientIntHandler2.channelActive");
      
    }  
}  