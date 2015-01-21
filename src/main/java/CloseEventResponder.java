import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;

public class CloseEventResponder {

  @Subscribe
  public void handleClosing(CloseEvent ev) {
    System.err.println("closer");
    if (ev.getCause() != null) {
      ev.getCause().printStackTrace();
      System.exit(1);
    } else {
      System.exit(0);
    }
  }

  @Inject
  public void setEventBus(EventBus eventBus) {
    eventBus.register(this);
  }
}
