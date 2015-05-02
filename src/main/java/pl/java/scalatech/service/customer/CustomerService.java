package pl.java.scalatech.service.customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pl.java.scalatech.entity.test.TCustomer;


/**
 * @author przodownik
 * Module name :    JpaKata
 * Creating time :  30 maj 2014
 */
public interface CustomerService {

    Page<TCustomer> getAllCustomers(Pageable pageable);
    TCustomer persistCustomer(TCustomer customer);
    void deleteCustomer(TCustomer customer);
    TCustomer findByLogin(String login);
  
   
}
