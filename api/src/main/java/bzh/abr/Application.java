package bzh.abr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.net.InetAddress;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    private Environment environment;

    protected Application() {}

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        logger.info(
                "Access URLs:\n----------------------------------------------------------\n\t"
                        + "Local: \t\thttp://127.0.0.1:{}\n\t"
                        + "External: \thttp://{}:{}\n----------------------------------------------------------",
                environment.getProperty("server.port"),
                InetAddress.getLocalHost().getHostAddress(),
                environment.getProperty("server.port")
        );
    }
}
