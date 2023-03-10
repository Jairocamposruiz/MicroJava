package es.jcoder.microjava.caseuse;

import es.jcoder.microjava.entities.User;
import es.jcoder.microjava.services.UserService;
import org.springframework.stereotype.Component;

@Component
public class UpdateUser {
    private final UserService userService;

    public UpdateUser(UserService userService) {
        this.userService = userService;
    }

    public User update(Long id, User newUser) {
        return userService.update(id, newUser);
    }
}
