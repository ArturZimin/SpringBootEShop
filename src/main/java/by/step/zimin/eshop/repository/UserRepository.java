package by.step.zimin.eshop.repository;

import by.step.zimin.eshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User,Long> {


    User findFirstByUsername(String username);

    Optional<User> findUserByEmail(String email);
}
