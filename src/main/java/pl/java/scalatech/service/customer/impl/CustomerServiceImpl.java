package pl.java.scalatech.service.customer.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.java.scalatech.entity.test.TCustomer;
import pl.java.scalatech.repository.CustomerRepository;
import pl.java.scalatech.service.customer.CustomerService;

/**
 * @author przodownik
 *         Module name : JpaKata
 *         Creating time : 30 maj 2014
 */
@Transactional(readOnly = true)
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Page<TCustomer> getAllCustomers(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public TCustomer persistCustomer(TCustomer customer) {
        return customerRepository.save(customer);
    }

    @Override
    @Transactional
    public void deleteCustomer(TCustomer customer) {
        customerRepository.delete(customer);
    }

    @Override
    public TCustomer findByLogin(String login) {
        return customerRepository.findByLogin(login);
    }

}
