package pl.java.scalatech.entity;

import java.math.BigDecimal;

import javax.persistence.Cacheable;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.Builder;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * @author przodownik
 * Module name :    JpaKata
 * Creating time :  30 maj 2014
 */
@Entity
@Data
@ToString(callSuper = true)
@Builder
@AllArgsConstructor
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Customer extends PKEntity{

    private @NonNull String name;
    private @NonNull String login;
    private  BigDecimal salary;
    public Customer(){}
   
}


