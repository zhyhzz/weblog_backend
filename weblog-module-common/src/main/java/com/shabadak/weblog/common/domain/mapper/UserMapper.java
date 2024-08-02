package com.shabadak.weblog.common.domain.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shabadak.weblog.common.domain.dos.UserDO;

/**
 * @Title: UserMapper
 * @Author shabadak
 * @Package com.shabadak.weblog.common.domain.mapper
 * @Date 2024/8/1 14:42
 * @description UserMapper
 */
public interface UserMapper extends BaseMapper<UserDO> {
    default UserDO findByUsername(String username) {
        LambdaQueryWrapper<UserDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserDO::getUsername, username);
        return selectOne(wrapper);
    }
}