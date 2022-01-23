package sia.sushicloud.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import sia.sushicloud.service.UserRepositoryUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserRepositoryUserDetailsService();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new StandardPasswordEncoder("53cr3t");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService())
                .passwordEncoder(encoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/design", "/orders").hasRole("USER")
                .antMatchers("/", "/**").permitAll()
                .and()
                .formLogin().loginPage("/login")
                .defaultSuccessUrl("/design", true)
                .and()
                .logout()
                .logoutSuccessUrl("/");
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }
}
