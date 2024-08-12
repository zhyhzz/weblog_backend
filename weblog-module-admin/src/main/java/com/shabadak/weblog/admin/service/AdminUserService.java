package com.shabadak.weblog.admin.service;

import com.shabadak.weblog.admin.model.vo.user.UpdateAdminUserPasswordReqVO;
import com.shabadak.weblog.common.utils.Response;
/**
 * @Title: AdminUserService
 * @Author shabadak
 * @Package com.shabadak.weblog.admin.service
 * @Date 2024/8/9 14:24
 * @description
 */
public interface AdminUserService {

    /**
     * 修改密码
     * @param updateAdminUserPasswordReqVO
     * @return
     */
    Response updatePassword(UpdateAdminUserPasswordReqVO updateAdminUserPasswordReqVO);

    /**
     * 获取当前登录用户信息
     * @return
     */
    Response findUserInfo();

}
