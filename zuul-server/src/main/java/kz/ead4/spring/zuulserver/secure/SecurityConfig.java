package kz.ead4.spring.zuulserver.secure;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
//                .cors().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling().authenticationEntryPoint((req, resp, e) -> resp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .and()
                .authorizeRequests()

                // API AUTH
                .antMatchers(HttpMethod.POST, "/api/user-api/auth/**").permitAll()

                // API USER
                .antMatchers("/api/user-api/user/public/**").permitAll()
                .antMatchers("/api/user-api/user/private/**").hasAnyAuthority("ADMIN", "USER")

                // API FORUM API
                .antMatchers("/api/forum-api/forum/public/**").permitAll()
                .antMatchers("/api/forum-api/forum/private/**").hasAnyAuthority("ADMIN", "USER")

                .anyRequest().authenticated()
                .and()
                .addFilterAfter(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
