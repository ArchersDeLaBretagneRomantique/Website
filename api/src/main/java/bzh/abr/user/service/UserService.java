package bzh.abr.user.service;

import bzh.abr.user.exception.UserAlreadyExistsException;
import bzh.abr.user.model.Role;
import bzh.abr.user.model.User;
import bzh.abr.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);

        if (!user.isPresent()) throw new UsernameNotFoundException("No user found with username: " + username);

        return user.get();
    }

    public void addUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) throw new UserAlreadyExistsException("User already existe");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        user.setEnabled(false);
        user.setLocked(false);
        userRepository.save(user);
    }
}
