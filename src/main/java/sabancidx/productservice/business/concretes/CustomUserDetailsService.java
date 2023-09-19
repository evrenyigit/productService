package sabancidx.productservice.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sabancidx.productservice.dataAccess.UserRepository;
import sabancidx.productservice.entities.concretes.User;
import sabancidx.productservice.security.UserPrincipal;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userRepository
                .findByUsernameOrEmail(usernameOrEmail, usernameOrEmail).get();
        return UserPrincipal.create(user);
    }

    public UserPrincipal loadUserById(Long recordId) throws Exception {
        User user = userRepository.findById(recordId).get();
        return UserPrincipal.create(user);
    }
}