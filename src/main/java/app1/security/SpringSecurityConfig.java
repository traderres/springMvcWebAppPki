package app1.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.authentication.preauth.x509.X509AuthenticationFilter;

/**
 * Created by adam on 1/6/2016.
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter
{
    private static final Logger logger = LoggerFactory.getLogger(SpringSecurityConfig.class);

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder aAuthMgrBuilder) throws Exception
    {
        logger.debug("configureGlobal() started");

        super.configure(aAuthMgrBuilder);

        // Tell Spring-Security to use our authentication provider bean
        // NOTE:  This authentication provider bean calls myUserDetailService
        aAuthMgrBuilder.authenticationProvider(preAuthProvider() );

        logger.debug("configureGlobal() finished");
    }


    @Override
    public void configure(HttpSecurity aHttp) throws Exception
    {
        logger.debug("configure() #1 started");

        // Sets the *ORDER* of what happens in security
        //  1) Setup the session
        //  2) Use the x509Filter to extract the principal
        //  3) Call the authenticationProvider --> which calls the myUserDetailsService
        //  4) Verify that certain pages have the required role
        aHttp.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .and()
                .addFilter(x509Filter())
                .authorizeRequests()
                .antMatchers("/**").access("hasRole('ROLE_USER')")
                .and()
                .requiresChannel().antMatchers("/**").requiresSecure()
                .and()
                .anonymous().disable();

        logger.debug("configure() #1 finished");
    }




    @Override
    public void configure(WebSecurity aWebSecurity) throws Exception
    {
        logger.debug("configure() #2 started");

        aWebSecurity.ignoring()
                .antMatchers("/resources/**");

        logger.debug("configure() #2 finished");
    }


    @Bean
    public MyAuthenticationManager myAuthenticationManager()
    {
        return new MyAuthenticationManager();
    }


    @Bean
    public SubjectX509PrincipalExtractor subjectX509PrincipalExtractor()
    {
        return new SubjectX509PrincipalExtractor();
    }

    @Bean
    public X509AuthenticationFilter x509Filter()
    {
        // Setup a filter that extracts the principal from the cert
        X509AuthenticationFilter x509Filter = new X509AuthenticationFilter();
        x509Filter.setContinueFilterChainOnUnsuccessfulAuthentication(false);
        x509Filter.setAuthenticationManager(myAuthenticationManager());
        x509Filter.setPrincipalExtractor(subjectX509PrincipalExtractor());

        return x509Filter;
    }

    @Bean
    public MyUserDetailsService myUserDetailsService()
    {
        return new MyUserDetailsService();
    }


    @Bean
    public PreAuthenticatedAuthenticationProvider preAuthProvider()
    {
        // Connect the myUserDetailsService to this filter
        PreAuthenticatedAuthenticationProvider preAuthProvider = new PreAuthenticatedAuthenticationProvider();
        preAuthProvider.setPreAuthenticatedUserDetailsService(myUserDetailsService());
        preAuthProvider.setThrowExceptionWhenTokenRejected(true);

        return preAuthProvider;
    }

}
        