package pl.java.scalatech.test;

import java.math.BigDecimal;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.fest.assertions.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import pl.java.scalatech.app.JpaKataApplication;
import pl.java.scalatech.config.JpaConfig;
import pl.java.scalatech.entity.Customer;
import pl.java.scalatech.entity.User;
import pl.java.scalatech.repository.UserRepository;
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
@Slf4j
public class ApplicationTests {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private UserRepository userRepository;
    @Before
    public void init(){
     userRepository.save(new User("przodownik"));
     userRepository.save(new User("toy"));
     userRepository.save(new User("boy"));
     
    }
    
    @Test
    public void contextLoads() {
        Assertions.assertThat(true);
    }

    @Test
    public void shouldInsertAndRetrieveData() throws InterruptedException {
        log.info("++++  user {}",userRepository.findAll());
        customerService.persistCustomer(Customer.builder().login("przodownik").name("slawek").salary(new BigDecimal(2342)).build());
        List<Customer> customers = customerService.getAllCustomers(new PageRequest(0, 1)).getContent();
        log.info("++++ Audit : {}",customers.get(0));
        Assertions.assertThat(customers).isNotEmpty().hasSize(1);

    }
}
