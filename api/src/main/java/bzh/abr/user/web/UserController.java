package bzh.abr.user.web;

import bzh.abr.user.exception.UserException;
import bzh.abr.user.model.User;
import bzh.abr.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    @Transactional
    public ResponseEntity<Void> addUser(@Validated @RequestBody User user) {
        if (userService.exists(user)) {
            throw new UserException("User already existe");
        }
        userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
