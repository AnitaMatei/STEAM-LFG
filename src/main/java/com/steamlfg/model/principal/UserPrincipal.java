package com.steamlfg.model.principal;

import com.steamlfg.model.dto.UserDTO;
import com.steamlfg.model.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Transient;
import java.util.Collection;

public class UserPrincipal implements UserDetails {
    private UserDTO user;

    public UserPrincipal(UserDTO user) {
        this.user = user;
    }

    @Transient
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList("ROLE_USER");
    }

    @Transient
    @Override
    public String getPassword() {
        return "none";
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Transient
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Transient
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Transient
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Transient
    @Override
    public boolean isEnabled() {
        return true;
    }
}
