package com.shabadak.weblog.admin.service;

import com.shabadak.weblog.admin.model.vo.category.AddCategoryReqVO;
import com.shabadak.weblog.admin.model.vo.category.FindCategoryPageListReqVO;
import com.shabadak.weblog.common.utils.PageResponse;
import com.shabadak.weblog.common.utils.Response;

/**
 * @Title: AdminCategoryService
 * @Author shabadak
 * @Package com.shabadak.weblog.admin.service
 * @Date 2024/8/12 11:05
 * @description
 */
public interface AdminCategoryService {

    Response addCategory(AddCategoryReqVO addCategoryReqVO);

    PageResponse getCategoryList(FindCategoryPageListReqVO findCategoryPageListReqVO);

    Response deleteCategory(Long id);

    Response findCategorySelectList();
}
