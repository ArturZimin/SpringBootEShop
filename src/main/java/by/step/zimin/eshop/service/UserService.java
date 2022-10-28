package by.step.zimin.eshop.service;

import by.step.zimin.eshop.dto.UserDto;
import by.step.zimin.eshop.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    boolean save(UserDto userDto);

    void save(User user);

    List<UserDto> getAllUser();


    User findByName(String name);


    void updateUser(UserDto userDto);

    User userDtoToUser(UserDto userDto);

    UserDto toDto(User user);

    Boolean deleteProductFromBucketById(Long productId, Long userId);

    void deleteUser(Long userId);
}
