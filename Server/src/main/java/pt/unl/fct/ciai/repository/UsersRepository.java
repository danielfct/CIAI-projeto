package pt.unl.fct.ciai.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pt.unl.fct.ciai.model.User;

public interface UsersRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

    @Query("SELECT u "
            + "FROM User u "
            + "WHERE u.id LIKE CONCAT('%',:search,'%') "
            + "OR u.first_name LIKE CONCAT('%',:search,'%') "
            + "OR u.last_name LIKE CONCAT('%',:search,'%') "
            + "OR u.username LIKE CONCAT('%',:search,'%')"
            + "OR u.email LIKE CONCAT('%',:search,'%')"
            + "OR u.role LIKE CONCAT('%',:search,'%')")
    Iterable<User> searchUsers(@Param(value = "search") String search);
}