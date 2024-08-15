package com.shabadak.weblog.admin.model.vo.tag;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @Title: AddCategoryReqVO
 * @Author shabadak
 * @Package com.shabadak.weblog.admin.model.vo.category
 * @Date 2024/8/12 11:03
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "添加标签VO")
public class AddTagReqVO {

    @NotBlank(message = "标签名称不能为空")
    @Length(min = 1, max = 10, message = "标签名称字数限制 1 ~ 10 之间")
    private String name;

}
