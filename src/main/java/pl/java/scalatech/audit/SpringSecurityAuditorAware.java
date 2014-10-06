package pl.java.scalatech.audit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import pl.java.scalatech.entity.User;
import pl.java.scalatech.repository.UserRepository;

@Component
public class SpringSecurityAuditorAware implements AuditorAware<User> {

    @Autowired private UserRepository userRepository;
    
    @Override
    public User getCurrentAuditor() {
        User user = userRepository.findAll().get(0);  
        return user;
    }

}