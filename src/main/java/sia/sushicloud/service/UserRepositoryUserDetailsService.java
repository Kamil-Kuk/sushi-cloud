package sia.sushicloud.service;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sia.sushicloud.model.User;
import sia.sushicloud.persistence.JpaUserRepository;

@Service
@NoArgsConstructor
public class UserRepositoryUserDetailsService implements UserDetailsService {

    @Autowired
    private JpaUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user != null){
            return user;
        }
        throw new UsernameNotFoundException("User " + username + " was not found");
    }
}
