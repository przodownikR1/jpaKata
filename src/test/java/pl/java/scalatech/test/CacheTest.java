package pl.java.scalatech.test;

import java.io.IOException;
import java.math.BigDecimal;

import lombok.extern.slf4j.Slf4j;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import pl.java.scalatech.app.JpaKataApplication;
import pl.java.scalatech.entity.Customer;
import pl.java.scalatech.service.customer.CustomerService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { JpaKataApplication.class})
@ActiveProfiles(value="dev")
@Transactional
@Slf4j
public class CacheTest {
    @Autowired
    private CustomerService customerService;
    
    @Before
    public void init(){
        customerService.persistCustomer(Customer.builder().login("przodownik").name("slawek").salary(new BigDecimal(2342)).build());
        customerService.persistCustomer(Customer.builder().login("ironMike").name("tyson").salary(new BigDecimal(42)).build());
        customerService.persistCustomer(Customer.builder().login("royjones").name("jones").salary(new BigDecimal(52)).build());
        customerService.persistCustomer(Customer.builder().login("money").name("mayweather").salary(new BigDecimal(27642)).build());
        customerService.persistCustomer(Customer.builder().login("manny").name("pacquiao").salary(new BigDecimal(8244)).build());
       
    }
    @Test

    public void shouldCacheWorkWhenSearchByLogin(){
       
        log.info("{}",customerService.findByLogin("przodownik"));
        log.info("{}",customerService.findByLogin("przodownik"));
        log.info("{}",customerService.findByLogin("money"));
        log.info("{}",customerService.findByLogin("money"));
        log.info("{}",customerService.findByLogin("money"));
        
    }
    @Test
   
    public void shouldCacheWorkWhenSearchByName() throws IOException{
       
        log.info("{}",customerService.findByNameLike("slawek"));
        log.info("{}",customerService.findByNameLike("slawek"));
        log.info("{}",customerService.findByNameLike("sla"));
        log.info("{}",customerService.findByNameLike("sla"));
        log.info("{}",customerService.findByNameLike("sla"));
     
    }
}
