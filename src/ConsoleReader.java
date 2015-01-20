import com.google.common.eventbus.EventBus;
import com.google.inject.Inject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleReader extends Thread {

    @Inject
    EventBus eventBus;

    public void run() {
        System.err.println("started");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        for (; ; ) {
            System.err.println(in);
            String line = null;
            try {
                line = in.readLine();
            } catch (IOException e) {
                eventBus.post(new CloseEvent(e));
                break;
            }
            if (line == null) {
                break;
            }
            System.err.println(line);
            eventBus.post(new LineEvent(line));
            if ("quit".equals(line.toLowerCase())) {
                eventBus.post(new CloseEvent());
                break;
            }
        }
    }
}
