package top.zhacker.blog.web.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import top.zhacker.blog.web.model.User;
import top.zhacker.blog.web.service.UserServiceClient;

import java.util.ArrayList;
import java.util.List;

/**
 * DATE: 17/1/6 下午5:36 <br>
 * MAIL: hechengopen@gmail.com <br>
 * AUTHOR: zhacker
 *
 * SpringSecurity要求的获取用户信息的组件
 *
 */
@Slf4j
public class BlogUserDetailsService implements UserDetailsService {

    @Autowired
    private UserServiceClient userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("user login, username={}", username);
        User resp =  userService.findUserByName(username);
        List<GrantedAuthority> gas = new ArrayList<>();
        gas.add(new SimpleGrantedAuthority("ROLE_USER"));

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                username, resp.getPassword(), true, true, true, true, gas);
        return userDetails;
    }
}
