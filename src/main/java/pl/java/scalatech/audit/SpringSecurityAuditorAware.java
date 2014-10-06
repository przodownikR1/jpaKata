package pl.java.scalatech.audit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import pl.java.scalatech.entity.Customer;
import pl.java.scalatech.service.customer.CustomerService;

@Component
public class SpringSecurityAuditorAware implements AuditorAware<Customer> {

    @Autowired private CustomerService  customerService;

    @Override
    public Customer getCurrentAuditor() {
        return customerService.getCurrentAccount();
    }

}