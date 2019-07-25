package pl.jbaranska.alpha.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private PasswordEncoder passwordEncoder;
    private DataSource dataSource;

    public SecurityConfig(PasswordEncoder passwordEncoder, DataSource dataSource) {
        this.passwordEncoder = passwordEncoder;
        this.dataSource = dataSource;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/","/**","/admin/**","/orders/**").hasRole("ADMIN")
                .antMatchers("/","/order/*","/orders/*").hasRole("USER")
                .antMatchers("/", "/**").permitAll()
                .and()
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .loginProcessingUrl("/loginBySpring")
                .defaultSuccessUrl("/")
                .failureUrl("/login?status=error")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login");
    }


    @Override
    protected void configure(AuthenticationManagerBuilder authentication) throws Exception {
        authentication
                .inMemoryAuthentication()
                .withUser("admin@com.pl")
                .password(passwordEncoder.encode("admin"))
                .authorities("ROLE_ADMIN");


        authentication
                .jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("" +
                        "select EMAIL, PASSWORD, 1 " +
                        "FROM APP_USERS " +
                        "WHERE EMAIL=?")
                .authoritiesByUsernameQuery("" +
                        "SELECT U.EMAIL, R.ROLE " +
                        "FROM APP_USERS U " +
                        "LEFT JOIN USERS_ROLES UR ON U.ID = UR.ID_USER " +
                        "LEFT JOIN ROLES R ON UR.ID_ROLE = R.ID " +
                        "WHERE EMAIL = ?")
                .passwordEncoder(passwordEncoder);


    }
}