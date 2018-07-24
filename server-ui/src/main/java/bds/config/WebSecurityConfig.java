package bds.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/home", "/routes", "/payments", "/routes/**", "/payments/**","/askTrack", "/askTrackPage", "/showTrackPage").access("hasRole('CLIENT') or hasRole('MANAGER') or hasRole('ROOT')")
                .antMatchers("/registerClient", "/registerClient/**").access("hasRole('MANAGER') or hasRole('ROOT')")
                .antMatchers("/registerManager", "/registerManager/**").hasRole("ROOT")
                .antMatchers("/css", "/css/**","/img", "/img/**").permitAll()
                .antMatchers("/error").authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Autowired
    private DataSource dataSource;


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                .withUser("client").password("client").roles("CLIENT")
//                .and()
//                .withUser("manager").password("manager").roles("MANAGER")
//                .and()
//                .withUser("root").password("root").roles("ROOT");
//
//    }

        auth
                .jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery(
                        "select name as username, password, 'true' as enabled from usertable where not archive and name = ?")
                .authoritiesByUsernameQuery(
                        "select usertable.name as username, roletable.name as role from usertable, roletable where usertable.role=roletable.id and usertable.name = ?");

    }

}
