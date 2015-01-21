/**
 * Created by pawelch on 20.01.15.
 */
public class CloseEvent {

  private Throwable cause;

  public CloseEvent() {
  }

  public CloseEvent(Throwable cause) {
    this.cause = cause;
  }

  public Throwable getCause() {
    return cause;
  }
}
