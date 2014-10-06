package pl.java.scalatech.entity;

import java.util.Date;

import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Audit {

    @CreatedDate
    private Date createdDate = new Date();

    @LastModifiedDate
    private Date lastModifiedDate = new Date();

    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Customer createdBy;

    @LastModifiedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Customer lastModifiedBy;

}
