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
import java.util.Objects;
import java.util.stream.Collectors;

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
        if (validationUserDto(userDto)){
        User user = userDtoToUser(userDto);
           userRepository.save(user);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public List<UserDto> getAllUser() {
        return userRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }



    public User userDtoToUser(UserDto userDto) {
        //собираем нашего юзера из dto
        User user = User.builder()
                .userName(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .email(userDto.getEmail())
                .role(userDto.getRole())
                .address(userDto.getAddress())
                .phone(userDto.getPhone())
                .build();
        return user;
    }

    public UserDto toDto(User user) {
        return UserDto.builder()
                .username(user.getUserName())
                .email(user.getEmail())
                .address(user.getAddress())
                .role(user.getRole())
                .phone(user.getPhone())
                .build();
    }


    public Boolean validationUserDto(UserDto userDto){

        if (validationName(userDto.getUsername())&&
        validationEmail(userDto.getEmail())&&
//        validationPassword(userDto.getPassword())&&
        validationPhone(userDto.getPhone())){
            return true;
        }
        else {
           return false;
        }
    }

    public Boolean validationPhone(String phone) {
        String checkPhone = "[\\d+]{7,15}";
        if (phone.matches(checkPhone)) {
            System.out.println("phone true");
            return true;
        } else {
            return false;
        }
    }


    public Boolean validationName(String name) {
        String checkName = "([a-zA-Z ]{5,20})";
        if (name.matches(checkName)) {
            System.out.println("name true");

            return true;
        } else {
            return false;
        }
    }



    public Boolean validationEmail(String email) {
        String checkEmail = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w{2,8})+$";
        if (email.matches(checkEmail)) {
            System.out.println("email true");

            return true;
        } else {
            return false;
        }
    }

    public Boolean validationPassword(String password) {
        String pass = password;
        String check1 = "[A-Z]+";
        String check2 = "[a-z]+";
        String check3 = "[\\W]+";
        if (pass.matches(check1) &&
                pass.matches(check2) &&
                pass.matches(check3) &&
                pass.length() >= 4 &&
                pass.length() <= 20) {
            System.out.println("password true");

            return true;
        } else {
            return false;
        }
    }


    /**
     * находим юзера в bd
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