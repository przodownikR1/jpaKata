package pl.java.scalatech.test;

import java.util.List;

import org.fest.assertions.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.java.scalatech.app.Application;
import pl.java.scalatech.config.JpaConfig;
import pl.java.scalatech.entity.Customer;
import pl.java.scalatech.repository.CustomerRepository;

/**
 * @author przodownik
 *         Module name : JpaKata
 *         Creating time : 30 maj 2014
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { Application.class, JpaConfig.class })
public class ApplicationTests {
    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void contextLoads() {
        Assertions.assertThat(true);
    }

    @Test
    public void shouldInsertAndRetrieveData() {
        customerRepository.save(new Customer("slawek", "borowiec"));
        List<Customer> customers = customerRepository.findAll();
        Assertions.assertThat(customers).isNotEmpty().hasSize(1);
    }
}
