package com.shabadak.weblog.admin.model.vo.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Title: FindCategoryPageListRspVO
 * @Author shabadak
 * @Package com.shabadak.weblog.admin.model.vo.category
 * @Date 2024/8/12 14:28
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindCategoryPageListRspVO {
    /**
     * 分类 ID
     */
    private Long id;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}
