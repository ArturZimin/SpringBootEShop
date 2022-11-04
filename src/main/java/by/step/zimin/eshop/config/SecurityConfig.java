package by.step.zimin.eshop.config;

import by.step.zimin.eshop.model.Role;
import by.step.zimin.eshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import javax.persistence.Basic;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig  {

    private UserService userService;



    @Autowired
    public  void setUserService(UserService userService){

        this.userService=userService;
    }



    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception{

        //with help lambda
        return http.csrf(csrf->csrf.disable())
                .authorizeRequests(auth->{
                    try {
                        auth.antMatchers("/products/add/form").hasAnyAuthority(Role.MANAGER.name(),Role.ADMIN.name());//showAllUsers can only manager and ADMIN
                        auth.antMatchers("/users/new","/users/get/all").hasAuthority(Role.ADMIN.name());//and admin
                        auth.anyRequest().permitAll();//разрешить все
                        auth.and()
                                .formLogin()
                                .loginPage("/login") //если не зарегистрирован переходит на стр атентификации
                                .failureUrl("/error")//если произошли какие-либо файлы выводим страничку
                                .loginProcessingUrl("/auth")//идентификация пользователя
                                .permitAll()//разрешить все
                                .and()
                                .  logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))//разлогирование
                                .logoutSuccessUrl("/").deleteCookies("JSESSIONID")
                                .invalidateHttpSession(true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                })
                .httpBasic(Customizer.withDefaults())
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


//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(authenticationProvider());
//    }


//    @Basic
//    private AuthenticationProvider authenticationProvider(){
//        DaoAuthenticationProvider auth=new DaoAuthenticationProvider();
//        auth.setUserDetailsService(userService);
//        auth.setPasswordEncoder(passwordEncoder());
//        return  auth;
//    }





//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                  .antMatchers("/users/new ").hasAuthority(Role.ADMIN.name())//могут создавать только с ролью админ
//                  .anyRequest().permitAll()
//                .and()
//                  .formLogin()
//                  .loginPage("/login")
//                  .loginProcessingUrl("/auth")
//                  .permitAll()
//                .and()
//                .  logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                  .logoutSuccessUrl("/").deleteCookies("JSESSIONID")
//                  .invalidateHttpSession(true)
//                .and()
//                  .csrf().disable();
//
//
//    }





}
