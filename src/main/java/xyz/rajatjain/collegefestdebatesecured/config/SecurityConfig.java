package xyz.rajatjain.collegefestdebatesecured.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import xyz.rajatjain.collegefestdebatesecured.service.CollegeFestUserDetailsService;

/**
 * @author rajatjain on - 27-03-2022
 * @project CollegeFestDebateSecured
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private CollegeFestUserDetailsService userDetailsService;

    @Autowired
    public void setUserDetailsService(CollegeFestUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userDetailsService);
        return authenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/student/save", "/student/showFormForAddUpdate").hasAnyAuthority("USER", "ADMIN")
                .antMatchers("/student/delete").hasAnyAuthority("ADMIN")
                .anyRequest().authenticated().and()
                .formLogin().loginProcessingUrl("/login").successForwardUrl("/student/list").permitAll()
                .and()
                .logout().logoutSuccessUrl("/login").permitAll()
                .and().csrf().disable();
    }
}
