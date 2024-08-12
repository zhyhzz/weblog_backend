package com.shabadak.weblog.jwt.service;

import com.shabadak.weblog.common.domain.dos.UserDO;
import com.shabadak.weblog.common.domain.dos.UserRoleDO;
import com.shabadak.weblog.common.domain.mapper.UserMapper;
import com.shabadak.weblog.common.domain.mapper.UserRoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Title: UserDetailServiceImpl
 * @Author shabadak
 * @Package com.shabadak.weblog.jwt.service
 * @Date 2024/8/1 15:41
 * @description
 */
@Service
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 从数据库中查询
        UserDO userDO = userMapper.findByUsername(username);

        // 判断用户是否存在
        if (Objects.isNull(userDO)) {
            throw new UsernameNotFoundException("该用户不存在");
        }
        //用户角色
        List<UserRoleDO> rolesDos = userRoleMapper.selectByUsername(username);
        String[] roleArr = null;

        if(!CollectionUtils.isEmpty(rolesDos)) {
            List<String> roles = rolesDos.stream().map(p->p.getRole()).collect(Collectors.toList());
            roleArr = roles.toArray(new String[roles.size()]);
        }

        return User.withUsername(userDO.getUsername()).password(userDO.getPassword()).authorities(roleArr).build();

    }
}