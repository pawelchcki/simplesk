import com.google.inject.Inject;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class TelnetChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Inject
    StringEncoder encoder;
    @Inject
    StringDecoder decoder;
    @Inject
    ChannelPrinter channelPrinter;

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline()
                .addLast(new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()))
                .addLast(this.decoder)
                .addLast(this.encoder)
                .addLast(this.channelPrinter);
    }
}
