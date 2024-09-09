package com.admin.security.services;

import com.admin.entities.Admin;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;
    
    private int adminId;
    private String username;
    
    @JsonIgnore
    private String password;
    private String email;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(int adminId, String username, String password, String email) {
        this.adminId = adminId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.authorities = new ArrayList<>();
    }

    public static UserDetailsImpl build(Admin admin) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        return new UserDetailsImpl(
            admin.getId(),
            admin.getUsername(),
            admin.getPassword(),
            admin.getEmail()
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public int getAdminId() {
        return adminId;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return adminId == user.adminId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(adminId);
    }
}
