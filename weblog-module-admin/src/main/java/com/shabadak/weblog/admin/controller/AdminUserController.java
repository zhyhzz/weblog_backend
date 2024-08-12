package com.shabadak.weblog.admin.controller;

import com.shabadak.weblog.admin.model.vo.user.UpdateAdminUserPasswordReqVO;
import com.shabadak.weblog.admin.service.AdminUserService;
import com.shabadak.weblog.common.aspect.ApiOperationLog;
import com.shabadak.weblog.common.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title: UserController
 * @Author shabadak
 * @Package com.shabadak.weblog.admin.controller
 * @Date 2024/8/9 14:48
 * @description
 */

@RestController
@RequestMapping("/admin")
@Api(tags = "Admin用户模块")
public class AdminUserController {

    @Autowired
    private AdminUserService adminUserService;

    @PostMapping("/password/update")
    @ApiOperation(value = "修改用户密码")
    @ApiOperationLog(description = "修改用户密码")
    public Response updatePassword(@RequestBody @Validated UpdateAdminUserPasswordReqVO updateAdminUserPasswordReqVO) {
        return adminUserService.updatePassword(updateAdminUserPasswordReqVO);
    }

    @PostMapping("/user/info")
    @ApiOperation(value = "获取用户信息")
    @ApiOperationLog(description = "获取用户信息")
    public Response findUserInfo() {
        return adminUserService.findUserInfo();
    }
}
