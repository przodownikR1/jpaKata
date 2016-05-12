package pl.java.scalatech.entity;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.Builder;

/**
 * @author przodownik
 * Module name :    JpaKata
 * Creating time :  30 maj 2014
 */
@Entity
@Data
@ToString(callSuper = true,exclude="phones")
@Builder
@AllArgsConstructor
@Cacheable//
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)//
public class Customer extends PKEntity{

    private @NonNull String name;
    private @NonNull String login;
    private  BigDecimal salary;
    public Customer(){}

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="customerId")
    //@Cache (usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Phone> phones;

}


