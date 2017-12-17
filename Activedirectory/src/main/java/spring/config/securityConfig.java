package spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;

@Configuration
@EnableWebSecurity
@PropertySource("classpath:configuration.properties")
public class securityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private Environment env;

    public securityConfig(){

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
    protected void configure(AuthenticationManagerBuilder authManagerBuilder) throws Exception {

        authManagerBuilder.authenticationProvider(activeDirectoryLdapAuthenticationProvider());

    }

    /**
     * create bean for activeDirectory login
     * @return
     */
    @Bean
    public AuthenticationProvider activeDirectoryLdapAuthenticationProvider() {

        ActiveDirectoryLdapAuthenticationProvider provider = new ActiveDirectoryLdapAuthenticationProvider(env.getProperty("domain"), env.getProperty("url"));

        provider.setConvertSubErrorCodesToExceptions(true);
        provider.setUseAuthenticationRequestCredentials(true);

        return provider;
    }

}
