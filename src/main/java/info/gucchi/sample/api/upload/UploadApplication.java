package info.gucchi.sample.api.upload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.util.logging.Logger;

@SpringBootApplication
@EnableConfigurationProperties({FileStorageProperties.class})
public class UploadApplication {

    public static void main(String[] args) {
        SpringApplication.run(UploadApplication.class, args);
        Logger logger = Logger.getLogger("main");
        logger.info("main");
        System.out.println("hello");
    }

}
