package com.shabadak.weblog.common.domain.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.shabadak.weblog.common.domain.dos.TagDO;

import java.util.List;

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


    /**
     * 根据标签 ID 批量查询
     * @param tagIds
     * @return
     */
    default List<TagDO> selectByIds(List<Long> tagIds) {
        return selectList(Wrappers.<TagDO>lambdaQuery()
                .in(TagDO::getId, tagIds));
    }
}
