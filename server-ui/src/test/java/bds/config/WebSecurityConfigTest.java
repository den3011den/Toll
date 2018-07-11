package bds.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class WebSecurityConfigTest {

    @Test
    public void configure() throws Exception {
        WebSecurityConfig webSecurityConfig = mock(WebSecurityConfig.class);
        doNothing().when(webSecurityConfig).configure(isA(HttpSecurity.class));
        HttpSecurity http = null;
        webSecurityConfig.configure(http);
        verify(webSecurityConfig, times(1)).configure(http);
    }

    @Test
    public void configureGlobal() throws Exception {

        WebSecurityConfig webSecurityConfig = mock(WebSecurityConfig.class);
        doNothing().when(webSecurityConfig).configureGlobal(isA(AuthenticationManagerBuilder.class));
        AuthenticationManagerBuilder auth = null;
        webSecurityConfig.configureGlobal(auth);
        verify(webSecurityConfig, times(1)).configureGlobal(auth);

    }
}