package app1.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Created by adam on 1/6/2016.
 */
public class UserInfo implements UserDetails, Serializable
{
    private String username;
    private List<GrantedAuthority> grantedAuthorities;


    public UserInfo(String aUsername, List<GrantedAuthority> aGrantedAuthorities)
    {
        this.username = aUsername;
        this.grantedAuthorities = aGrantedAuthorities;
    }


    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.grantedAuthorities;
    }

    public String getPassword() {
        return null;
    }

    public String getUsername() {
        return this.username;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }

}
   