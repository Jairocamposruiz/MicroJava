package es.jcoder.microjava.caseuse;

import es.jcoder.microjava.entities.User;
import es.jcoder.microjava.services.UserService;

import java.util.List;

public class GetUserImplement implements GetUser {
    private UserService userService;

    public GetUserImplement(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<User> getAll() {
        return userService.getAllUsers();
    }
}
