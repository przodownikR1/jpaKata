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
import pl.java.scalatech.config.JpaEmbeddedConfig;
import pl.java.scalatech.entity.test.TCustomer;
import pl.java.scalatech.entity.test.TPhone;
import pl.java.scalatech.repository.UserRepository;
import pl.java.scalatech.service.customer.CustomerService;

import com.google.common.collect.Lists;

/**
 * @author przodownik
 *         Module name : JpaKata
 *         Creating time : 30 maj 2014
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { JpaKataApplication.class, JpaEmbeddedConfig.class })
@ActiveProfiles(value = "test")
@Transactional
@Slf4j
public class ApplicationTests {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private UserRepository userRepository;

    @Before
    public void init() {
        // userRepository.save(new User("przodownik"));
        // userRepository.save(new User("toy"));
        // userRepository.save(new User("boy"));

    }

    @Test
    public void contextLoads() {
        Assertions.assertThat(true);
    }

    @Test
    public void shouldInsertAndRetrieveData() throws InterruptedException {
        log.info("++++  user {}", userRepository.findAll());
        customerService.persistCustomer(TCustomer.builder().login("przodownik").name("slawek").salary(new BigDecimal(2342)).build());
        List<TCustomer> customers = customerService.getAllCustomers(new PageRequest(0, 1)).getContent();
        log.info("++++ Audit : {}", customers.get(0));
        Assertions.assertThat(customers).isNotEmpty().hasSize(1);

    }

    @Test
    public void shouldOne2ManyWork() {
        List<TPhone> phones = Lists.newArrayList(new TPhone("5555"), new TPhone("6666"));
        TCustomer c = TCustomer.builder().login("przodownikR1").name("slawekR1").salary(new BigDecimal(2342)).build();
        c.setPhones(phones);
        customerService.persistCustomer(c);
        TCustomer przodownikR1 = customerService.findByLogin("przodownikR1");
        log.info(" +++++       phones {}", przodownikR1.getPhones());
        System.err.println("test");
        for (TPhone p : przodownikR1.getPhones()) {
            log.info("+++  {}", p);
        }

    }
}
