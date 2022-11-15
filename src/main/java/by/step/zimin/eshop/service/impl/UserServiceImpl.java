package by.step.zimin.eshop.service.impl;


import by.step.zimin.eshop.dto.UserDto;
import by.step.zimin.eshop.model.Bucket;
import by.step.zimin.eshop.model.Product;
import by.step.zimin.eshop.model.Role;
import by.step.zimin.eshop.model.User;
import by.step.zimin.eshop.repository.ProductRepository;
import by.step.zimin.eshop.repository.UserRepository;
import by.step.zimin.eshop.service.EmailService;
import by.step.zimin.eshop.service.ProductService;
import by.step.zimin.eshop.service.UserService;
import by.step.zimin.eshop.service.VerificationTokenService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final VerificationTokenService verificationTokenService;
    private final PasswordEncoder passwordEncoder;
    private final ProductRepository productRepository;
    private final EmailService emailService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, VerificationTokenService verificationTokenService, PasswordEncoder passwordEncoder, ProductRepository productRepository, EmailService emailService) {

        this.userRepository = userRepository;
        this.verificationTokenService = verificationTokenService;
        this.passwordEncoder = passwordEncoder;
        this.productRepository = productRepository;
        this.emailService = emailService;
    }

    public boolean userExists(String email) {
        return userRepository.findUserByEmail(email).isPresent();
    }

    @Override
    @Transactional
    public boolean save(UserDto userDto) {

        //if create or register
        if (validationUserDto(userDto)) {
            User user = userDtoToUser(userDto);
            userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }

    @Override
    @Transactional
    public User save(User user) {
       return userRepository.save(user);
    }

    @Override
    public List<UserDto> getAllUser() {
        return userRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public User findByName(String name) {

        return  userRepository.findFirstByUsername(name) ;

    }

    @Override
    @Transactional
    public void updateUser(UserDto userDto) {
        if (userDto.getId() != null) {
            Role roleUser = userRepository.findById(userDto.getId()).get().getRole();
            Boolean enable=userRepository.findById(userDto.getId()).get().getEnable();
            Bucket bucket=userRepository.findById(userDto.getId()).get().getBucket();
            userDto.setRole(roleUser);
            userDto.setBucket(bucket);
            userDto.setEnable(enable);
            User user = userDtoToUser(userDto);
            userRepository.save(user);
        }

    }

    @Override
    public User userDtoToUser(UserDto userDto) {

        //собираем нашего юзера из dto
        User user = User.builder()
                .id(userDto.getId())
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .email(userDto.getEmail())
                .role(userDto.getRole())
                .address(userDto.getAddress())
                .phone(userDto.getPhone())
                .enable(userDto.getEnable())
                .bucket(userDto.getBucket())
                .build();
        return user;
    }


    @Override
    @Transactional
    public User registration(UserDto userDto) {
        //password encryption
        String password = passwordEncoder.encode(userDto.getPassword());
        userDto.setPassword(password);

        User user = new User();
        //disable new user before activation
        user.setEnable(false);

        mapUserDtoToUser(userDto, user);
        Optional<User> saved = Optional.of(save(user));

        saved.ifPresent(u -> {
            try {
                String token = UUID.randomUUID().toString();//random token
                verificationTokenService.save(saved.get(), token);

                //send verification email
                emailService.sendHtmlMail(u);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return saved.get();
    }

    @Override
    public UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .address(user.getAddress())
                .role(user.getRole())
                .phone(user.getPhone())
                .bucket(user.getBucket())
                .role(user.getRole())
                .build();
    }

    @Override
    @Transactional
    public Boolean deleteProductFromBucketById(Long productId, Long userId) {
        List<Product> productList = userRepository.findById(userId).get().getBucket().getProductList();//достаем лист продуктов у юзера

        Boolean isRemove = false;
        for (Product p : productList) {
            if (p.getId() == productId) {
                productList.remove(p);
                isRemove = true;
                break;
            }
        }
        User user = userRepository.findById(userId).get();//находим юзера
        user.getBucket().setProductList(productList);//сеттим список продуктов с удаленным продуктом
        Product product = productRepository.findById(productId).get();
        product.setAmount(product.getAmount() + 1L);
        productRepository.save(product);
        userRepository.save(user);//сохраняем

        return isRemove;
    }

    @Override
    @Transactional
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId).get();
        userRepository.delete(user);
    }

    @Override
    public void mapUserDtoToUser(UserDto userDto, User user) {
        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setPhone(userDto.getPhone());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());
        user.setAddress(userDto.getAddress());
        user.setEmail(userDto.getEmail());

    }


    public Boolean validationUserDto(UserDto userDto) {

        if (validationName(userDto.getUsername()) &&
                validationEmail(userDto.getEmail()) &&
        validationPassword(userDto.getPassword())&&
                validationPhone(userDto.getPhone())) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean validationPhone(String phone) {
        String checkPhone = "[\\d+]{7,15}";
        if (phone.matches(checkPhone)) {
            return true;
        } else {
            return false;
        }
    }


    public Boolean validationName(String name) {

        if (name.length() >= 5) {
            boolean checkUpperCase = false;
            boolean checkDigit = false;
            for (int i = 0; i < name.length(); i++) {
                if (name.charAt(i) >= 65 && name.charAt(i) <= 90) {
                    checkUpperCase = true;
                }
                if (name.charAt(i) >= 48 && name.charAt(i) <= 57) {
                    checkDigit = true;
                }
            }
            if (checkUpperCase && checkDigit) {
                return true;
            }
        }
        return false;
    }


    public Boolean validationEmail(String email) {
        String checkEmail = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w{2,8})+$";
        if (email.matches(checkEmail)) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean validationPassword(String password) {
        String pass = password;

        if (pass.length()>=4){
            return true;
        } else {
            throw new RuntimeException("The password cannot be less than four characters.Enter the password  correctly!");
        }
    }


    /**
     * находим юзера в bd
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findFirstByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with name:" + username);
        }

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(user.getRole().name()));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                roles
        );

    }
}