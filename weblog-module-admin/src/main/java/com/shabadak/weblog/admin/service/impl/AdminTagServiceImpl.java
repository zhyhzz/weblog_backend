package com.shabadak.weblog.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shabadak.weblog.admin.model.vo.tag.FindTagPageListReqVO;
import com.shabadak.weblog.admin.model.vo.tag.FindTagPageListRspVO;
import com.shabadak.weblog.admin.service.AdminTagService;
import com.shabadak.weblog.common.domain.dos.ArticleTagRelDO;
import com.shabadak.weblog.common.domain.dos.CategoryDO;
import com.shabadak.weblog.common.domain.dos.TagDO;
import com.shabadak.weblog.common.domain.mapper.ArticleTagRelMapper;
import com.shabadak.weblog.common.domain.mapper.TagMapper;
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

/**
 * @Title: AdminTagServiceImpl
 * @Author shabadak
 * @Package com.shabadak.weblog.admin.service.impl
 * @Date 2024/8/14 9:32
 * @description
 */
@Service
public class AdminTagServiceImpl implements AdminTagService {

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private ArticleTagRelMapper articleTagRelMapper;
    @Override
    public PageResponse getTagList(FindTagPageListReqVO findTagPageListReqVO) {
        Long current = findTagPageListReqVO.getCurrent();
        Long size = findTagPageListReqVO.getSize();

        Page<TagDO> page = new Page<>(current, size);

        String name = findTagPageListReqVO.getName();
        LocalDate startDate = findTagPageListReqVO.getStartDate();
        LocalDate endDate = findTagPageListReqVO.getEndDate();

        LambdaQueryWrapper<TagDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(name), TagDO::getName, name.trim())
                .ge(Objects.nonNull(startDate), TagDO::getCreateTime, startDate)
                .le(Objects.nonNull(endDate), TagDO::getCreateTime, endDate)
                .orderByDesc(TagDO::getCreateTime);

        Page<TagDO> pages = tagMapper.selectPage(page, wrapper);
        List<TagDO> tagDOList = pages.getRecords();

        List<FindTagPageListRspVO> vos = new ArrayList<>();
        if(!CollectionUtils.isEmpty(tagDOList)) {
            vos= tagDOList.stream().map(TagDO->FindTagPageListRspVO.builder()
                    .id(TagDO.getId())
                    .name(TagDO.getName())
                    .createTime(TagDO.getCreateTime()).build()).collect(Collectors.toList());
        }

        return PageResponse.success(pages, vos);
    }

    @Override
    public Response addTag(String name) {
        TagDO tagDO = tagMapper.selectByName(name);
        if (Objects.nonNull(tagDO)) {
            throw new BizException(ResponseCodeEnum.TAG_NAME_IS_EXISTED);
        }
        TagDO tag = TagDO.builder().name(name.trim()).build();
        // 执行 insert
        tagMapper.insert(tag);
        return Response.success();

    }

    @Override
    public Response getTagByName(String name) {
        LambdaQueryWrapper<TagDO> wrapper = new LambdaQueryWrapper();
        wrapper.like(StringUtils.isNotBlank(name), TagDO::getName, name.trim());
        List<TagDO> tagDO = tagMapper.selectList(wrapper);
        return Response.success(tagDO);
    }

    @Override
    public Response findTagSelectList() {
        // 查询所有标签, Wrappers.emptyWrapper() 表示查询条件为空
        List<TagDO> tagDOS = tagMapper.selectList(Wrappers.emptyWrapper());

        // DO 转 VO
        List<SelectRspVO> vos = null;
        if (!CollectionUtils.isEmpty(tagDOS)) {
            vos = tagDOS.stream()
                    .map(tagDO -> SelectRspVO.builder()
                            .label(tagDO.getName())
                            .value(tagDO.getId())
                            .build())
                    .collect(Collectors.toList());
        }

        return Response.success(vos);
    }

    /**
     * 删除标签
     *
     * @param tagId
     * @return
     */
    @Override
    public Response deleteTag(Long tagId) {


        // 校验该标签下是否有关联的文章，若有，则不允许删除，提示用户需要先删除标签下的文章
        ArticleTagRelDO articleTagRelDO = articleTagRelMapper.selectOneByTagId(tagId);

        if (Objects.nonNull(articleTagRelDO)) {
//            log.warn("==> 此标签下包含文章，无法删除，tagId: {}", tagId);
            throw new BizException(ResponseCodeEnum.TAG_CAN_NOT_DELETE);
        }

        // 根据标签 ID 删除
        int count = tagMapper.deleteById(tagId);

        return count == 1 ? Response.success() : Response.fail(ResponseCodeEnum.TAG_NOT_EXISTED);
    }
}
