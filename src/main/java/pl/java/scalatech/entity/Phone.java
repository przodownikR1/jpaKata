package pl.java.scalatech.entity;

import javax.persistence.Cacheable;
import javax.persistence.Entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Builder;

@Entity
@Data
@ToString(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Cacheable//
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)//
public class Phone extends PKEntity{

    private String phoneNumber;

}
