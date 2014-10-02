package pl.java.scalatech.repository;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;

import pl.java.scalatech.entity.Customer;

/**
 * @author przodownik
 * Module name :    JpaKata
 * Creating time :  30 maj 2014
 */
public interface CustomerRepository extends JpaRepository<Customer, Long>{
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    Customer findByLogin(String name);
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    Customer findByNameLike(String name);
}
