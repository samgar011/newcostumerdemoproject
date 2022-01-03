package costumer.demo.newcostumerpro.service;
import costumer.demo.newcostumerpro.model.CustomUserDetail;
import costumer.demo.newcostumerpro.model.User;
import costumer.demo.newcostumerpro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CostumerUserDetailService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByEmail(email);
        user.orElseThrow(() -> new UsernameNotFoundException("Wrong Email"));
        return user.map(CustomUserDetail::new).get();
    }




}
