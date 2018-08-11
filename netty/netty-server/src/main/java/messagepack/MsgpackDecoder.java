package messagepack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.msgpack.MessagePack;

import java.util.List;

public class MsgpackDecoder extends MessageToMessageDecoder<ByteBuf> {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        //从数据报byteBuf 中获取需要解码的byte数组，然后调用MessagePack的read方法将其反序列化为Object对象
        //将解码后的对象加入到解码列表list
        final byte[] array;
        final int length = byteBuf.readableBytes();
        array = new byte[length];
        byteBuf.getBytes(byteBuf.readerIndex(),array,0,length);//????作用：
        MessagePack messagePack = new MessagePack();
        list.add(messagePack.read(array));

    }
}
