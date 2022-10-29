package stargftmilhas.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import stargftmilhas.service.AutenticacaoService;

@Configuration
@EnableWebSecurity
@ComponentScan
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AutenticacaoService autenticacaoService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/usuarios/**").hasRole("ADMIN")
                .antMatchers("/atividade/**").hasRole("ADMIN")
                .antMatchers("/participante/**").hasRole("ADMIN")
                .antMatchers("/grupo/**").hasRole("ADMIN")
                .antMatchers("/evento/**").hasRole("ADMIN")
                .antMatchers("/participacao/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin( form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/home", true)
                        .permitAll()                )
                .logout(logout -> logout.logoutUrl("/logout"));
    }
}
