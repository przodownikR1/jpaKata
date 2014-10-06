package pl.java.scalatech.entity;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
public class User extends PKEntity{

  
    private static final long serialVersionUID = -874202160090991934L;

    public User(String login) {
        super();
        this.login = login;
    }

    private String login;
    
}
