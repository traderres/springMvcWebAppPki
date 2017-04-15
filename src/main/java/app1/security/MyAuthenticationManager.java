package app1.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * Created by adam on 1/6/2016.
 */
@Component
public class MyAuthenticationManager implements AuthenticationManager
{

    public Authentication authenticate(Authentication aAuth) throws AuthenticationException
    {
        // We are really authenticating in the MyUserDetailService -- so do nothing here
        return aAuth;
    }
}
   