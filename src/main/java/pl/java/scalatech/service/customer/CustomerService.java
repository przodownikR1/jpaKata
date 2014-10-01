package pl.java.scalatech.service.customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pl.java.scalatech.entity.Customer;


/**
 * @author przodownik
 * Module name :    JpaKata
 * Creating time :  30 maj 2014
 */
public interface CustomerService {

    Page<Customer> getAllCustomers(Pageable pageable);
    Customer persistCustomer(Customer customer);
    void deleteCustomer(Customer customer);
   
}
