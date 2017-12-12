package spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.encoding.LdapShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@PropertySource("classpath:configuration.properties")
public class securityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private Environment env;

    public securityConfig() {

        super();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .anyRequest().fullyAuthenticated()
                .and()
                .formLogin()
                .successForwardUrl("/hello");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth
                .ldapAuthentication()
                .userDnPatterns(env.getProperty("userDnPatterns")) //"uid={0},ou=people"
                .groupSearchBase(env.getProperty("groupSearchBase")) //"ou=groups"
                .contextSource()
                .url(env.getProperty("url")) //ldap://localhost:8389/dc=springframework,dc=org
                .and()
                .passwordCompare()
                .passwordEncoder(new LdapShaPasswordEncoder())
                .passwordAttribute(env.getProperty("passwordAttribute")); //userPassword
    }

}
