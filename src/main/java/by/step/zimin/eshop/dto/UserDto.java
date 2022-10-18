package by.step.zimin.eshop.dto;
import by.step.zimin.eshop.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private String username;
    private String password;
    private String email;
    private String address;
    private String phone;
    private Role role;

}

