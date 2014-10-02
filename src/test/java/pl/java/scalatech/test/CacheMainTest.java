package pl.java.scalatech.test;

import java.io.IOException;
import java.math.BigDecimal;

import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import pl.java.scalatech.app.JpaKataApplication;
import pl.java.scalatech.entity.Customer;
import pl.java.scalatech.service.customer.CustomerService;

@Slf4j
public class CacheMainTest {
    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

        ctx.register(JpaKataApplication.class);

        ctx.refresh();
        CustomerService customerService = (CustomerService) ctx.getBean("customerServiceImpl");

        customerService.persistCustomer(Customer.builder().login("przodownik").name("slawek").salary(new BigDecimal(2342)).build());
        customerService.persistCustomer(Customer.builder().login("ironMike").name("tyson").salary(new BigDecimal(42)).build());
        customerService.persistCustomer(Customer.builder().login("royjones").name("jones").salary(new BigDecimal(52)).build());
        customerService.persistCustomer(Customer.builder().login("money").name("mayweather").salary(new BigDecimal(27642)).build());
        customerService.persistCustomer(Customer.builder().login("manny").name("pacquiao").salary(new BigDecimal(8244)).build());
        log.info("{}", customerService.findByLogin("przodownik"));
        log.info("{}", customerService.findByLogin("przodownik"));
        log.info("{}", customerService.findByLogin("przodownik"));
        log.info("{}", customerService.findByLogin("przodownik"));
        log.info("{}", customerService.findByLogin("przodownik"));
        log.info("{}", customerService.findByLogin("money"));
        log.info("{}", customerService.findByLogin("money"));
        log.info("{}", customerService.findByLogin("money"));

        System.in.read();

    }
}
