package com.steamlfg.service;

import com.steamlfg.model.dto.UserDTO;
import com.steamlfg.model.principal.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.openid.OpenIDAuthenticationToken;
import org.springframework.stereotype.Service;


@Service
public class CustomAuthenticationUserDetailsService implements AuthenticationUserDetailsService<OpenIDAuthenticationToken> {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserDetails(OpenIDAuthenticationToken openIDAuthenticationToken) throws UsernameNotFoundException {
        System.out.println("Loading details for user with steamid " + openIDAuthenticationToken.getName());
        UserDTO user = userService.findByOid(openIDAuthenticationToken.getName());

        if (user == null)
            return new UserPrincipal(userService.saveUser(openIDAuthenticationToken.getName()));
        else return new UserPrincipal(user);
    }
}
