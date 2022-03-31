package heavynimbus.backend.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurity extends WebSecurityConfigurerAdapter {
  private final DataSource datasource;
  private final PasswordEncoder passwordEncoder;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.jdbcAuthentication()
        .dataSource(datasource)
        .passwordEncoder(passwordEncoder)
        .usersByUsernameQuery("""
                SELECT username, password, enabled
                FROM account
                WHERE username = ?
                """)
            .authoritiesByUsernameQuery("""
                SELECT ? as username, 'USER' as authority
                """);
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/login", "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html", "/health")
        .permitAll()
        .anyRequest()
            .hasAuthority("USER")
        .and()
        .formLogin()
        .disable()
        .csrf()
        .disable()
        .httpBasic()
        .disable()
        .logout()
        .disable();
  }
}
