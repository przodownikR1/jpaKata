package pl.java.scalatech.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.java.scalatech.entity.Customer;

/**
 * @author przodownik
 * Module name :    JpaKata
 * Creating time :  30 maj 2014
 */
public interface CustomerRepository extends JpaRepository<Customer, Long>{
   
}
