package pl.java.scalatech.test;

import java.io.IOException;
import java.math.BigDecimal;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.google.common.collect.Sets;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.app.JpaKataApplication;
import pl.java.scalatech.entity.Customer;
import pl.java.scalatech.entity.Phone;
import pl.java.scalatech.service.customer.CustomerService;

@Slf4j
public class CacheMainTest {
    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

        ctx.register(JpaKataApplication.class);

        ctx.refresh();
        CustomerService customerService = (CustomerService) ctx.getBean("customerServiceImpl");


        customerService.persistCustomer(Customer.builder().login("przodownik").name("slawek").salary(new BigDecimal(2342))
                .phones(Sets.newHashSet(new Phone("6123744353"),new Phone("6123744354"),new Phone("6123744356"))).build());
        customerService.persistCustomer(Customer.builder().login("ironMike").name("tyson").salary(new BigDecimal(42))
                .phones(Sets.newHashSet(new Phone("1123744353"),new Phone("2123744354"),new Phone("3123744356"))).build());
        customerService.persistCustomer(Customer.builder().login("royjones").name("jones").salary(new BigDecimal(52))
                .phones(Sets.newHashSet(new Phone("5123744353"),new Phone("4123744354"),new Phone("7723744356"))).build());
        customerService.persistCustomer(Customer.builder().login("money").name("mayweather").salary(new BigDecimal(27642))
                .phones(Sets.newHashSet(new Phone("612374553"),new Phone("644354"),new Phone("344356"))).build());
        customerService.persistCustomer(Customer.builder().login("manny").name("pacquiao").salary(new BigDecimal(8244))
                .phones(Sets.newHashSet(new Phone("853"),new Phone("754"))).build());
        log.info("{} , phones : {}", customerService.findByLoginFetch("przodownik"),customerService.findByLoginFetch("przodownik").getPhones());
        log.info("{} , phones : {}", customerService.findByLoginFetch("przodownik"),customerService.findByLoginFetch("przodownik").getPhones());
        log.info("{} , phones : {}", customerService.findByLoginFetch("przodownik"),customerService.findByLoginFetch("przodownik").getPhones());
        log.info("{} , phones : {}", customerService.findByLoginFetch("przodownik"),customerService.findByLoginFetch("przodownik").getPhones());
        log.info("{} , phones : {}", customerService.findByLoginFetch("przodownik"),customerService.findByLoginFetch("przodownik").getPhones());
        log.info("{} , phones : {}", customerService.findByLoginFetch("money"),customerService.findByLoginFetch("money").getPhones());
        log.info("{} , phones : {}", customerService.findByLoginFetch("money"),customerService.findByLoginFetch("money").getPhones());
        log.info("{} , phones : {}", customerService.findByLoginFetch("money"),customerService.findByLoginFetch("money").getPhones());

        log.info("{} , phones : {}", customerService.findByLoginFetch("manny"),customerService.findByLoginFetch("manny").getPhones());
        log.info("{} , phones : {}", customerService.findByLoginFetch("manny"),customerService.findByLoginFetch("manny").getPhones());
        log.info("{} , phones : {}", customerService.findByLoginFetch("manny"),customerService.findByLoginFetch("manny").getPhones());
        log.info("{} , phones : {}", customerService.findByLoginFetch("manny"),customerService.findByLoginFetch("manny").getPhones());
        log.info("{} , phones : {}", customerService.findByLoginFetch("manny"),customerService.findByLoginFetch("manny").getPhones());

        System.in.read();

    }
}
