import com.google.inject.Inject;
import com.google.inject.Provider;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class BootstrapProvider implements Provider<Bootstrap> {

  @Inject
  EventLoopGroup eventLoopGroup;

  @Inject
  TelnetChannelInitializer telnetChannelInitializer;

  @Override
  public Bootstrap get() {
    Bootstrap bootstrap = new Bootstrap();
    bootstrap
        .group(this.eventLoopGroup)
        .channel(NioSocketChannel.class)
        .handler(telnetChannelInitializer);
    return bootstrap;
  }
}
