package by.step.zimin.eshop.service.impl;

import by.step.zimin.eshop.dto.UserDto;
import by.step.zimin.eshop.model.User;
import by.step.zimin.eshop.repository.UserRepository;
import by.step.zimin.eshop.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean save(UserDto userDto) {

        //собираем нашего юзера
        User user = User.builder()
                .userName(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .email(userDto.getEmail())
                .role(userDto.getRole())
                .address(userDto.getAddress())
                .phone(userDto.getPhone())
                .build();
        userRepository.save(user);
        return true;
    }


    /**
     находим юзера в bd
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findFirstByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with name:" + username);
        }

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(user.getRole().name()));

        return new org.springframework.security.core.userdetails.User(
                user.getUserName(),
                user.getPassword(),
                roles
        );

    }
}