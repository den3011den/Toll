package bds;

import bds.dao.TrackPoint;
import bds.dao.repo.TrackRepository;
import bds.dto.PointDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@SpringBootApplication
@ComponentScan({"bds.services","bds.controllers", "bds"})
public class GpsTrackerCoreMain {

    public static void main(String[] args) {
        SpringApplication.run(GpsTrackerCoreMain.class, args);
    }


 }