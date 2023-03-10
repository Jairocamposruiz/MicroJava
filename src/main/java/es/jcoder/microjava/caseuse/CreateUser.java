package es.jcoder.microjava.caseuse;

import es.jcoder.microjava.entities.User;
import es.jcoder.microjava.services.UserService;
import org.springframework.stereotype.Component;

@Component
public class CreateUser {
    private final UserService userService;

    public CreateUser(UserService userService) {
        this.userService = userService;
    }


    public User save(User newUser) {
        return userService.save(newUser);
    }
}
