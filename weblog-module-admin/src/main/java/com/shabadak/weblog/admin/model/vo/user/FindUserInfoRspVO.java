package com.shabadak.weblog.admin.model.vo.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Title: FindUserInfoRspVO
 * @Author shabadak
 * @Package com.shabadak.weblog.admin.model.vo
 * @Date 2024/8/9 16:44
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindUserInfoRspVO {
    /**
     * 用户名
     */
    private String username;

}