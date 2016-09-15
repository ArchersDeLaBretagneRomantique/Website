package bzh.abr.config;

import bzh.abr.user.model.Role;
import bzh.abr.user.model.User;
import bzh.abr.user.repository.UserRepository;
import bzh.abr.util.UserEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Profile("test")
public class InitData {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        initUsers();
    }

    private void initUsers() {
        User admin = new User();
        admin.setUsername(UserEnum.ADMIN.getUsername());
        admin.setPassword(passwordEncoder.encode(UserEnum.ADMIN.getPassword()));
        admin.setRole(UserEnum.ADMIN.getRole());
        admin.setEnabled(true);
        admin.setLocked(false);
        userRepository.save(admin);

        User user = new User();
        user.setUsername(UserEnum.USER.getUsername());
        user.setPassword(passwordEncoder.encode(UserEnum.USER.getPassword()));
        user.setRole(UserEnum.USER.getRole());
        user.setEnabled(true);
        user.setLocked(false);
        userRepository.save(user);
    }
}
