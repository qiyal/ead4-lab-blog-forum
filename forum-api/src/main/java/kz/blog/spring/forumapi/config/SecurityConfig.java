package kz.blog.spring.forumapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()

                // GET
                .antMatchers(
                        HttpMethod.GET,
                        "/forum/all",
                        "/forum",
                        "/forum/search"
                ).permitAll()

                // POST
                .antMatchers(
                        HttpMethod.POST,
                        "/forum/create"
                ).hasRole("PRIVATE_REST_CLIENT")

                // DELETE
                .antMatchers(
                        HttpMethod.DELETE,
                        "/forum/delete/{forumId}"
                ).hasRole("PRIVATE_REST_CLIENT")

                // PATCH
                .antMatchers(
                        HttpMethod.PATCH,
                        "/forum/change-owner",
                        "/forum/add-member",
                        "/forum/remove-member"
                ).hasRole("PRIVATE_REST_CLIENT")
                .anyRequest().permitAll()
                .and()
                .httpBasic();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
//                .withUser("public-rest-client")
//                .password("{noop}password")
//                .roles("PUBLIC_REST_CLIENT")
//                .and()
                .withUser("private-rest-client")
                .password("{noop}password")
                .roles("PRIVATE_REST_CLIENT");
    }
}
