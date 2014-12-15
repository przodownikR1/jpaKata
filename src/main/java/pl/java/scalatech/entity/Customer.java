package pl.java.scalatech.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.Builder;
import lombok.extern.slf4j.Slf4j;


@Entity
@Data
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true)
@Builder
@AllArgsConstructor
@Slf4j
public class Customer extends Audit{

 
    private static final long serialVersionUID = 1L;
    private @NonNull String name;
    private @NonNull String login;
    private  BigDecimal salary;
    public Customer(){
        log.info("sdfSD");
        
    }
   
}


