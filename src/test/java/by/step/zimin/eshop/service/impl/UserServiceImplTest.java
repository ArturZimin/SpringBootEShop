package by.step.zimin.eshop.service.impl;

import by.step.zimin.eshop.dto.UserDto;
import by.step.zimin.eshop.model.Role;
import by.step.zimin.eshop.model.User;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceImplTest {


    @Autowired
    private UserServiceImpl userService;

    private User user;
    private User user1;
    private UserDto userDto;

    @BeforeEach
    public void initialize() {
        user = new User();
        user.setEnable(false);
        user.setUsername("Artur V");
        user.setPassword("4444");
        user.setEmail("hgghgh@jhj.com");
        user.setAddress("Minsk Angarskaya 12");
        user.setRole(Role.USER);

        user1 = new User();
        user1.setEnable(false);
        user1.setUsername("Olga V");
        user1.setPassword("1111");
        user1.setEmail("hgretyt@.com");

        userDto = new UserDto();
        userDto.setEnable(false);
        userDto.setUsername("Andrey V");
        userDto.setPassword("3333");
        userDto.setEmail("hgghgh@jhj.com");
        userDto.setAddress("g67676767676767joij");

    }


    @Test
    void userExistsByEmail() {
        userService.save(this.user);
        boolean isExist = userService.userExists(this.user.getEmail());
        Assert.assertEquals(true, isExist);
    }

    @Test
    void save() {
        User u = userService.save(user);
        Assert.assertNotNull(u);
        Assert.assertEquals(user, u);
    }


    @Test
    void getAllUser() {
        List<UserDto> first = userService.getAllUser();
        userService.save(user);
        userService.save(user1);
        List<UserDto> userDtoList = userService.getAllUser();
        Assert.assertEquals(first.size() + 2, userDtoList.size());
    }

    @Test
    void findByName() {
        userService.save(user);
        User u = userService.findByName(user.getUsername());
        Assert.assertNotNull(u);
        Assert.assertEquals(user.getUsername(), u.getUsername());
    }

    @Test
    void updateUser() {
        String phone = user.getPhone();
        userService.save(user);
        userService.updateUser(userService.toDto(user));
        User u1 = userService.findByName(user.getUsername());
        Assert.assertEquals(phone, u1.getPhone());
        user.setPhone("00000000");
        userService.updateUser(userService.toDto(user));
        Assert.assertNotEquals(phone, u1.getPhone());
    }


    @Test
    void registration() throws MessagingException {
//        PasswordEncoder usMock=Mockito.mock(PasswordEncoder.class);
//        EmailService emailServiceMock=Mockito.mock(EmailService.class);
        userService.registration(userDto);
        User fromDB = userService.findByName(userDto.getUsername());
        userDto.setEnable(null);
        userDto.setId(fromDB.getId());
        UserDto fromUserDto = userService.toDto(fromDB);
        Assert.assertEquals(userDto, fromUserDto);

    }

    @Test
    void toDto() {
        UserDto userDto2 = userService.toDto(user1);
        boolean isUser = user1.getClass() == User.class;
        boolean isUserDto = userDto2 instanceof UserDto;

        Assert.assertTrue(isUser);
        Assert.assertTrue(isUserDto);

    }


    @Test
    void deleteUser() {
        userService.save(user);
        User dbUser = userService.findByName(user.getUsername());
        userService.deleteUser(dbUser.getId());

        dbUser = userService.findByName(user.getUsername());

        assertNull(dbUser);
    }

    @Test
    void mapUserDtoToUser() {
        userService.mapUserDtoToUser(userDto, user);

        Assert.assertEquals(user.getUsername(), userDto.getUsername());
        Assert.assertEquals(user.getPassword(), userDto.getPassword());
        Assert.assertEquals(user.getEmail(), userDto.getEmail());

    }

    @Test
    void validationUserDto() {
        userDto.setUsername("Adfdf567");
        userDto.setPhone("+375299751269");
        boolean isValidUserDto = userService.validationUserDto(userDto);
        Assert.assertTrue(isValidUserDto);
    }

    @Test
    void validationPhone() {
        userDto.setPhone("+375299751269");
        boolean isValidPhone = userService.validationPhone(userDto.getPhone());
        Assertions.assertTrue(isValidPhone);
        userDto.setPhone("+37");
        boolean isNotValidPhone = userService.validationPhone(userDto.getPhone());
        Assertions.assertFalse(isNotValidPhone);
    }


    @Test
    void validationPassword() {
        boolean isValidPassword = userService.validationPassword(userDto.getPassword());
        Assertions.assertTrue(isValidPassword);
        userDto.setPassword("12");
        try {
            userService.validationPassword(userDto.getPassword());
        } catch (RuntimeException e) {
            String msg="The password cannot be less than four characters.Enter the password  correctly!";
            Assert.assertEquals(msg,e.getMessage());
        }
    }

    @Test
    void loadUserByUsername() {
        userService.save(user);
       UserDetails userDetails= userService.loadUserByUsername(user.getUsername());
       Assertions.assertNotNull(userDetails);
       Assertions.assertEquals(user.getUsername(),userDetails.getUsername());
       Assertions.assertEquals(user.getPassword(),userDetails.getPassword());
    }


}

