package com.shabadak.weblog.common.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Title: SelectRspVO
 * @Author shabadak
 * @Package com.shabadak.weblog.common.model.vo
 * @Date 2024/8/12 15:00
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SelectRspVO {
    /**
     * Select 下拉列表的展示文字
     */
    private String label;

    /**
     * Select 下拉列表的 value 值，如 ID 等
     */
    private Object value;
}