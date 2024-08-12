package com.shabadak.weblog.admin.controller;

import com.shabadak.weblog.admin.model.vo.category.AddCategoryReqVO;
import com.shabadak.weblog.admin.model.vo.category.FindCategoryPageListReqVO;
import com.shabadak.weblog.admin.service.AdminCategoryService;
import com.shabadak.weblog.common.aspect.ApiOperationLog;
import com.shabadak.weblog.common.utils.PageResponse;
import com.shabadak.weblog.common.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Title: AdminCategoryController
 * @Author shabadak
 * @Package com.shabadak.weblog.admin.controller
 * @Date 2024/8/12 11:15
 * @description
 */
@RestController
@RequestMapping("/admin")
@Api(tags = "Admin 分类模块")
public class AdminCategoryController {

    @Autowired
    private AdminCategoryService categoryService;

    @PostMapping("/category/add")
    @ApiOperation(value = "添加分类")
    @ApiOperationLog(description = "添加分类")
    public Response addCategory(@RequestBody @Validated AddCategoryReqVO addCategoryReqVO) {
        return categoryService.addCategory(addCategoryReqVO);
    }

    @PostMapping("/category/list")
    @ApiOperation(value = "分类列表")
    @ApiOperationLog(description = "分类列表")
    public PageResponse getCategoryList(@RequestBody @Validated FindCategoryPageListReqVO findCategoryPageListReqVO) {

        return categoryService.getCategoryList(findCategoryPageListReqVO);
    }

    @PostMapping("/category/delete")
    @ApiOperation(value = "删除分类")
    @ApiOperationLog(description = "删除分类")
    public Response deleteCategory(@RequestParam("id") Long id) {

        return categoryService.deleteCategory(id);
    }

    @GetMapping("/category/select/list")
    @ApiOperation(value = "分类标签列表")
    @ApiOperationLog(description = "分类标签列表")
    public Response findCategorySelectList() {
        return  categoryService.findCategorySelectList();
    }

}