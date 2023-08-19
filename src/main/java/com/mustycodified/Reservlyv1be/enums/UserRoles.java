package com.mustycodified.Reservlyv1be.enums;

import com.google.common.collect.Sets;

import java.util.Set;

import static com.mustycodified.Reservlyv1be.enums.Authorities.*;

public enum UserRoles {
    ADMIN (Sets.newHashSet(USER_READ, USER_EDIT, USER_DELETE)),
    GUEST (Sets.newHashSet(USER_READ, USER_EDIT));
    private final Set<Authorities> authorities;

    UserRoles(Set<Authorities> authorities) {
        this.authorities = authorities;
    }
    public Set<Authorities> getAuthorities(){
        return authorities;
    }
}
