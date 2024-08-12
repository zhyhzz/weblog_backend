package com.shabadak.weblog.admin.service.impl;

import com.shabadak.weblog.admin.model.vo.user.FindUserInfoRspVO;
import com.shabadak.weblog.admin.model.vo.user.UpdateAdminUserPasswordReqVO;
import com.shabadak.weblog.admin.service.AdminUserService;
import com.shabadak.weblog.common.domain.mapper.UserMapper;
import com.shabadak.weblog.common.enums.ResponseCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.shabadak.weblog.common.utils.Response;
/**
 * @Title: AdminUserServiceImpl
 * @Author shabadak
 * @Package com.shabadak.weblog.admin.service.impl
 * @Date 2024/8/9 14:24
 * @description
 */
@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Response updatePassword(UpdateAdminUserPasswordReqVO updateAdminUserPasswordReqVO) {
        String username = updateAdminUserPasswordReqVO.getUsername();
        String password = updateAdminUserPasswordReqVO.getPassword();

        String encodePassword = passwordEncoder.encode(password);
        return  userMapper.updatePasswordByUsername(username, encodePassword) == 1 ? Response.success() : Response.fail(ResponseCodeEnum.USERNAME_NOT_FOUND);

    }

    /**
     * 获取当前登录用户信息
     * @return
     */
    @Override
    public Response findUserInfo() {
        // 获取存储在 ThreadLocal 中的用户信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 拿到用户名
        String username = authentication.getName();

        return Response.success(FindUserInfoRspVO.builder().username(username).build());
    }

}
