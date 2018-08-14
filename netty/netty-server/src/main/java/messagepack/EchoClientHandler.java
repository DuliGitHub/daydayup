package messagepack;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class EchoClientHandler extends ChannelHandlerAdapter {
    private final int sendNumber;

    public EchoClientHandler(int sendNumber) {
        this.sendNumber = sendNumber;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Client receive the maspack message : " + msg);
        ctx.write(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("连接成功");
        ctx.writeAndFlush(Unpooled.copiedBuffer("ABC".getBytes()));
        UserInfo [] infos = UserInfo();
        for(UserInfo info : infos){
            ctx.write(info);
//            ctx.writeAndFlush(info);
        }
        ctx.flush();
    }
    private UserInfo[] UserInfo(){
        UserInfo[] userInfos = new UserInfo[sendNumber];
        UserInfo userInfo;
        for(int i = 0;i<sendNumber;i++){
            userInfo = new UserInfo();
            userInfo.setAge(i);
            userInfo.setName("ABC --- >" + i);
            userInfos[i] = userInfo;
        }
        return userInfos;
    }
}
