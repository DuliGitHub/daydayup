package netty_private_protocol;

import io.netty.buffer.ByteBuf;

import javax.xml.bind.Marshaller;
import java.io.IOException;

public class MarshallingEncoder {
    private static final byte[] LENGTH_PLACEHOLDER = new byte[4];
    Marshaller marshaller;

    public MarshallingEncoder() throws IOException{
//        marshaller = MarshallingCodecFactory.buildMarshalling();//Jboss
//    }
//
//    protected void encode(Object msg,ByteBuf out) throws Exception{
//        try {
//            int lengthPos = out.writerIndex();
//            out.writeBytes(LENGTH_PLACEHOLDER);
//            ChannelBufferByteOutput output = new Chan
//        }
    }
}
