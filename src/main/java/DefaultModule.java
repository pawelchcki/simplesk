import com.google.common.eventbus.EventBus;
import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

public class DefaultModule extends AbstractModule {

  private EventBus eventBus = new EventBus("Default");

  @Override
  protected void configure() {
    bind(EventLoopGroup.class).to(NioEventLoopGroup.class);
    bind(Bootstrap.class).toProvider(BootstrapProvider.class);
    bind(EventBus.class).toInstance(eventBus);
    requireBinding(CloseEventResponder.class);
    Names.bindProperties(binder(), System.getProperties());
  }
}
