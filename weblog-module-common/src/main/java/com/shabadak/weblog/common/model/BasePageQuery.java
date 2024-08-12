package com.shabadak.weblog.common.model;

import lombok.Data;

/**
 * @Title: BasePageQuery
 * @Author shabadak
 * @Package com.shabadak.weblog.common.model
 * @Date 2024/8/12 14:24
 * @description
 */
@Data
public class BasePageQuery {
    /**
     * 当前页码, 默认第一页
     */
    private Long current = 1L;
    /**
     * 每页展示的数据数量，默认每页展示 10 条数据
     */
    private Long size = 10L;
}
