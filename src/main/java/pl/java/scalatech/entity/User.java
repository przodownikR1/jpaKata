package pl.java.scalatech.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.java.scalatech.entity.common.EntityCommon;

@Entity
@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@Table(name="p_user")
public class User extends EntityCommon<Long>{

  
    private static final long serialVersionUID = -874202160090991934L;

    public User(String login) {
        super();
        this.login = login;
    }

    private String login;
    
}
