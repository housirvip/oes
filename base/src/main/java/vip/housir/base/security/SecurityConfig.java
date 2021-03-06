package vip.housir.base.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import vip.housir.base.constant.ErrorMessage;
import vip.housir.base.utils.JwtUtils;

import javax.servlet.http.HttpServletResponse;

/**
 * @author housirvip
 */
@Slf4j
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expire}")
    private Long expire;

    @Value("${jwt.delay}")
    private Long delay;

    @Bean
    public JwtUtils jwtUtils() {

        return new JwtUtils(secret, expire, delay);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().exceptionHandling()
                .authenticationEntryPoint((httpServletRequest, httpServletResponse, e) ->
                        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, ErrorMessage.UNAUTHORIZED))
                .and().authorizeRequests()
                .antMatchers("/actuator/**", "/auth/**", "/noauth/**", "/druid/**").permitAll()
                .antMatchers("/**").authenticated()
//                .antMatchers("/admin/**").hasAnyRole("ADMIN", "ROOT")
                .and().addFilter(new JwtAuthFilter(authenticationManager(), jwtUtils()));
    }
}
