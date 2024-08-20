package com.shabadak.weblog.admin.service;

import com.shabadak.weblog.admin.model.vo.blob.UpdateBlogSettingsReqVO;
import com.shabadak.weblog.common.utils.Response;

/**
 * @Title: AdminBlogSettingsService
 * @Author shabadak
 * @Package com.shabadak.weblog.admin.service
 * @Date 2024/8/15 9:20
 * @description
 */
public interface AdminBlogSettingsService {

    /**
     * 更新博客设置信息
     * @param updateBlogSettingsReqVO
     * @return
     */
    Response updateBlogSettings(UpdateBlogSettingsReqVO updateBlogSettingsReqVO);

    /**
     * 获取博客设置详情
     * @return
     */
    Response findDetail();
}
