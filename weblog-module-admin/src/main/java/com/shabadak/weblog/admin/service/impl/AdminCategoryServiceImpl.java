package com.shabadak.weblog.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shabadak.weblog.admin.model.vo.category.AddCategoryReqVO;
import com.shabadak.weblog.admin.model.vo.category.FindCategoryPageListReqVO;
import com.shabadak.weblog.admin.model.vo.category.FindCategoryPageListRspVO;
import com.shabadak.weblog.admin.service.AdminCategoryService;
import com.shabadak.weblog.common.domain.dos.CategoryDO;
import com.shabadak.weblog.common.domain.mapper.CategoryMapper;
import com.shabadak.weblog.common.enums.ResponseCodeEnum;
import com.shabadak.weblog.common.exception.BizException;
import com.shabadak.weblog.common.model.vo.SelectRspVO;
import com.shabadak.weblog.common.utils.PageResponse;
import com.shabadak.weblog.common.utils.Response;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

/**
 * @Title: AdminCategoryServiceImpl
 * @Author shabadak
 * @Package com.shabadak.weblog.admin.service.impl
 * @Date 2024/8/12 11:05
 * @description
 */
@Service
public class AdminCategoryServiceImpl implements AdminCategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 添加分类
     *
     * @param addCategoryReqVO
     * @return
     */
    @Override
    public Response addCategory(AddCategoryReqVO addCategoryReqVO) {
        String categoryName = addCategoryReqVO.getName();
        CategoryDO categoryDO = categoryMapper.selectByName(categoryName);
        if (Objects.nonNull(categoryDO)) {
            throw new BizException(ResponseCodeEnum.CATEGORY_NAME_IS_EXISTED);
        }
            CategoryDO insertCategoryDO = CategoryDO.builder().name(addCategoryReqVO.getName().trim()).build();

        // 执行 insert
        categoryMapper.insert(insertCategoryDO);

        return Response.success();
    }

    @Override
    public PageResponse getCategoryList(FindCategoryPageListReqVO findCategoryPageListReqVO) {

        Long current = findCategoryPageListReqVO.getCurrent();
        Long size = findCategoryPageListReqVO.getSize();

        Page<CategoryDO> page = new Page<>(current, size);

        LambdaQueryWrapper<CategoryDO> wrapper = new LambdaQueryWrapper<>();

        String name = findCategoryPageListReqVO.getName();
        LocalDate startDate = findCategoryPageListReqVO.getStartDate();
        LocalDate endDate = findCategoryPageListReqVO.getEndDate();

        wrapper.like(StringUtils.isNotBlank(name), CategoryDO::getName, name.trim())
                .ge(Objects.nonNull(startDate), CategoryDO::getCreateTime, startDate)
                .le(Objects.nonNull(endDate), CategoryDO::getCreateTime, endDate)
                .orderByDesc(CategoryDO::getCreateTime);

        Page<CategoryDO> pages = categoryMapper.selectPage(page, wrapper);
        List<CategoryDO> categoryDOS = pages.getRecords();

        List<FindCategoryPageListRspVO> vos = null;
        if(!CollectionUtils.isEmpty(categoryDOS)) {
            vos = categoryDOS.stream()
                    .map(categoryDO-> FindCategoryPageListRspVO.builder()
                            .id(categoryDO.getId())
                            .name(categoryDO.getName())
                            .createTime(categoryDO.getCreateTime())
                            .build()).collect(Collectors.toList());
        }
        return PageResponse.success(pages, vos);
    }

    @Override
    public Response deleteCategory(Long id) {

        CategoryDO categoryDO = categoryMapper.selectById(id);
        if(null != categoryDO) {
            categoryMapper.deleteById(id);
        }
        return Response.success();
    }

    @Override
    public Response findCategorySelectList() {

        List<CategoryDO> categoryDOS = categoryMapper.selectList(new QueryWrapper<>());
        List<SelectRspVO> selectRspVOS = new ArrayList<>();
        if(!CollectionUtils.isEmpty(categoryDOS)) {
            selectRspVOS = categoryDOS.stream().map(categoryDO -> SelectRspVO.builder()
                    .label(categoryDO.getName())
                    .value(categoryDO.getId())
                    .build()
            ).collect(Collectors.toList());
        }

        return Response.success(selectRspVOS);
    }
}
