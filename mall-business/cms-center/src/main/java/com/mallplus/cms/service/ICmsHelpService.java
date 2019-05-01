package com.mallplus.cms.service;

import com.mallplus.cms.entity.CmsHelp;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 帮助表 服务类
 * </p>
 *
 * @author zscat
 * @since 2019-04-17
 */
public interface ICmsHelpService extends IService<CmsHelp> {

    boolean saves(CmsHelp entity);
}
