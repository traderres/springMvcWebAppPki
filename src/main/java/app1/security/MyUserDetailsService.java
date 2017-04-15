
package app1.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import java.util.ArrayList;

/**
 * Created by adam on 1/6/2016.
 */
public class MyUserDetailsService implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken>
{

    public UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken aToken) throws UsernameNotFoundException
    {
        try
        {
            String sUserDN = aToken.getName();

            // A U T H E N T I C A T E     H E R E
            // -- Lookup the user in LDAP, a database, or make a web service call
            // -- Then get the results and put them into the UserInfo object
            // -- Finally, add the ROLE_USER granted authority
            //

            // Create a list of granted authorities
            ArrayList<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));

            // Create your UserInfo object
            UserInfo userInfo = new UserInfo(sUserDN, grantedAuthorities);
            return userInfo;
        }
        catch (Exception e)
        {
            throw new UsernameNotFoundException("Unable to authenticate this user", e);
        }
    }
}

  