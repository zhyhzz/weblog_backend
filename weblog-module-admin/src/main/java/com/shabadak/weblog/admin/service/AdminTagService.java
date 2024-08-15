package com.shabadak.weblog.admin.service;

import com.shabadak.weblog.admin.model.vo.tag.FindTagPageListReqVO;
import com.shabadak.weblog.common.utils.PageResponse;
import com.shabadak.weblog.common.utils.Response;

/**
 * @Title: AdminTagService
 * @Author shabadak
 * @Package com.shabadak.weblog.admin.service
 * @Date 2024/8/14 9:32
 * @description
 */
public interface AdminTagService {

    PageResponse getTagList(FindTagPageListReqVO findTagPageListReqVO);

    Response addTag(String name);
}
