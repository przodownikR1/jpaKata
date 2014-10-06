package pl.java.scalatech.test;

import java.math.BigDecimal;
import java.util.List;

import org.fest.assertions.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import pl.java.scalatech.app.JpaKataApplication;
import pl.java.scalatech.config.JpaConfig;
import pl.java.scalatech.entity.Customer;
import pl.java.scalatech.repository.CustomerRepository;
import pl.java.scalatech.service.customer.CustomerService;

/**
 * @author przodownik
 *         Module name : JpaKata
 *         Creating time : 30 maj 2014
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { JpaKataApplication.class, JpaConfig.class })
@ActiveProfiles(value = "dev")
@Transactional
public class ApplicationTests {
    @Autowired
    private CustomerService customerService;

    @Test
    public void contextLoads() {
        Assertions.assertThat(true);
    }

    @Test
    public void shouldInsertAndRetrieveData() throws InterruptedException {
        customerService.persistCustomer(Customer.builder().login("przodownik").name("slawek").salary(new BigDecimal(2342)).build());
        List<Customer> customers = customerService.getAllCustomers(new PageRequest(0, 1)).getContent();

        Assertions.assertThat(customers).isNotEmpty().hasSize(1);

    }
}
