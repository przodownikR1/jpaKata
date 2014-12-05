package pl.java.scalatech.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import pl.java.scalatech.entity.common.EntityCommon;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@Table(name="p_user")
public class User extends EntityCommon{

  
    private static final long serialVersionUID = -874202160090991934L;

    public User(String login) {
        super();
        this.login = login;
    }

    private String login;
    
}
