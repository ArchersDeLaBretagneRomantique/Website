package bzh.abr.user;

import bzh.abr.Application;
import bzh.abr.user.model.Role;
import bzh.abr.user.model.User;
import bzh.abr.user.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
@ActiveProfiles("test")
public class UserTest {

    @Value("${local.server.port}")
    private int serverPort;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void shouldAddUser() {
        Map<String, String> user = new HashMap<>();
        user.put("username", "testusr");
        user.put("password", "testpwd");
        user.put("role", Role.ADMIN);
        user.put("enabled", "true");
        user.put("locked", "true");

        RestTemplate template = new RestTemplate();
        ResponseEntity<Void> resp = template.postForEntity("http://localhost:" + serverPort + "/api/users", user, Void.class);

        Assert.assertEquals(HttpStatus.CREATED, resp.getStatusCode());

        Optional<User> storedUser = userRepository.findByUsername("testusr");
        Assert.assertTrue(storedUser.isPresent());
        Assert.assertTrue(passwordEncoder.matches("testpwd", storedUser.get().getPassword()));
        Assert.assertEquals(storedUser.get().getRole(), Role.USER);
        Assert.assertEquals(storedUser.get().isEnabled(), false);
        Assert.assertEquals(storedUser.get().isLocked(), false);
    }

    public void shouldNotAddTwoUsersWithSameUsername() {
        Map<String, String> user = new HashMap<>();
        user.put("username", "testusr");
        user.put("password", "testpwd");

        RestTemplate template = new RestTemplate();
        ResponseEntity<Void> resp = template.postForEntity("http://localhost:" + serverPort + "/api/users", user, Void.class);

        Assert.assertEquals(HttpStatus.CREATED, resp.getStatusCode());

        user.put("password", "anotherpwd");
        resp = template.postForEntity("http://localhost:" + serverPort + "/api/users", user, Void.class);

        Assert.assertEquals(HttpStatus.CONFLICT, resp.getStatusCode());
    }
}
