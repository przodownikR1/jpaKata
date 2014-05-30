package pl.java.scalatech.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import pl.java.scalatech.config.JpaConfig;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@PropertySource("classpath:application.properties")
@Import(JpaConfig.class)
public class Application {
    
    

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
