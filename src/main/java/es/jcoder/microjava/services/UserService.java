package es.jcoder.microjava.services;

import es.jcoder.microjava.entities.User;
import es.jcoder.microjava.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final Log LOGGER = LogFactory.getLog(UserService.class);

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Con la anotacion @Transactional si existe en algun punto de la funcion un error
    // se encarga de hacer rollback de las acciones anteriores en la base de datos
    @Transactional
    public void saveTransactional(List<User> users) {
        users
                .stream()
                .peek(user -> LOGGER.info("Usuario insertado:" + user))
                .forEach(user -> userRepository.save(user));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User save(User newUser) {
        return userRepository.save(newUser);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public User update(Long id, User newUser) {
        return userRepository.findById(id)
                .map(user -> {
                            user.setEmail(newUser.getEmail());
                            user.setBirthDate(newUser.getBirthDate());
                            user.setName(newUser.getName());
                            return userRepository.save(user);
                        }
                )
                .get();
    }
}
