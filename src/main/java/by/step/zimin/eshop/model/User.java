package by.step.zimin.eshop.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "user_name")
    private String username;
    private String password;
    private String address;
    private String phone;
    private String email;
    private Boolean enable;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(cascade = CascadeType.REMOVE)
    private Bucket bucket;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Wallet wallet;

    public  Boolean isEnabled(){
        if (this.enable){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(address, user.address) && Objects.equals(phone, user.phone) && Objects.equals(email, user.email) && Objects.equals(enable, user.enable) && role == user.role && Objects.equals(bucket, user.bucket) && Objects.equals(wallet, user.wallet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, address, phone, email, enable, role, bucket, wallet);
    }
}




/**
 * Overview
 * The @Builder annotation produces complex builder APIs for your classes.
 *
 * @Builder lets you automatically produce the code required to have your class be instantiable with code such as:
 * <p>
 * Person.builder()
 * .name("Adam Savage")
 * .city("San Francisco")
 * .job("Mythbusters")
 * .job("Unchained Reaction")
 * .build();
 * @Builder can be placed on a class, or on a constructor, or on a method.
 * While the "on a class" and "on a constructor" mode are the most common use-case,
 * @Builder is most easily explained with the "method" use-case.
 */