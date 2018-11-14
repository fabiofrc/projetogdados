package com.gdados.projeto.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    @Override
    public AppUserDetailsService userDetailsService() {
        return new AppUserDetailsService();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(getPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        JsfLoginUrlAuthenticationEntryPoint jsfLoginEntry = new JsfLoginUrlAuthenticationEntryPoint();
        jsfLoginEntry.setLoginFormUrl("/Login.xhtml");
        jsfLoginEntry.setRedirectStrategy(new JsfRedirectStrategy());

        JsfAccessDeniedHandler jsfDeniedEntry = new JsfAccessDeniedHandler();
        jsfDeniedEntry.setLoginPath("/AcessoNegado.xhtml");
        jsfDeniedEntry.setContextRelative(true);

        http.userDetailsService(userDetailsService())
                .csrf().disable()
                .headers().frameOptions().sameOrigin()
                .and()
                .authorizeRequests()
                .antMatchers("/Home.xhtml", "/teste.xhtml", "/paginas/plb/**", "/Erro.xhtml", "/javax.faces.resources/**").permitAll()
                .antMatchers("/paginas/adm/**").hasRole("ADMINISTRADOR")
                .antMatchers("/paginas/pf/**", "/paginas/pf/participante/cadastro_perfil").hasRole("PARTICIPANTE")
                .antMatchers("/Login.xhtml").anonymous()
                .and()
                .formLogin()
                .loginPage("/Login.xhtml")
                .failureUrl("/Login.xhtml?invalid=true").and().rememberMe()
                .and()
                .logout()
                .logoutSuccessUrl("/Login.xhtml")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .deleteCookies("JSESSIONID")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/AcessoNegado.xhtml")
                .authenticationEntryPoint(jsfLoginEntry)
                .accessDeniedHandler(jsfDeniedEntry);

    }

}
