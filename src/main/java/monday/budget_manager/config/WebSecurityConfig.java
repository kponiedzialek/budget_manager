package monday.budget_manager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        .authorizeHttpRequests(
            authorize ->
                authorize
                    .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**"))
                    .permitAll()
                    .requestMatchers("/register")
                    .permitAll()
                    .requestMatchers("/")
                    .permitAll()
                    .anyRequest()
                    .authenticated())
        .headers(AbstractHttpConfigurer::disable)
        .csrf(
            csrf ->
                csrf.ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")))
        .formLogin(login -> login.loginPage("/login").permitAll().defaultSuccessUrl("/", true))
        .logout(logout -> logout.permitAll().logoutSuccessUrl("/"));

    return httpSecurity.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
