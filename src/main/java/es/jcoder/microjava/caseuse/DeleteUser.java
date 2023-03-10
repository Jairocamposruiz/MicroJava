package es.jcoder.microjava.caseuse;

import es.jcoder.microjava.services.UserService;
import org.springframework.stereotype.Component;

@Component
public class DeleteUser {
    private final UserService userService;

    public DeleteUser(UserService userService) {
        this.userService = userService;
    }

    public void delete(Long id) {
        userService.delete(id);
    }
}
