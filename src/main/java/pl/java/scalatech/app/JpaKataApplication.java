package pl.java.scalatech.app;

import java.math.BigDecimal;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.PageRequest;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.config.JmxConfig;
import pl.java.scalatech.config.JpaConfig;
import pl.java.scalatech.entity.Customer;
import pl.java.scalatech.service.customer.CustomerService;

@Configuration
@ComponentScan(basePackages = "pl.java.scalatech")
@EnableAutoConfiguration
@PropertySource("classpath:application.properties")
@Slf4j
@Import(value={JpaConfig.class,JmxConfig.class})

public class JpaKataApplication {
    @Autowired
    private CustomerService customerService;

    public static void main(String[] args) {
        System.setProperty("spring.profiles.default", System.getProperty("spring.profiles.default", "test"));

        SpringApplication.run(JpaKataApplication.class, args);
    }

    @Bean
    @Profile("test")
    InitializingBean populateData(final CustomerService customerService) {
        return () -> {
           log.info("start populate data");
            log.info("{}", customerService.persistCustomer(Customer.builder().login("przodownik").name("slawek").salary(new BigDecimal(2342)).build()));
            log.info("{}", customerService.persistCustomer(Customer.builder().login("ironMike").name("tyson").salary(new BigDecimal(42)).build()));
            log.info("{}", customerService.persistCustomer(Customer.builder().login("royjones").name("jones").salary(new BigDecimal(52)).build()));
            log.info("{}", customerService.persistCustomer(Customer.builder().login("money").name("mayweather").salary(new BigDecimal(27642)).build()));
            log.info("{}", customerService.persistCustomer(Customer.builder().login("manny").name("pacquiao").salary(new BigDecimal(8244)).build()));
            log.info("{}", customerService.getAllCustomers(new PageRequest(0, 2)));
            log.info("{}", customerService.getAllCustomers(new PageRequest(1, 2)));
            log.info("{}", customerService.getAllCustomers(new PageRequest(3, 2)));
            log.info("{}", customerService.getAllCustomers(new PageRequest(0, 4)));
            log.info("{}", customerService.getAllCustomers(new PageRequest(0, 2)));


            Customer trash = customerService.persistCustomer(Customer.builder().login("trash").name("trash").salary(new BigDecimal(1)).build());

            log.info("{}", customerService.getAllCustomers(new PageRequest(0, 6)));
            customerService.deleteCustomer(trash);

        };
    }
}
