package com.arextest.agent.test.service;

import com.arextest.agent.test.entity.Role;
import com.arextest.agent.test.entity.User;
import com.arextest.agent.test.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * @author daixq
 * @date 2023/01/12
 */
@Service
@Slf4j
public class UserService implements UserDetailsService {
    @Autowired
    UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        String prefix = "[loadUserByUsername], user: " + username;
        log.info(prefix);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        // check if user exist
        User userInfo = null;
        try {
            userInfo = userMapper.selectOne(wrapper);
        } catch (Exception ex) {
            log.error(prefix, ex);
        }
        if (userInfo == null){
            throw new UsernameNotFoundException(prefix + " does not exist");
        }
        List<Role> roles = userMapper.findRoles(userInfo.getId());
        List<GrantedAuthority> authorities = new ArrayList<>();
        // set roles
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return new User(username,userInfo.getPassword(),authorities);
    }
}
