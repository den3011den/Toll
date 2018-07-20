package bds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;

public class GpsTrackerCoreMainTest {

    public static void main(String[] args) {
        SpringApplication.run(GpsTrackerCoreMain.class, args);
    }
}