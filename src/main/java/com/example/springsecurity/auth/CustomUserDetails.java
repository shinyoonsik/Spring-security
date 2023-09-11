package com.example.springsecurity.auth;

import com.example.springsecurity.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * loginProcessingUrl("/login") // /login으로 접근하면 security가 낚아채서 login을 진행함
 * 로그인진행이 완료되면 시큐리티는 자신만의 session(SecurityContextHolder)에 user정보를 저장한다
 * SecurityContextHolder에 들어갈수 있는 객체의 타입은 정해져 있다. => Authentication객체
 * 그리고 Authentication객체안에 User정보를 저장한다.
 * 그런데, Authentication객체안에 저장할 수 있는 User타입도 UserDetails로 정해져 있다.
 * 그러므로 UserDetails를 implements한 것이다
 * ????? security session이 SecurityContext인건가????????
 */

public class CustomUserDetails implements UserDetails {

    private UserEntity user;

    public CustomUserDetails(UserEntity user) {
        this.user = user;
    }

    // 해당 user의 권한을 리턴하는 곳
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> userAuthorities = new ArrayList<>();
        userAuthorities.add((GrantedAuthority) () -> user.getRole());

        return userAuthorities;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUsername();
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
        // 예제: 유저 enable 검증로직
        // 1년동안 로그인하지 않은 유저라면 휴면계정으로 간주하고 false로 바꿔줌
        // 현재시간 - 가장 최근 로그인 시간 => 1년 초과하면 return false
        return true;
    }
}
