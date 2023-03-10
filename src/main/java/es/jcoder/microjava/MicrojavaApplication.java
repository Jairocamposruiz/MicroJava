package es.jcoder.microjava;

import es.jcoder.microjava.beans.MyBeanDependency;
import es.jcoder.microjava.beans.MyBeanWithDependenciesDependency;
import es.jcoder.microjava.beans.MyBeanWithProperties;
import es.jcoder.microjava.components.ComponentDependency;
import es.jcoder.microjava.dtos.UserDto;
import es.jcoder.microjava.entities.User;
import es.jcoder.microjava.pojos.UserPojo;
import es.jcoder.microjava.repositories.UserRepository;
import es.jcoder.microjava.services.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class MicrojavaApplication implements CommandLineRunner {

//    Esta es la manera en la que lo he visto siempre usar sin necesidad de constructor pero parece que aconsejan
//    usarlo con constructor porque para testear es mas facil de inyectar los mocks es mas si usamos esta manera
//    el ide nos recomienda que lo cambiemos a el modo con Constructor
//    @Autowired
//    @Qualifier("componentTwoImplement")
//    private ComponentDependency componentDependency;
//
//    @Autowired
//    private MyBeanDependency myBeanDependency;
//
//    @Autowired
//    private MyBeanWithDependenciesDependency myBeanWithDependenciesDependency;
//
//    @Autowired
//    private MyBeanWithProperties myBeanWithProperties;


    //    El autowired en versiones recientes de spring ya no es necesario ponerlo en el constructor en caso de ponerlo tampoco afecta a mal
//    La anotación @Qualifier sirve para especificar que implementacion usar si tenemos 2 que implementen la misma interfaz
    private final ComponentDependency componentDependency;
    private final MyBeanDependency myBeanDependency;
    private final MyBeanWithDependenciesDependency myBeanWithDependenciesDependency;
    private final MyBeanWithProperties myBeanWithProperties;
    private final UserPojo userPojo;
    private final UserRepository userRepository;
    private final UserService userService;

    private final Log LOGGER = LogFactory.getLog(MicrojavaApplication.class);

    @Autowired
    public MicrojavaApplication(
            @Qualifier("componentTwoImplement") ComponentDependency componentDependency,
            MyBeanDependency myBeanDependency,
            MyBeanWithDependenciesDependency myBeanWithDependenciesDependency,
            MyBeanWithProperties myBeanWithProperties,
            UserPojo userPojo,
            UserRepository userRepository,
            UserService userService
    ) {
        this.componentDependency = componentDependency;
        this.myBeanDependency = myBeanDependency;
        this.myBeanWithDependenciesDependency = myBeanWithDependenciesDependency;
        this.myBeanWithProperties = myBeanWithProperties;
        this.userPojo = userPojo;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public static void main(String[] args) {
        SpringApplication.run(MicrojavaApplication.class, args);
    }

    // Este metodo run es solo a modo de practica para la clase para tener una manera de probar cosas en consola
    // para tenerlo hemos tenido que implementar CommandLineRunner en la clase
    @Override
    public void run(String... args) throws Exception {
//        Descomentar la que queramos probar

//        someActions();
//
//        saveUsersInDatabase();
//
//        saveWidthErrorTransactional();
    }

    // Simplemente estamos ejecutando cosas de otras clases para ver como funciona la inyeccion de dependencias
    private void someActions() {
        componentDependency.saludar();
        myBeanDependency.print();
        myBeanWithDependenciesDependency.printWithDependency();
        System.out.println(myBeanWithProperties.function());
        System.out.println(userPojo.getAge());
        System.out.println(userPojo.getEmail());
        System.out.println(userPojo.getPassword());
        LOGGER.error("This is an error");
        LOGGER.info(("This is only info"));
        LOGGER.debug("This is necessary info if we want to debug");
    }

    private void saveUsersInDatabase() {
        User user1 = new User("Jairo", "Jairo@email.com", LocalDate.of(1991, 6, 24));
        User user2 = new User("Sandra", "Sandra@email.com", LocalDate.of(1998, 3, 20));
        User user3 = new User("Antonio", "Antonio@email.com", LocalDate.of(1992, 7, 20));
        User user4 = new User("Alfonsa", "Alfonsa@email.com", LocalDate.of(1993, 8, 20));
        User user5 = new User("User1", "Juan@email.com", LocalDate.of(1994, 9, 20));
        User user6 = new User("User3", "Beatriz@email.com", LocalDate.of(1995, 10, 20));
        User user7 = new User("User2", "Maria@email.com", LocalDate.of(1996, 11, 20));
        User user8 = new User("Miguel", "Miguel@email.com", LocalDate.of(1997, 12, 20));
        User user9 = new User("Pedro", "Pedro@email.com", LocalDate.of(1918, 1, 20));
        User user10 = new User("Kira", "Kira@email.com", LocalDate.of(1928, 2, 20));

        List<User> usersList = Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8, user9, user10);
//        Guardamos usuarios
        System.out.println();
        userRepository.saveAll(usersList);

//        Obtenemos usuarios y ordenarlos por fecha de nacimiento
        System.out.println();
        List<User> users = userRepository.findAll(Sort.by("birthDate").descending());
        System.out.println();
        users.forEach(user -> System.out.println(user));

//        Obtener usuario por email
        System.out.println();
        Optional<User> myUser = Optional.ofNullable(userRepository
                .findByUserEmail("Jairo@email.com")
                .orElseThrow(() -> new RuntimeException("There is no user with this email")));
        System.out.println();
        System.out.println("El usuario es: " + myUser);

//        Obtener usuarios por coincidencia en nombre y ordenados por nombre
        System.out.println();
        List<User> usersByName = userRepository.findByNameAndShort("Use", Sort.by("name").descending());
        System.out.println();
        usersByName.forEach(user -> System.out.println(user));

//        Obtener usuarios con nombre como
        System.out.println();
        userRepository.findByNameLike("%ser%")
                .forEach((user) -> System.out.println(user));

//        Busca por nombre o email
        System.out.println();
        userRepository.findByNameOrEmail(null, "Sandra@email.com")
                .forEach((user) -> System.out.println(user));

//        Busca entre fechas
        System.out.println();
        LocalDate initDate = LocalDate.of(1990, 1, 1);
        LocalDate endDate = LocalDate.of(1995, 1, 1);
        userRepository.findByBirthDateBetween(initDate, endDate)
                .forEach((user) -> System.out.println(user));

//        Busca por nombre y ordena descendientemente por id
        System.out.println();
        userRepository.findByNameLikeOrderByIdDesc("%ser%")
                .forEach((user) -> System.out.println(user));

//        Busca por nombre y ordena descendientemente por id
        System.out.println();
        userRepository.findByNameContainingOrderByIdDesc("ser")
                .forEach((user) -> System.out.println(user));

//        Busca por fecha de nacimiento e email
        System.out.println();
        LocalDate date = LocalDate.of(1991, 6, 24);
        String email = "Jairo@email.com";
//        Retorna un optional pero como usamos el orElseThrow se supone que si no hay lanza error
//        por ello si asigna siempre va a ser un UserDto
        UserDto userByEmailAndDate = userRepository.getAllByBirthDateAndEmail(date, email)
                .orElseThrow(() -> new RuntimeException("No se encontro el usuario"));
        System.out.println(userByEmailAndDate);
    }

    private void saveWidthErrorTransactional() {
        // Para generar un error vamos a tener 2 email iguales en el usuario 1 y el 3
        User test1 = new User("TestTransactional1", "TestTransactional1@domain.com", LocalDate.now());
        User test2 = new User("TestTransactional2", "TestTransactional2@domain.com", LocalDate.now());
        User test3 = new User("TestTransactional3", "TestTransactional1@domain.com", LocalDate.now());
        User test4 = new User("TestTransactional4", "TestTransactional4@domain.com", LocalDate.now());

        List<User> users = Arrays.asList(test1, test2, test3, test4);
        // Vamos a lanzar el guardado de los usuarios pero va a fallar por el email repetido
        try {
            userService.saveTransactional(users);
        } catch(Exception error) {
            LOGGER.error("Error al guardar los usuarios, se hace rollback de todos");
        }

        // Ejecutamos la busqueda de todos los usuarios pero no retorna ninguno porque hizo rollback de todos aunque el fallo lo diera el tercero
        userService.getAllUsers().forEach(user -> LOGGER.info("Este es el usuario dentro del metodo transaccional" + user));
    }
}
