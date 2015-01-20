import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;

public class ClientService {
    @Inject
    private Bootstrap bootstrap;
    private Channel channel;

    @Subscribe
    public void handleLineEvent(LineEvent event) {
        System.err.println(event);
        if (this.channel != null) {
            this.channel.writeAndFlush(event.getLine() + "\n");
        }
    }

    public void connect(String host, int port) {
        if (this.channel == null) {
            try {
                this.channel = this.bootstrap.connect(host, port).sync().channel();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    @Inject
    public void registerEventBus(EventBus eventBus) {
        eventBus.register(this);
    }
}
