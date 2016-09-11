package bzh.abr.user.repository;

import bzh.abr.user.model.User;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface UserRepository extends Repository<User, Long> {

    Optional<User> findByUsername(String username);

}
