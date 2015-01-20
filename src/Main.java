import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.name.Named;

public class Main {
    private ClientService clientService;
    private ConsoleReader consoleReader;
    private String hostname;
    private Integer port;

    @Inject
    Main(ClientService clientService, ConsoleReader reader, @Named("hostname") String hostname, @Named("port") Integer port) {
        this.clientService = clientService;
        this.consoleReader = reader;
        this.hostname = hostname;
        this.port = port;
    }

    public void start() {
        this.consoleReader.start();
        this.clientService.connect(hostname, port);
    }

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new DefaultModule());
        Main m = injector.getInstance(Main.class);
        m.start();
    }
}
