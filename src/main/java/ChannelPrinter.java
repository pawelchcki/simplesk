import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable
public class ChannelPrinter extends SimpleChannelInboundHandler<String> {

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
    System.err.println(msg);
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    cause.printStackTrace();
    ctx.close();
  }
}
