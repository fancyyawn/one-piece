package top.zhacker.ms.auth.oauth2.jwt.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * DATE: 2017/5/4 <br>
 * MAIL: hechengopen@gmail.com <br>
 * AUTHOR: zhacker
 */
@Service
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {

    Map<String, User> userMap = new ConcurrentHashMap<>();
    {
        userMap.put("user", new User(
                "user", "password", true, true,true, true,
                Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))));
        userMap.put("reader", new User(
                "reader", "reader", true, true,true, true,
                Arrays.asList(new SimpleGrantedAuthority("FOO_READ"))));
        userMap.put("writer", new User(
                "writer", "writer", true, true,true, true,
                Arrays.asList(new SimpleGrantedAuthority("FOO_READ"), new SimpleGrantedAuthority("FOO_WRITE"))));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("user login, username={}", username);
        return userMap.get(username);
    }
}
