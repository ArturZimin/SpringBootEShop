package by.step.zimin.eshop.service;

import by.step.zimin.eshop.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    boolean save(UserDto userDto);
}
