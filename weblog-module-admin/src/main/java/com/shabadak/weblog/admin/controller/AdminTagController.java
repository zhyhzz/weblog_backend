package com.shabadak.weblog.admin.controller;

import com.shabadak.weblog.admin.model.vo.tag.FindTagPageListReqVO;
import com.shabadak.weblog.admin.service.AdminTagService;
import com.shabadak.weblog.common.utils.PageResponse;
import com.shabadak.weblog.common.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Title: AdminTagController
 * @Author shabadak
 * @Package com.shabadak.weblog.admin.controller
 * @Date 2024/8/14 9:02
 * @description
 */
@RequestMapping("/admin")
@RestController
public class AdminTagController {

    @Autowired
    private AdminTagService adminTagService;

    @PostMapping("/tag/list")
    public PageResponse getTagList(@RequestBody @Validated FindTagPageListReqVO findTagPageListReqVO) {
        return adminTagService.getTagList(findTagPageListReqVO);
    }

    @PostMapping("/tag/add")
    public Response addTag(@RequestParam("name") String name) {
        return adminTagService.addTag(name);
    }
}
