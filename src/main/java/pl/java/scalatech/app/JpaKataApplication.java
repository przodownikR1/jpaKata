package pl.java.scalatech.app;

import java.math.BigDecimal;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.PageRequest;

import pl.java.scalatech.config.JpaConfig;
import pl.java.scalatech.entity.Customer;
import pl.java.scalatech.service.customer.CustomerService;

@Configuration
@ComponentScan(basePackages = "pl.java.scalatech")
@EnableAutoConfiguration
@PropertySource("classpath:application.properties")
@Slf4j
@Import(JpaConfig.class)
public class JpaKataApplication {

    @Autowired
    private CustomerService customerService;

    public static void main(String[] args) {
        SpringApplication.run(JpaKataApplication.class, args);
    }

    @Bean
    InitializingBean populateData(final CustomerService customerService) {
        return () -> {

            customerService.persistCustomer(Customer.builder().login("przodownik").name("slawek").salary(new BigDecimal(2342)).build());
            customerService.persistCustomer(Customer.builder().login("ironMike").name("tyson").salary(new BigDecimal(42)).build());
            customerService.persistCustomer(Customer.builder().login("royjones").name("jones").salary(new BigDecimal(52)).build());
            customerService.persistCustomer(Customer.builder().login("money").name("mayweather").salary(new BigDecimal(27642)).build());
            customerService.persistCustomer(Customer.builder().login("manny").name("pacquiao").salary(new BigDecimal(8244)).build());
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
