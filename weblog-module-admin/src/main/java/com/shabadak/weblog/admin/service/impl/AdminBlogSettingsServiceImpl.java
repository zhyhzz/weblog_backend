package com.shabadak.weblog.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shabadak.weblog.admin.convert.BlogSettingsConvert;
import com.shabadak.weblog.admin.model.vo.blob.FindBlogSettingsRspVO;
import com.shabadak.weblog.admin.model.vo.blob.UpdateBlogSettingsReqVO;
import com.shabadak.weblog.admin.service.AdminBlogSettingsService;
import com.shabadak.weblog.common.domain.dos.BlogSettingsDO;
import com.shabadak.weblog.common.domain.mapper.BlogSettingsMapper;
import com.shabadak.weblog.common.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Title: AdminBlogSettingsServiceImpl
 * @Author shabadak
 * @Package com.shabadak.weblog.admin.service.impl
 * @Date 2024/8/15 9:21
 * @description
 */
@Service
public class AdminBlogSettingsServiceImpl extends ServiceImpl<BlogSettingsMapper, BlogSettingsDO> implements AdminBlogSettingsService {

    @Autowired
    private BlogSettingsMapper blogSettingsMapper;

    @Override
    public Response updateBlogSettings(UpdateBlogSettingsReqVO updateBlogSettingsReqVO) {
        // VO 转 DO
        BlogSettingsDO blogSettingsDO = BlogSettingsConvert.INSTANCE.convertVO2DO(updateBlogSettingsReqVO);
        blogSettingsDO.setId(1L);
        saveOrUpdate(blogSettingsDO);
        return Response.success();
    }


    /**
     * 获取博客设置详情
     *
     * @return
     */
    @Override
    public Response findDetail() {
        // 查询 ID 为 1 的记录
        BlogSettingsDO blogSettingsDO = blogSettingsMapper.selectById(1L);

        // DO 转 VO
        FindBlogSettingsRspVO vo = BlogSettingsConvert.INSTANCE.convertDO2VO(blogSettingsDO);

        return Response.success(vo);
    }
}
