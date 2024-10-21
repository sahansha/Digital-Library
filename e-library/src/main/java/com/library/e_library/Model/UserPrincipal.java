package com.library.e_library.Model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Component
public class UserPrincipal implements UserDetails {
    private final Member member;

    public UserPrincipal(Member member) {
        this.member = member;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(this.member.getRole().toString()));
    }

    @Override
    public String getUsername() {
        return this.member.getUsername();
    }

    @Override
    public String getPassword() {
        return this.member.getPassword();
    }
}
