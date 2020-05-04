package kg.onlinestore.unas.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("admin").password("{noop}admin123").roles("ADMIN")
//                .and()
//                .withUser("kulanbek").password("{noop}kulanbek123").roles("USER")
//                .and()
//                .withUser("aidin").password("{noop}aidin123").roles("USER");
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select login, password, is_active from users where login=?")
                .authoritiesByUsernameQuery("select u.login, ur.role_name from user_roles ur join users u on ur.user_id=u.id where u.login=? and u.is_active=true");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.httpBasic().and().authorizeRequests()
//                .antMatchers(HttpMethod.POST, "/items").hasRole("ADMIN")
//                .antMatchers(HttpMethod.POST,"/categories").hasRole("ADMIN")
//                .antMatchers(HttpMethod.POST, "/orders/add").hasAnyRole("ADMIN", "USER")
//                .antMatchers(HttpMethod.POST, "/wallets/add").hasAnyRole("ADMIN", "USER")

//                .antMatchers(HttpMethod.DELETE, "/categories/delete/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.DELETE, "/items/delete/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.DELETE, "/users/delete/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.DELETE, "/wallets/delete/**").hasAnyRole("ADMIN", "USER")
//                .antMatchers(HttpMethod.DELETE, "/orders/delete/**").hasAnyRole("ADMIN", "USER")
//
//                .antMatchers(HttpMethod.GET, "/items").hasAnyRole("ADMIN", "USER")
//                .antMatchers(HttpMethod.GET, "/categories").hasAnyRole("ADMIN", "USER")
//                .antMatchers(HttpMethod.GET, "/users").hasRole("ADMIN")
//                .antMatchers(HttpMethod.GET, "/orders").hasAnyRole("ADMIN", "USER")
//                .and().csrf().disable().headers().frameOptions().disable().and()
//                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
        http.httpBasic().and().authorizeRequests()
                .antMatchers(HttpMethod.POST, "/item").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/item/category/**").permitAll()
                .antMatchers(HttpMethod.POST, "/category").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/category").permitAll()
                .antMatchers("/wallet/**").hasAnyRole("ADMIN", "USER")
                .antMatchers("/cart-item/**").hasAnyRole("ADMIN", "USER")
                .antMatchers("/cart/**").hasAnyRole("ADMIN", "USER")
                .and().csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
