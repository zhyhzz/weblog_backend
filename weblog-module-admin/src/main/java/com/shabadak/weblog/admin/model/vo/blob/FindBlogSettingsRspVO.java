package com.shabadak.weblog.admin.model.vo.blob;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Title: FindBlogSettingsRspVO
 * @Author shabadak
 * @Package com.shabadak.weblog.admin.model.vo.blob
 * @Date 2024/8/19 8:45
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindBlogSettingsRspVO {

    private String logo;

    private String name;

    private String author;

    private String introduction;

    private String avatar;

    private String githubHomepage;

    private String csdnHomepage;

    private String giteeHomepage;

    private String zhihuHomepage;
}