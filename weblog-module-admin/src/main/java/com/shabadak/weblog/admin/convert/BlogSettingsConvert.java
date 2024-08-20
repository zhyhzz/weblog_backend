package com.shabadak.weblog.admin.convert;

import com.shabadak.weblog.admin.model.vo.blob.FindBlogSettingsRspVO;
import com.shabadak.weblog.admin.model.vo.blob.UpdateBlogSettingsReqVO;
import com.shabadak.weblog.common.domain.dos.BlogSettingsDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Title: BlogSettingsConvert
 * @Author shabadak
 * @Package com.shabadak.weblog.admin.convert
 * @Date 2024/8/19 8:42
 * @description
 */
@Mapper
public interface BlogSettingsConvert {
    /**
     * 初始化 convert 实例
     */
    BlogSettingsConvert INSTANCE = Mappers.getMapper(BlogSettingsConvert.class);

    /**
     * 将 VO 转化为 DO
     * @param bean
     * @return
     */
    BlogSettingsDO convertVO2DO(UpdateBlogSettingsReqVO bean);


    /**
     * 将 DO 转化为 VO
     * @param bean
     * @return
     */
    FindBlogSettingsRspVO convertDO2VO(BlogSettingsDO bean);

}
