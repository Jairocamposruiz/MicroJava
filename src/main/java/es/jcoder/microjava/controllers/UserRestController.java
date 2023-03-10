package es.jcoder.microjava.controllers;

import es.jcoder.microjava.caseuse.CreateUser;
import es.jcoder.microjava.caseuse.DeleteUser;
import es.jcoder.microjava.caseuse.GetUser;
import es.jcoder.microjava.caseuse.UpdateUser;
import es.jcoder.microjava.entities.User;
import es.jcoder.microjava.repositories.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// RestController ereda de Controller y amplia la funcionalidad formateando todos los
// retornos a JSON
@RestController
@RequestMapping("/users")
public class UserRestController {

    private final GetUser getUser;
    private final CreateUser createUser;
    private final DeleteUser deleteUser;
    private final UpdateUser updateUser;
    private final UserRepository userRepository;

    public UserRestController(GetUser getUser, CreateUser createUser, DeleteUser deleteUser, UpdateUser updateUser, UserRepository userRepository) {
        this.getUser = getUser;
        this.createUser = createUser;
        this.deleteUser = deleteUser;
        this.updateUser = updateUser;
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    List<User> getAll() {
        return getUser.getAll();
    }

    @PostMapping("/")
    ResponseEntity<User> create(@RequestBody User newUser) {
        return new ResponseEntity<>(createUser.save(newUser), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    ResponseEntity delete(@PathVariable Long id) {
        deleteUser.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Aqui le estamos pasando un user entero lo cual no tiene mucho sentido pero lo correcto
    // es que en todos estos metodos tener un DTO para lo que necesitemos ejemplo un user con
    // parametros opcionales y sin poder asignarle el id para no reemplazarlo
    @PutMapping("/{id}")
    ResponseEntity<User> update(@PathVariable Long id, @RequestBody User newUser) {
        return new ResponseEntity<>(updateUser.update(id, newUser), HttpStatus.OK);
    }

    //Con RequestParam obtenemos los Query parameters
    @GetMapping("/pageable")
    List<User> getUserPageable(@RequestParam int page, @RequestParam int size) {
        PageRequest pageable = PageRequest.of(page, size);
        return userRepository.findAll(pageable).getContent();
    }
}
