package com.example.springsecurity.auth;

import com.example.springsecurity.entity.UserEntity;
import com.example.springsecurity.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * 시큐리티 설정에서 loginProcessingUrl("/login")호출이 되면,
 * spring은 IoC컨테이너에서 UserDetailsService타입의 Bean을 찾는다. 그렇게 되면
 * CustomUserDetailsService을 찾게 되고 비로소 loadUserByUsername()을 호출해서 사용자 정보와 권한을 로딩한다 <= 규칙
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 매개변수 username은 login요청 page의 input태그의 name과 같아야 매칭이 된다.

        UserEntity userEntity = userEntityRepository.findByUsername(username);

        if (ObjectUtils.isEmpty(userEntity)) return null;
        else return new CustomUserDetails(userEntity);
        // 리턴되는 객체는 Authentication로 감싸져서 Security Context에 담기게 된다.
    }
}
