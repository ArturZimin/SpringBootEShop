package by.step.zimin.eshop.config;

import by.step.zimin.eshop.model.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig {


    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        //with help lambda
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeRequests(auth -> {
                    try {
                        auth.antMatchers("/products/add/**").hasAnyAuthority(Role.MANAGER.name(), Role.ADMIN.name())//showAllUsers can only manager and ADMIN
                                .antMatchers("/users/new", "/users/get/all","/headers/**").hasAuthority(Role.ADMIN.name())//and admin
                                .antMatchers("/email/activation").hasAnyAuthority(Role.USER.name(),Role.GUEST.name(),Role.ADMIN.name(),Role.MANAGER.name())
                                .anyRequest().permitAll()//разрешить все
                                .and()
                                .formLogin()
                                .loginPage("/login") //если не зарегистрирован переходит на стр атентификации
                                .failureUrl("/error")//если произошли какие-либо файлы выводим страничку
                                .loginProcessingUrl("/auth")//идентификация пользователя
                                .permitAll()//разрешить все
                                .and()
                                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))//разлогирование
                                .logoutSuccessUrl("/").deleteCookies("JSESSIONID")
                                .invalidateHttpSession(true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                })
                .httpBasic(Customizer.withDefaults())//Returns: the HttpSecurity for further customizations
                .build();

    }


    /*   /login GET - the login form
/login POST - process the credentials and if valid authenticate the user
/login?error GET - redirect here for failed authentication attempts
/login?logout GET - redirect here after successfully logging out*/

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }


}
