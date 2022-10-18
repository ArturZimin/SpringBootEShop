package by.step.zimin.eshop.repository;

import by.step.zimin.eshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Long> {


    User findFirstByUserName(String username);
}
