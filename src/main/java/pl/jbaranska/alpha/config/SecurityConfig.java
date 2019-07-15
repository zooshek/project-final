package pl.jbaranska.alpha.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private PasswordEncoder passwordEncoder;
    private DataSource dataSource;

    @Autowired
    public SecurityConfig(PasswordEncoder passwordEncoder, DataSource dataSource) {
        this.passwordEncoder = passwordEncoder;
        this.dataSource = dataSource;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authentication) throws Exception {
        authentication
                .inMemoryAuthentication()
                .withUser("test1")
                .password(passwordEncoder.encode("test1"))
                .authorities("ROLE_TESTER")
                .and()
                .withUser("test2")
                .password(passwordEncoder.encode("test2"))
                .authorities("ROLE_TESTER");

        authentication
                .jdbcAuthentication()
                .usersByUsernameQuery("" +
                        "select EMAIL, PASSWORD, 1 " +
                        "FROM USERS " +
                        "WHERE EMAIL=?")
                .authoritiesByUsernameQuery("" +
                        "SELECT U.EMAIL, R.ROLE " +
                        "FROM USERS U " +
                        "LEFT JOIN USERS_ROLES UR ON U.ID = UR.ID_USER " +
                        "LEFT JOIN ROLES R ON UR.ID_ROLE = R.ID " +
                        "WHERE EMAIL = ?")
                .passwordEncoder(passwordEncoder);

    }

}
