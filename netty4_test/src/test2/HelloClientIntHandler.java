package test2;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class HelloClientIntHandler extends ChannelInboundHandlerAdapter {  

    @Override  
    // 读取服务端的信息  
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {  
        System.out.println("HelloClientIntHandler.channelRead");
        // 通知执行下一个InboundHandler    
        ctx.fireChannelRead(msg); 
    }  
    @Override  
    // 当连接建立的时候向服务端发送消息 ，channelActive 事件当连接建立的时候会触发  
    public void channelActive(ChannelHandlerContext ctx) throws Exception {  
        System.out.println("HelloClientIntHandler.channelActive");
        String msg = "Are you ok?";  
        ByteBuf encoded = ctx.alloc().buffer(4 * msg.length());  
        encoded.writeBytes(msg.getBytes());  
        ctx.write(encoded);  
        ctx.flush();  
    }  
}  