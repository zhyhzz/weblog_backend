package com.shabadak.weblog.common.domain.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shabadak.weblog.common.domain.dos.CategoryDO;

/**
 * @Title: CategoryMapper
 * @Author shabadak
 * @Package com.shabadak.weblog.common.domain.mapper
 * @Date 2024/8/12 11:00
 * @description
 */
public interface CategoryMapper extends BaseMapper<CategoryDO> {

    /**
     * 根据用户名查询
     * @param categoryName
     * @return
     */
    default CategoryDO selectByName(String categoryName) {
        LambdaQueryWrapper<CategoryDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CategoryDO::getName, categoryName);
        return selectOne(wrapper);
    }
}
