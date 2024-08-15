package com.shabadak.weblog.common.domain.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shabadak.weblog.common.domain.dos.TagDO;

/**
 * @Title: TagMapper
 * @Author shabadak
 * @Package com.shabadak.weblog.common.domain.mapper
 * @Date 2024/8/14 8:59
 * @description
 */
public interface TagMapper extends BaseMapper<TagDO> {
    default TagDO selectByName(String name) {
        return selectOne(new LambdaQueryWrapper<TagDO>().eq(TagDO::getName, name));
    }
}
