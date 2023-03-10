package es.jcoder.microjava.repositories;

import es.jcoder.microjava.dtos.UserDto;
import es.jcoder.microjava.entities.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long>, JpaRepository<User, Long> {

    //El query varía un poco de como se haría una petición SQL normal
    @Query("SELECT user FROM User user WHERE user.email=?1")
    Optional<User> findByUserEmail(String email);

    @Query("SELECT user FROM User user WHERE user.name LIKE ?1%")
    List<User> findByNameAndShort(String name, Sort sort);

    // Ejemplo con QueryMethods en lugar de la anotación @Query
    List<User> findByName(String name);

    Optional<User> findByEmailAndName(String email, String name);

    List<User> findByNameLike(String name); // Ejemplo buscando con LIKE

    List<User> findByNameOrEmail(String name, String email); // Busca por nombre o email

    List<User> findByBirthDateBetween(LocalDate begin, LocalDate end); // Busca entre fechas

    List<User> findByNameLikeOrderByIdDesc(String name); // Busca por nombre y ordena por id descendiente

    List<User> findByNameContainingOrderByIdDesc(String name); // Busca por nombre y ordena solo que ahora no hay que poner "%nombre%" cuando escribimos el parametro como con like

    //Esta es otra manera donde podemos usar la anotacion @Query junto con la anotacion @Param para agregar parametros nombrados a una query
    //En este caso tambien estamos retornando un DTO en lugar del user para especificar que datos queremos mostrar y no mostrar el user entero
    @Query("SELECT new es.jcoder.microjava.dtos.UserDto(user.id, user.name, user.birthDate) " +
            "FROM User user " +
            "WHERE user.birthDate=:paramBirthDate " +
            "AND user.email=:paramName")
    Optional<UserDto> getAllByBirthDateAndEmail(@Param("paramBirthDate") LocalDate date, @Param("paramName") String email);
}
