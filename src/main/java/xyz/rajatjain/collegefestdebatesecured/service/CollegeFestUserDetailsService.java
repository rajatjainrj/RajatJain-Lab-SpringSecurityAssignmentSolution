package xyz.rajatjain.collegefestdebatesecured.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import xyz.rajatjain.collegefestdebatesecured.model.CollegeFestUserDetails;
import xyz.rajatjain.collegefestdebatesecured.model.User;
import xyz.rajatjain.collegefestdebatesecured.repository.UserRepository;

import java.util.Optional;

/**
 * @author rajatjain on - 27-03-2022
 * @project CollegeFestDebateSecured
 */
@Service
public class CollegeFestUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUserName(username);
        if(!userOptional.isPresent()){
            throw new UsernameNotFoundException("username not registered");
        }
        return new CollegeFestUserDetails(userOptional.get());
    }
}
