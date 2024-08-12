package com.shabadak.weblog.common.domain.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shabadak.weblog.common.domain.dos.UserRoleDO;

import java.util.List;

import static com.baomidou.mybatisplus.extension.toolkit.SimpleQuery.selectList;

/**
 * @Title: UserRoleMapper
 * @Author shabadak
 * @Package com.shabadak.weblog.common.domain.mapper
 * @Date 2024/8/5 8:59
 * @description
 */
public interface UserRoleMapper extends BaseMapper<UserRoleDO> {
    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    default List<UserRoleDO> selectByUsername(String username) {
        LambdaQueryWrapper<UserRoleDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRoleDO::getUsername, username);

        return selectList(wrapper);
    }
}