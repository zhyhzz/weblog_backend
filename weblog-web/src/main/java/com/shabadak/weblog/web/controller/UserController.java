package com.shabadak.weblog.web.controller;

import com.shabadak.weblog.web.model.User;
import org.springframework.stereotype.Controller;

/**
 * @Title: UserController
 * @Author shabadak
 * @Package com.shabadak.weblog.web.controller
 * @Date 2024/8/8 16:21
 * @description
 */
@Controller
public class UserController {

    public User getUser() {
        return new User();
    }
}
