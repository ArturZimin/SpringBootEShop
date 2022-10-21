package by.step.zimin.eshop.service;

import by.step.zimin.eshop.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    boolean save(UserDto userDto);

    List<UserDto> getAllUser();


}
