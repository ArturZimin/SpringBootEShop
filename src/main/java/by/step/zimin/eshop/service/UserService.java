package by.step.zimin.eshop.service;

import by.step.zimin.eshop.dto.UserDto;
import by.step.zimin.eshop.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService{


    boolean userExists(String email);

    boolean save(UserDto userDto);

    User save(User user);

    List<UserDto> getAllUser();

    User registration(UserDto userDto);

    User findByName(String name);

    void updateUser(UserDto userDto);

    User userDtoToUser(UserDto userDto);

    UserDto toDto(User user);

    Boolean deleteProductFromBucketById(Long productId, Long userId);

    void deleteUser(Long userId);

    void mapUserDtoToUser(UserDto userDto, User user);
}
