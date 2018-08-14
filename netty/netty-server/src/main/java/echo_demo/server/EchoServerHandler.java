package echo_demo.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

@ChannelHandler.Sharable
/**
 * 标示一个Channelhandler 可以被多个Channel 安全地共享
 */

public class EchoServerHandler extends ChannelHandlerAdapter {

    int counter = 0;

    /**
     * 对于每个传入的消息都要调用
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//               -----------------------------------以下为 FixedLengthFrameDecoder 固定长度分隔符 -------------------------------------------
        System.out.println("Receive client : [" + msg + "]");//利用telnet命令测试



//        -----------------------------------以下为 DelimiterBasedFrameDecoder -------------------------------------------
//        String body = (String) msg;
//        System.out.println("This is " + ++counter + " times receive client : [" + body + "]");
//        body += "$_";
//        ByteBuf echo = Unpooled.copiedBuffer(body.getBytes());
//        ctx.writeAndFlush(echo);




//        ---------------------------------------------分隔符--------------------------------------------------
//        ByteBuf in = (ByteBuf) msg;
//        System.out.println(
//                "Server received: " + in.toString(CharsetUtil.UTF_8)//将消息记录到控制台
//        );
//        ctx.write(in);/**将接受到的消息写给发送者，而不冲刷出站消息**/
    }

    /**
     * P18
     * 通知ChannelInboundHandlerAdapter 最后一次对channelRead() 的调用时当前批量读取中的最后一条消息
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
                .addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
