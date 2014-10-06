package pl.java.scalatech.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true)
@Builder
@AllArgsConstructor
public class Customer extends Audit{

 
    private static final long serialVersionUID = 1L;
    private @NonNull String name;
    private @NonNull String login;
    private  BigDecimal salary;
    public Customer(){}
   
}


