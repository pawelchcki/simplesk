import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;

final class CloseChannelFutureListener implements ChannelFutureListener{
  @Inject
  private EventBus eventBus;

  @Override
  public void operationComplete(ChannelFuture future) throws Exception {
    eventBus.post(new CloseEvent());
  }
}

public class ClientService {

  @Inject
  private Bootstrap bootstrap;
  private Channel channel;
  private EventBus eventBus;

  @Inject
  private CloseChannelFutureListener closeChannelFutureListener;

  @Subscribe
  public void handleLineEvent(LineEvent event) {
    if (this.channel != null) {
      this.channel.writeAndFlush(event.getLine() + "\n");
    }
  }

  public void connect(String host, int port) {
    if (this.channel == null) {
      try {
        this.setChannel(bootstrap.connect(host, port).sync().channel());

      } catch (Exception e) {
        eventBus.post(new CloseEvent(e));
      }
    }
  }

  public void setChannel(Channel channel) {
    this.channel = channel;
    this.channel.closeFuture().addListener(closeChannelFutureListener);
  }

  @Inject
  public void setEventBus(EventBus eventBus) {
    this.eventBus = eventBus;
    eventBus.register(this);
  }
}
